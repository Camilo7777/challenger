package com.mercadolibre.challenger.configuration;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.servers.Server;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.Collections;

@Configuration
public class OpenAPIConfig {

    @Bean
    public OpenAPI customOpenAPI() {
        return new OpenAPI()
                .info(new Info()
                        .title("API para gestion de IPS")
                        .version("1.0.0")
                        .description("Esta API permite obtener y bloquear ips maliciosas."))
                .servers(Collections.singletonList(new Server()
                        .url("http://localhost:8080")
                        .description("Servidor de desarrollo local")));
    }
}
