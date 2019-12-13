package com.farzi.eng.farziweb.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;
import java.util.Arrays;
import java.util.HashSet;

@Configuration
@EnableSwagger2
public class SwaggerConfig {

    private Contact defaultContact = new Contact("Farzi Web", "", "thefaarziengineers@gmail.com");
    private ApiInfo defaultApiInfo = new ApiInfo("Farzi Backend API", "API Documentation", "1.0", "Terms and Conditions", defaultContact,
            "Apache 2.0", "http://apache_licence_url");
    private HashSet defaultProducesAndConsumes = new HashSet(
            Arrays.asList("application/json", "application/json"));

    @Bean
    public Docket api() {
        return new Docket(DocumentationType.SWAGGER_2)
                .apiInfo(defaultApiInfo)
                .produces(defaultProducesAndConsumes)
                .consumes(defaultProducesAndConsumes);
    }
}
