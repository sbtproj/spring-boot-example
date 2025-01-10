package at.shtrans.frontend.api.service;

import at.shtrans.frontend.model.Customer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestClient;

import org.springframework.http.MediaType;

import java.util.List;

@Service
public class CustomerApiService {

    @Autowired
    private RestClient restClient;

    String url ="customer/";

    public Customer create(Customer customer) {
        return restClient.post() // Angeben, dass dies eine GET-Anfrage ist
                .uri(url + "/create") // URI für die Anfrage festlegen
                .accept(MediaType.APPLICATION_JSON)
                .retrieve() // Anfrage ausführen und Antwort abrufen
                .body(Customer.class); // Antworttext als Customer extrahieren
    }

    public Customer getById(Long id) {
        return restClient.get() // Angeben, dass dies eine GET-Anfrage ist
                .uri(url + "/{id}",id) // URI für die Anfrage festlegen
                .accept(MediaType.APPLICATION_JSON)
                .retrieve() // Anfrage ausführen und Antwort abrufen
                .body(Customer.class); // Antworttext als Customer extrahieren
    }

    public List<Customer> getAll() {
        return restClient.get() // Angeben, dass dies eine GET-Anfrage ist
                .uri(url) // URI für die Anfrage
                .accept(MediaType.APPLICATION_JSON)
                .retrieve() // Anfrage ausführen und Antwort abrufen
                .body(new ParameterizedTypeReference<List<Customer>>() {}); // Antworttext als Customer extrahieren
    }

}
