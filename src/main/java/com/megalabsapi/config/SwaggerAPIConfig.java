package com.megalabsapi.config;
import io.swagger.v3.oas.models.Components;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.info.License;
import io.swagger.v3.oas.models.security.SecurityScheme;
import io.swagger.v3.oas.models.servers.Server;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.List;

@Configuration
public class SwaggerAPIConfig {
    //Consultar el swagger en el siguiente enlace: http://localhost:8080/api/v1/swagger-ui/index.html#
    @Value("${megalabas-api.openapi.dev-url}")
    private String devUrl;

    @Bean
    public OpenAPI myOpenAPI(){
        //Definir el servidor de desarrollo
        Server devServer = new Server();
        devServer.setUrl(devUrl);
        devServer.setDescription("Megalabs Server");

        //Informacion de contacto
        Contact contact = new Contact();
        contact.setEmail("dsanchezc9@upao.edu.pe\n");
        contact.setName("Diego Sanchez Chuquimango");
        contact.setUrl("https://github.com/dsanchezchu");

        License mitLicense = new License().name("MIT License").url("https://opensource.org/licenses/MIT");

        //Informacion general de la API
        Info info = new Info()
                .title("API Megalabs")
                .version("1.0")
                .contact(contact)
                .description("API Restful de venta de libros")
                .termsOfService("https://www.google.com")
                .license(mitLicense);

        // Configuración de seguridad JWT
        SecurityScheme securityScheme = new SecurityScheme()
                .type(SecurityScheme.Type.HTTP)
                .scheme("bearer")
                .bearerFormat("JWT")
                .name("JWT Authentication");

        Components components = new Components()
                .addSecuritySchemes("bearerAuth", securityScheme);

        // Requerimiento de seguridad para utilizar en las operaciones
        //SecurityRequirement securityRequirement = new SecurityRequirement().addList("bearerAuth");


        return new OpenAPI()
                .info(info)
                .servers(List.of(devServer));
                /*.addSecurityItem(securityRequirement)
                .components(components);*/
    }

}