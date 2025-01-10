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
public class CustomerBean implements Serializable {

    private final static Logger LOGGER = LoggerFactory.getLogger(CustomerBean.class);

    @Autowired
    private CustomerApiService customerApiService;

     Customer customer;

     @PostConstruct
    private void init() {
         customer = new Customer();
    }

    public void create() {
        LOGGER.info("BEGIN -> create !!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!1 {}" , customer);
        customer = customerApiService.create(customer);
        LOGGER.info("END -> create !!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!1 ");
    }
    public void delete(Customer customer) {
        //customerApiService.delete(customer);
    }

    public Customer getCustomer() {
        return customer;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }
}
