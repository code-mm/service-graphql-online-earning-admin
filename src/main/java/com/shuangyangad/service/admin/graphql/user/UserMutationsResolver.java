package com.shuangyangad.service.admin.graphql.user;

import com.shuangyangad.service.admin.graphql.user.api.MutationResolver;
import com.shuangyangad.service.admin.graphql.user.model.LoginInputTO;
import com.shuangyangad.service.admin.graphql.user.model.LoginResultTO;
import com.shuangyangad.service.admin.service.SystemUserService;
import graphql.schema.DataFetchingEnvironment;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class UserMutationsResolver implements MutationResolver {

    @Autowired
    private SystemUserService systemUserService;


    @Override
    public LoginResultTO login(LoginInputTO input, DataFetchingEnvironment env) throws Exception {
        String username = input.getUsername();
        if (username.startsWith("system")) {
            return systemUserService.login(input, env);
        }
        return null;
    }
}
