package at.shtrans.swagger2.configuration;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Info;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Contact;
//import io.swagger.v3.oas.models.info.Info;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;


@Configuration
@OpenAPIDefinition(info = @Info(title = "Employees API", version = "2.0", description = "Employees Information"))
public class SwaggerConfiguration {

    @Bean
    public OpenAPI openAPI() {
        return new OpenAPI()
                .info(new io.swagger.v3.oas.models.info.Info().title("the title").version("0.0").description("My API")
                        .contact(new Contact().name("Fred").url("http://gigantic-server.com").email("Fred@gigagantic-server.com")));
    }
    // https://github.com/eugenp/tutorials/blob/master/spring-boot-modules/spring-boot-swagger-springfox/src/main/java/com/baeldung/swagger2/SwaggerConfig.java
}

