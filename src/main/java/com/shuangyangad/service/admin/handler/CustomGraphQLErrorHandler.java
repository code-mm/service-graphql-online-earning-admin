package com.shuangyangad.service.admin.handler;


import com.shuangyangad.service.admin.exception.AppException;
import graphql.GraphQLError;
import graphql.GraphqlErrorBuilder;
import graphql.kickstart.execution.error.GraphQLErrorHandler;
import graphql.kickstart.spring.error.ErrorContext;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@Slf4j
@Component
class CustomGraphQLErrorHandler implements GraphQLErrorHandler {

    @Override
    public List<GraphQLError> processErrors(List<GraphQLError> list) {
        log.info("Handle errors: {}", list);
        return list;
    }

    @ExceptionHandler(value = AppException.class)
    public GraphQLError toCustomError(AppException e, ErrorContext errorContext) {
        Map<String, Object> extensions = Optional
                .ofNullable(errorContext.getExtensions()).orElseGet(HashMap::new);
        extensions.put(e.getApiMessage().getCode(), e.getApiMessage().getMessage());
        return GraphqlErrorBuilder.newError()
                .message(e.getMessage())
                .extensions(extensions)
                .errorType(errorContext.getErrorType())
                .locations(errorContext.getLocations())
                .path(errorContext.getPath())
                .build();
    }
}
