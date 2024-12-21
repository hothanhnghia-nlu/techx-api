package vn.edu.hcmuaf.fit.api.config;

import io.swagger.v3.oas.annotations.enums.SecuritySchemeType;
import io.swagger.v3.oas.annotations.tags.Tag;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.info.License;
import io.swagger.v3.oas.models.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.security.SecurityScheme;
import io.swagger.v3.oas.models.servers.Server;
import org.springdoc.core.models.GroupedOpenApi;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.beans.factory.annotation.Value;

import java.util.List;

@Configuration
@SecurityScheme(
        name = "BearerAuth",
        type = SecuritySchemeType.HTTP,
        bearerFormat = "JWT",
        scheme = "bearer"
)
public class OpenAPIConfig {

    @Value("${open-api.service.title}")
    private String serviceTitle;

    @Value("${open-api.service.version}")
    private String serviceVersion;

    @Value("${open-api.service.url}")
    private String serviceUrl;

    @Bean
    public OpenAPI customOpenAPI() {
        return new OpenAPI()
                .info(new Info()
                        .title(serviceTitle)
                        .version(serviceVersion)
                        .description("REST API Documentation")
                        .contact(new Contact()
                                .name("TechX Team")
                                .email("contact@techx.com")
                                .url("https://techx.com")))
                .servers(List.of(new Server().url(serviceUrl)))
                .addSecurityItem(new SecurityRequirement().addList("BearerAuth"));
    }

    @Bean
    public GroupedOpenApi publicApi() {
        return GroupedOpenApi.builder()
                .group("public")
                .pathsToMatch("/api/**")
                .build();
    }
}