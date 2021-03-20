package com.shuangyangad.service.admin.config;

import graphql.language.StringValue;
import graphql.schema.*;

import java.io.File;
import java.net.URI;
import java.util.Optional;
import java.util.function.Function;

import static graphql.scalars.util.Kit.typeName;

public class URIScalar extends GraphQLScalarType {

    public URIScalar() {
        super("URI", "A URI scalar", new Coercing<URI, URI>() {
            @Override
            public URI serialize(Object input) throws CoercingSerializeException {
                Optional<URI> uri;
                if (input instanceof String) {
                    uri = Optional.of(parseURI(input.toString(), CoercingSerializeException::new));
                } else {
                    uri = toURI(input);
                }
                if (uri.isPresent()) {
                    return uri.get();
                }
                throw new CoercingSerializeException(
                        "Expected a 'URI' like object but was '" + typeName(input) + "'."
                );
            }

            @Override
            public URI parseValue(Object input) throws CoercingParseValueException {
                String urlStr;
                if (input instanceof String) {
                    urlStr = String.valueOf(input);
                } else {
                    Optional<URI> url = toURI(input);
                    if (!url.isPresent()) {
                        throw new CoercingParseValueException(
                                "Expected a 'URI' like object but was '" + typeName(input) + "'."
                        );
                    }
                    return url.get();
                }
                return parseURI(urlStr, CoercingParseValueException::new);
            }

            @Override
            public URI parseLiteral(Object input) throws CoercingParseLiteralException {
                if (!(input instanceof StringValue)) {
                    throw new CoercingParseLiteralException(
                            "Expected AST type 'StringValue' but was '" + typeName(input) + "'."
                    );
                }
                return parseURI(((StringValue) input).getValue(), CoercingParseLiteralException::new);
            }

            private URI parseURI(String input, Function<String, RuntimeException> exceptionMaker) {
                URI uri = URI.create(input);
                if (uri == null) {
                    throw exceptionMaker.apply("Invalid URI value : '" + input + "'.");
                }
                return uri;
            }
        });
    }

    private static Optional<URI> toURI(Object input) {
        if (input instanceof URI) {
            return Optional.of((URI) input);
        } else if (input instanceof URI) {
            return Optional.of(((URI) input));
        } else if (input instanceof File) {
            return Optional.of(((File) input).toURI());
        }
        return Optional.empty();
    }

}
