package at.shtrans.frontend.configuration;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
//import org.springframework.web.reactive.function.client.WebClient;

import org.springframework.web.client.RestClient;

@Configuration
public class SpringBootRestClientConfiguration {

    @Value("${REMOTE_BASE_URI:http://localhost:8080/restful-web-service/}")
    private String apiUrl;

    /*
    @Bean
    public WebClient webClient() {
        return WebClient.builder().baseUrl(apiUrl).build();
    }
     */

    // RestClient braucht kene zusaetzliche Bibliothek
    @Bean
    public RestClient restClient() {
        return RestClient.builder().baseUrl(apiUrl).build();
    }

}
