package com.example.hometwin.src.config;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.Components;
import io.swagger.v3.oas.models.info.Info;

import org.springdoc.core.models.GroupedOpenApi;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@OpenAPIDefinition
public class SwaggerConfig {

    @Bean
    public OpenAPI openAPI(){
        Info info = new Info()
                .title("Geniverse HomeTwin Search Web")
                .description("Geniverse HomeTwin Search Web(For developer testing)")
                .version("v1.0.0");
        return new OpenAPI()
                .components(new Components())
                .info(info);
    }
}
