package at.shtrans.frontend.api.service.web.client;

import at.shtrans.frontend.model.Customer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

import java.util.List;

@Service
public class CustomerWebClientApiService {

    private final static Logger LOGGER = LoggerFactory.getLogger(CustomerWebClientApiService.class);

    @Autowired
    private WebClient webClient;

    String url = "customer/";


    public Customer create(Customer customer) {
        LOGGER.info("BEGIN : create -> {}", customer);

        customer = webClient.post() // Angeben, dass dies eine GET-Anfrage ist
                // URI für die Anfrage festlegen
                .uri(uriBuilder -> uriBuilder.path(url + "/create").queryParam("role", "ADMIN").build())
                .accept(MediaType.APPLICATION_JSON)
                .body(Mono.just(customer), Customer.class)
                .retrieve() // Anfrage ausführen und Antwort abrufen
                .bodyToMono(Customer.class)// Antworttext als Customer extrahieren
                .block();

        LOGGER.info("END : create -> {}", customer);
        return customer;
    }

    public List<Customer> getAll() {
        LOGGER.info("BEGIN : getAll");

        List<Customer> customerList = webClient.get()
                .uri(uriBuilder -> uriBuilder.path(url).queryParam("role", "ADVISOR").build())
                .accept(MediaType.APPLICATION_JSON)
                .retrieve()
                .bodyToFlux(Customer.class)
                .collectList()
                .block();

        LOGGER.info("END : getAll -> {}", customerList);
        return customerList;
    }

    public Customer findById(Long id) {
        LOGGER.info("BEGIN : webClient -> id={}", id);

        Customer customer = webClient.get()
                .uri(uriBuilder -> uriBuilder.path(url + "/" + id).queryParam("role", "ADVISOR").build())
                .accept(MediaType.APPLICATION_JSON)
                .retrieve()
                .bodyToMono(Customer.class)
                .block();

        LOGGER.info("END : findById -> {}", customer);
        return customer;
    }


    public void deleteById(Long id) {
        LOGGER.info("BEGIN : deleteById -> id={}", id);

        ResponseEntity responseEntity = webClient.delete()
                .uri(uriBuilder -> uriBuilder.path(url + "delete/" + id).queryParam("role", "ADMIN")
                        .build())
                .retrieve()
                .bodyToMono(ResponseEntity.class).block();

        LOGGER.info("END : deleteById -> responseEntity={}", responseEntity);
    }

}
