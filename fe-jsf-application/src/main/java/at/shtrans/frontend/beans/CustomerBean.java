package at.shtrans.frontend.beans;

import at.shtrans.frontend.api.service.web.client.CustomerWebClientApiService;
import at.shtrans.frontend.model.Customer;
import jakarta.annotation.ManagedBean;
import jakarta.annotation.PostConstruct;
import jakarta.enterprise.context.SessionScoped;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import java.io.Serializable;

@ManagedBean
@SessionScoped
public class CustomerBean implements Serializable {

    private final static Logger LOGGER = LoggerFactory.getLogger(CustomerBean.class);

    @Autowired
    private CustomerWebClientApiService customerWebClientApiService;

    Customer customer;

    @PostConstruct
    private void init() {
        customer = new Customer();
    }

    public String create() {
        LOGGER.info("BEGIN : create -> {}", customer);

        customer = customerWebClientApiService.create(customer);

        LOGGER.info("END : create -> {}", customer);

        return "customer_list.xhtml";
    }

    public String update() {
        LOGGER.info("BEGIN : create -> {}", customer);

        customer = customerWebClientApiService.create(customer);

        LOGGER.info("END : create -> {}", customer);

        return "customer_list.xhtml";
    }

    public String deleteById(Long id) {
        LOGGER.info("BEGIN : delete -> id={}", id);

        customerWebClientApiService.deleteById(id);

        LOGGER.info("END : delete -> id={}", id);
        return "customer_list.xhtml";
    }

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
