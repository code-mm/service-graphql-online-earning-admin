package com.shuangyangad.service.admin.graphql.user;

import com.shuangyangad.service.admin.graphql.user.api.QueryResolver;

import com.shuangyangad.service.admin.graphql.user.model.RoleTO;
import graphql.schema.DataFetchingEnvironment;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class UserQueriesResolver implements QueryResolver {

    @Override
    public List<RoleTO> queryRoles(DataFetchingEnvironment env) throws Exception {
        return null;
    }
}
