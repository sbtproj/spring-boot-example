package at.shtrans.swagger;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Contact;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import io.swagger.v3.oas.models.servers.Server;
import io.swagger.v3.oas.models.info.Info;
import java.util.List;

@Configuration
public class SwaggerConfiguration {

    @Value("${project.version}")
    private String projectVersion;

    @Value("${swagger.open.api.info.title}")
    private String infoTitle;

    @Value("${swagger.open.api.info.description}")
    private String infoDescription;

    @Value("${swagger.open.api.info.contact.email}")
    private String contactEmail;

    @Value("${swagger.open.api.info.contact.name}")
    private String contactName;

    @Value("${swagger.open.api.info.contact.url}")
    private String contactUrl;

    @Value("${swagger.open.api.info.server1.url}")
    private String applicationUrl;

    @Value("${swagger.open.api.info.server1.description}")
    private String description;

    @Bean
    public OpenAPI openAPI() {
        return new OpenAPI().info(buildInfo()).servers(List.of(buildServer()));
    }

    private Info buildInfo() {
        return new Info().title(infoTitle).version(projectVersion).description(infoDescription).contact(buildContact());
    }

    private Contact buildContact() {
        return new Contact().email(contactEmail).name(contactName).url(contactUrl);
    }

    private Server buildServer(){
        return new Server().url(applicationUrl).description(description);
    }
}

