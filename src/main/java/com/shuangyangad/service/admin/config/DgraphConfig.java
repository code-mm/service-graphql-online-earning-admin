package com.shuangyangad.service.admin.config;

import com.shuangyangad.service.dgraph.client.DGraphGraphClient;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class DgraphConfig {


    @Bean
    DGraphGraphClient dGraphGraphClient() {
        return DGraphGraphClient.builder()
                .setUrl("http://localhost:8080/graphql")
                .build();
    }
}
