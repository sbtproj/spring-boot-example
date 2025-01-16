package at.shtrans.frontend.api.service;

import at.shtrans.frontend.model.Customer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestClient;

import java.util.List;

@Service
public class CustomerApiService {

    private final static Logger LOGGER = LoggerFactory.getLogger(CustomerApiService.class);

    @Autowired
    private RestClient restClient;

    String url = "customer/";

    public Customer create(Customer customer) {
        LOGGER.info("BEGIN : create -> {}", customer);

        customer = restClient.post() // Angeben, dass dies eine GET-Anfrage ist
           //     .uri(url + "/create") // URI für die Anfrage festlegen
                .uri(uriBuilder -> uriBuilder.path(url + "/create").queryParam("role", "ADMIN").build())
                .accept(MediaType.APPLICATION_JSON)
                .body(customer)
                .retrieve() // Anfrage ausführen und Antwort abrufen
                .body(Customer.class); // Antworttext als Customer extrahieren

        LOGGER.info("END : create -> {}", customer);
        return customer;
    }

    public Customer findById(Long id) {
        LOGGER.info("BEGIN : findById -> id={}", id);

        Customer customer = restClient.get() // Angeben, dass dies eine GET-Anfrage ist
                .uri(url + "/{id}", id) // URI für die Anfrage festlegen
               // .uri(uriBuilder -> uriBuilder.path(url + "/{id}").queryParam("id", id).queryParam("role", "ADMIN").build())
                .accept(MediaType.APPLICATION_JSON)
                .retrieve() // Anfrage ausführen und Antwort abrufen
                .body(Customer.class); // Antworttext als Customer extrahieren

        LOGGER.info("END : findById -> {}", customer);
        return customer;
    }

    public List<Customer> getAll() {
        LOGGER.info("BEGIN : getAll");

        List<Customer> customerList = restClient.get() // Angeben, dass dies eine GET-Anfrage ist
                //.uri(url) // URI für die Anfrage
                .uri(uriBuilder -> uriBuilder.path(url).queryParam("role", "ADMIN").build())
                .accept(MediaType.APPLICATION_JSON)
                .retrieve() // Anfrage ausführen und Antwort abrufen
                .body(new ParameterizedTypeReference<List<Customer>>() {
                }); // Antworttext als Customer extrahieren


        LOGGER.info("END : getAll -> {}", customerList);
        return customerList;
    }

    public void deleteById(Long id) {
        LOGGER.info("BEGIN : deleteById -> id={}", id);

       ResponseEntity responseEntity= restClient.delete()
                .uri(url + "delete/{id}", id)
                //.accept(MediaType.APPLICATION_JSON)
                .retrieve()
                .toBodilessEntity();

        LOGGER.info("END : deleteById -> responseEntity={}", responseEntity);
       // return customer;
    }

}
