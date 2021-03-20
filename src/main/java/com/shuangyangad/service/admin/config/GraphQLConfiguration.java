package com.shuangyangad.service.admin.config;

import graphql.language.StringValue;
import graphql.scalars.ExtendedScalars;
import graphql.schema.*;
import graphql.schema.idl.InterfaceWiringEnvironment;
import graphql.schema.idl.RuntimeWiring;
import graphql.schema.idl.UnionWiringEnvironment;
import graphql.schema.idl.WiringFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.Objects;
import java.util.regex.Pattern;


/**
 * @author bogdankobylinsky
 */
@Configuration
public class GraphQLConfiguration {

    @Bean
    public GraphQLScalarType extendedScalarsDateTime() {
        return ExtendedScalars.DateTime;
    }

    @Bean
    public GraphQLScalarType extendedScalarsDate() {
        return ExtendedScalars.Date;
    }

    @Bean
    public GraphQLScalarType extendedScalarsTime() {
        return ExtendedScalars.Time;
    }

    @Bean
    public GraphQLScalarType extendedScalarsBigDecimal() {
        return ExtendedScalars.GraphQLBigDecimal;
    }


    @Bean
    public GraphQLScalarType longType() {
        return ExtendedScalars.GraphQLLong;
    }


    @Bean
    public GraphQLScalarType urlGraphQLScalarType() {
        return ExtendedScalars.Url;

    }

    @Bean
    public GraphQLScalarType uriGraphQLScalarType() {
        return new URIScalar();

    }

    @Bean
    public GraphQLScalarType jsonGraphQLScalarType() {
        return ExtendedScalars.Json;

    }

    @Bean
    public GraphQLScalarType objectGraphQLScalarType() {
        return ExtendedScalars.Object;

    }

    @Bean
    public GraphQLScalarType phoneNumberGraphQLScalarType() {

        String regex = "(\\+\\d+)?1[3458]\\d{9}$";
        return ExtendedScalars.newRegexScalar("PhoneNumber")
                .addPattern(Pattern.compile(regex))
                .build();
    }


    @Bean
    public GraphQLScalarType emailGraphQLScalarType() {
        String regex = "(?:[a-z0-9!#$%&'*+/=?^_`{|}~-]+(?:\\.[a-z0-9!#$%&'*+/=?^_`{|}~-]+)*|\"(?:[\\x01-\\x08\\x0b\\x0c\\x0e-\\x1f\\x21\\x23-\\x5b\\x5d-\\x7f]|\\\\[\\x01-\\x09\\x0b\\x0c\\x0e-\\x7f])*\")@(?:(?:[a-z0-9](?:[a-z0-9-]*[a-z0-9])?\\.)+[a-z0-9](?:[a-z0-9-]*[a-z0-9])?|\\[(?:(?:25[0-5]|2[0-4][0-9]|[01]?[0-9][0-9]?)\\.){3}(?:25[0-5]|2[0-4][0-9]|[01]?[0-9][0-9]?|[a-z0-9-]*[a-z0-9]:(?:[\\x01-\\x08\\x0b\\x0c\\x0e-\\x1f\\x21-\\x5a\\x53-\\x7f]|\\\\[\\x01-\\x09\\x0b\\x0c\\x0e-\\x7f])+)\\])";
        return ExtendedScalars.newRegexScalar("Email")
                .addPattern(Pattern.compile(regex))
                .build();
    }


    @Bean
    public GraphQLScalarType positiveFloatGraphQLScalarType() {
        return ExtendedScalars.PositiveFloat;

    }


    @Bean
    public GraphQLScalarType textGraphQLScalarType() {
        return GraphQLScalarType.newScalar()
                .name("Text")
                .coercing(new Coercing<String, String>() {
                    @Override
                    public String serialize(final Object o) {
                        return o.toString();
                    }

                    @Override
                    public String parseValue(final Object o) {
                        return serialize(o);
                    }

                    @Override
                    public String parseLiteral(final Object input) {
                        if (!(input instanceof StringValue)) {
                            return null;
                        }
                        return ((StringValue) input).getValue();
                    }
                }).build();
    }

    @Bean
    public GraphQLScalarType mardownGraphQLScalarType() {
        return GraphQLScalarType.newScalar()
                .name("Html")
                .coercing(new Coercing<String, String>() {
                    @Override
                    public String serialize(final Object o) {
                        return o.toString();
                    }

                    @Override
                    public String parseValue(final Object o) {
                        return serialize(o);
                    }

                    @Override
                    public String parseLiteral(final Object input) {
                        if (!(input instanceof StringValue)) {
                            return null;
                        }
                        return ((StringValue) input).getValue();
                    }
                }).build();
    }


    @Bean
    public GraphQLScalarType bigDecimalGraphQLScalarType() {
        return GraphQLScalarType.newScalar()
                .name("BigDecimal")
                .coercing(new Coercing<String, BigDecimal>() {
                    @Override
                    public BigDecimal serialize(final Object o) throws CoercingSerializeException {
                        return new BigDecimal((String) o);
                    }

                    @Override
                    public String parseValue(final Object o) throws CoercingParseValueException {
                        return ((BigDecimal) o).setScale(2, RoundingMode.HALF_UP).toString();
                    }

                    @Override
                    public String parseLiteral(final Object input) throws CoercingParseLiteralException {
                        if (!(input instanceof StringValue)) {
                            return null;
                        }
                        return ((StringValue) input).getValue();
                    }
                }).build();
    }

    @Bean
    public RuntimeWiring buildDynamicRuntimeWiring() {
        WiringFactory dynamicWiringFactory = new WiringFactory() {

            @Override
            public boolean providesTypeResolver(InterfaceWiringEnvironment environment) {
                return !Objects
                        .isNull(environment.getInterfaceTypeDefinition().getDirectives("specialMarker"));
            }

            @Override
            public boolean providesTypeResolver(UnionWiringEnvironment environment) {
                return !Objects
                        .isNull(environment.getUnionTypeDefinition().getDirectives("specialMarker"));
            }
        };
        return RuntimeWiring.newRuntimeWiring()
                .wiringFactory(dynamicWiringFactory).scalar(ExtendedScalars.DateTime).build();
    }
}