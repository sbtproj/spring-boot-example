package at.shtrans.frontend.beans;

import at.shtrans.frontend.api.service.web.client.CustomerWebClientApiService;
import at.shtrans.frontend.model.Customer;
import jakarta.annotation.ManagedBean;
import jakarta.faces.view.ViewScoped;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import java.io.Serializable;
import java.util.List;

@ManagedBean
@ViewScoped
public class CustomerListBean implements Serializable {

    private final static Logger LOGGER = LoggerFactory.getLogger(CustomerListBean.class);

    @Autowired
    private CustomerWebClientApiService customerWebClientApiService;

    public List<Customer> getCustomers() {
        List<Customer> customers = customerWebClientApiService.getAll();
        LOGGER.info("getAll() -> " + customers.toString());

        return customers;
    }

}
