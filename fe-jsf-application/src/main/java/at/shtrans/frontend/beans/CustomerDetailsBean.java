package at.shtrans.frontend.beans;

import at.shtrans.frontend.api.service.web.client.CustomerWebClientApiService;
import at.shtrans.frontend.model.Customer;
import jakarta.annotation.ManagedBean;
import jakarta.enterprise.context.SessionScoped;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import java.io.Serializable;

@ManagedBean
@SessionScoped
public class CustomerDetailsBean implements Serializable {

    private final static Logger LOGGER = LoggerFactory.getLogger(CustomerDetailsBean.class);

    @Autowired
    private CustomerWebClientApiService customerWebClientApiService;

     Customer customer;

    public String loadDetails(Long id) {
        LOGGER.info("BEGIN : loadDetails -> {}", customer);

        customer = customerWebClientApiService.findById(id);

        LOGGER.info("END : loadDetails -> {}", customer);

        return "customer.xhtml";
    }

    public Customer getCustomer() {
        return customer;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }
}
