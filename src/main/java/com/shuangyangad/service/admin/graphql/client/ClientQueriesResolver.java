package com.shuangyangad.service.admin.graphql.client;


import com.shuangyangad.service.admin.graphql.client.api.QueryResolver;
import com.shuangyangad.service.admin.graphql.client.model.ClientTO;
import graphql.schema.DataFetchingEnvironment;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class ClientQueriesResolver implements QueryResolver {


    @Override
    public List<ClientTO> queryClients(DataFetchingEnvironment environment) throws Exception {
        return new ArrayList<>();
    }
}
