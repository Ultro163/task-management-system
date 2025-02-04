package com.example.config;

import graphql.scalars.ExtendedScalars;
import graphql.schema.Coercing;
import graphql.schema.GraphQLScalarType;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.graphql.execution.RuntimeWiringConfigurer;

@Slf4j
@Configuration
public class GraphQLScalarConfig {

//    @Bean
//    public GraphQLScalarType graphQLLong() {
//        log.info("Registering GraphQLLong scalar");
//        return ExtendedScalars.GraphQLLong;
//    }
//
//    @Bean
//    public GraphQLScalarType dateTime() {
//        log.info("Registering DateTime scalar");
//        return ExtendedScalars.DateTime;
//    }

    //    @Bean
//    public RuntimeWiringConfigurer runtimeWiringConfigurer() {
//        return wiringBuilder -> wiringBuilder
//                .scalar(ExtendedScalars.GraphQLLong)
//                .scalar(ExtendedScalars.DateTime).build();
//    }
    @Bean
    public GraphQLScalarType longScalar() {
        return GraphQLScalarType.newScalar()
                .name("GraphQLLong") // Имя должно совпадать с именем в схеме
                .description("Long scalar type")
                .coercing(new Coercing<Long, Long>() {
                    @Override
                    public Long serialize(Object dataFetcherResult) {
                        if (dataFetcherResult instanceof Long) {
                            return (Long) dataFetcherResult;
                        }
                        throw new IllegalArgumentException("Expected a Long object.");
                    }

                    @Override
                    public Long parseValue(Object input) {
                        return Long.valueOf(input.toString());
                    }

//                    @Override
//                    @Deprecated
//                    public Long parseLiteral(Object input) {
//                        if (input instanceof String) {
//                            return Long.valueOf((String) input);
//                        }
//                        throw new IllegalArgumentException("Expected a Long value.");
//                    }
                })
                .build();
    }

    @Bean
    public RuntimeWiringConfigurer runtimeWiringConfigurer(GraphQLScalarType longScalar) {
        return wiringBuilder -> wiringBuilder.scalar(longScalar).scalar(ExtendedScalars.DateTime);
    }
}

