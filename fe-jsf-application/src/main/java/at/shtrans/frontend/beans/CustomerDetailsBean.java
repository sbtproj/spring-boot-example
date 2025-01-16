package at.shtrans.frontend.beans;

import at.shtrans.frontend.api.service.CustomerApiService;
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
public class CustomerDetailsBean implements Serializable {

    private final static Logger LOGGER = LoggerFactory.getLogger(CustomerDetailsBean.class);

    @Autowired
    private CustomerApiService customerApiService;

     Customer customer;

     /*
     @PostConstruct
    private void init() {
         customer = new Customer();
    }
      */

    public String loadDetails(Long id) {
        LOGGER.info("BEGIN : loadDetails -> {}", customer);

        customer = customerApiService.findById(id);

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
