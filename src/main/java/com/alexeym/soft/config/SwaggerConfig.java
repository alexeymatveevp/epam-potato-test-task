package com.alexeym.soft.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.bind.annotation.RequestMethod;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.builders.ResponseMessageBuilder;
import springfox.documentation.schema.ModelRef;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;

/**
 * Created by Alexey Matveev on 3/7/2018.
 */
@Configuration
@EnableSwagger2
public class SwaggerConfig {

    @Bean
    public Docket api() {
        return new Docket(DocumentationType.SWAGGER_2)
                .select()
                .apis(RequestHandlerSelectors.basePackage("com.alexeym.soft.ctrl"))
                .paths(PathSelectors.any())
                .build()
                .globalResponseMessage(RequestMethod.GET, Arrays.asList(
                        new ResponseMessageBuilder()
                                .code(400)
                                .message("Bad user input")
                                .build(),
                        new ResponseMessageBuilder()
                                .code(500)
                                .message("Error on server")
                                .build()))
                .apiInfo(apiInfo());
    }

    private ApiInfo apiInfo() {
        return new ApiInfo(
                "Potato bags API",
                "EPAM test task - potato business API.",
                "",
                "Terms of service",
                new Contact("Alexey Matveev", "http://localhost:8080/swagger-ui.html", "david.lucky.star@gmail.com"),
                "License of API", "API license URL", Collections.emptyList());
    }
}
