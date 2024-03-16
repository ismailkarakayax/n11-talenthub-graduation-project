package com.n11.userreviewservice.config;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SwaggerConfig {

    @Bean
    public OpenAPI customOpenAPI() {
        return new OpenAPI()
                .info(new Info()
                        .title("User Review Service")
                        .version("1.0")
                        .description("User, Review ve Recommendation işlemleri için dökümantasyon")
                        .contact(new Contact()
                                .email("ismlkrky58@gmail.com")
                                .name("Geliştirici")
                                .url("https://github.com/ismailkarakayax")
                        )
                );
    }

}
