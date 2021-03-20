package com.shuangyangad.service.admin.graphql.subscription;

import graphql.kickstart.tools.GraphQLSubscriptionResolver;
import graphql.schema.DataFetchingEnvironment;
import lombok.extern.slf4j.Slf4j;
import org.reactivestreams.Publisher;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;

import java.time.Duration;

@Service
@Slf4j
class MySubscriptionResolver implements GraphQLSubscriptionResolver {
    Publisher<Integer> hello(String hello, DataFetchingEnvironment env) {
        log.info("hello : " + hello);
        return Flux.range(0, 100).delayElements(Duration.ofSeconds(1));

    }
}
