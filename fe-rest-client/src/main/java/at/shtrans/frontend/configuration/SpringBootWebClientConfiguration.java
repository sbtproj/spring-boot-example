package at.shtrans.frontend.configuration;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.http.HttpHeaders;
import org.springframework.web.reactive.function.client.WebClient;

import java.util.Base64;

@Configuration
@ComponentScan(basePackages = {"at.shtrans.frontend.api.service.web.client"})
@PropertySource("classpath:client-application.properties")
public class SpringBootWebClientConfiguration {

    @Value("${restful.service.uri:http://localhost:8080/restful-ws/}")
    private String apiUrl;

    @Value("${restful.service.auth.user}")
    private String user;

    @Value("${restful.service.auth.password}")
    private String password;

    @Bean
    public WebClient webClient() {

        return WebClient
                .builder()
                .baseUrl(apiUrl)
                .defaultHeader(HttpHeaders.AUTHORIZATION, encodeBasic(user, password))
                .build();
    }

    private String encodeBasic(String username, String password) {
        return "Basic "+ Base64
                .getEncoder()
                .encodeToString((username+":"+password).getBytes());
    }
}
