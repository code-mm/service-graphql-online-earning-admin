package com.shuangyangad.service.admin.graphql.client;


import com.shuangyangad.service.admin.graphql.client.api.MutationResolver;
import com.shuangyangad.service.admin.graphql.client.model.*;
import graphql.schema.DataFetchingEnvironment;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.validation.constraints.NotNull;


@Component
public class ClientMutationsResolver implements MutationResolver {

    @Override
    public CreateClientResultTO createClient(CreateClientInputTO input, DataFetchingEnvironment env) throws Exception {
        return null;
    }

    @Override
    public ClientTO updateClient(UpdateClientInputTO input, DataFetchingEnvironment env) throws Exception {
        return null;
    }

    @Override
    public boolean deleteClient(DeleteClientInputTO input, DataFetchingEnvironment env) throws Exception {
        return false;
    }
}
