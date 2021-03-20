package com.shuangyangad.service.admin.service;

import com.shuangyangad.service.admin.graphql.user.model.LoginInputTO;
import com.shuangyangad.service.admin.graphql.user.model.LoginResultTO;
import graphql.schema.DataFetchingEnvironment;

public interface SystemUserService {
    LoginResultTO login(LoginInputTO input, DataFetchingEnvironment env);
}
