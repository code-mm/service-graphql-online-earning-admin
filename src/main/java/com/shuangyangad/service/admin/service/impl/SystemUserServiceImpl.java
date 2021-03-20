package com.shuangyangad.service.admin.service.impl;

import com.shopify.graphql.support.Input;
import com.shuangyangad.service.admin.exception.AppException;
import com.shuangyangad.service.admin.graphql.user.model.LoginInputTO;
import com.shuangyangad.service.admin.graphql.user.model.LoginResultTO;
import com.shuangyangad.service.admin.service.SystemUserService;
import com.shuangyangad.service.admin.utils.TokenUtils;
import com.shuangyangad.service.dgraph.client.DGraphGraphClient;
import com.shuangyangad.service.dgraph.graphql.*;
import graphql.schema.DataFetchingEnvironment;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;

@Service
@Slf4j
public class SystemUserServiceImpl implements SystemUserService {

    @Autowired
    private DGraphGraphClient dGraphGraphClient;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Override
    public LoginResultTO login(LoginInputTO input, DataFetchingEnvironment env) {
        QueryQuery query = Operations.query(new QueryQueryDefinition() {
            @Override
            public void define(QueryQuery queryQuery) {

                queryQuery.getSystemUser(input.getUsername(), new SystemUserQueryDefinition() {
                    @Override
                    public void define(SystemUserQuery systemUserQuery) {
                        systemUserQuery.password();
                        systemUserQuery.userId();
                    }
                });
            }
        });
        Query queryRes = dGraphGraphClient.query(query);
        SystemUser systemUser = queryRes.getGetSystemUser();
        if (systemUser == null) {
            throw new AppException(AppException.MESSAGE.NOT_EXISTS_USER_ID);
        }

        if (passwordEncoder.matches(input.getPassword(), systemUser.getPassword())) {
            return LoginResultTO.builder()
                    .setToken(TokenUtils.generateToken(systemUser.getUserId(), "system_user"))
                    .build();
        }
        throw new AppException(AppException.MESSAGE.ERROR_PASSWORD);
    }
}
