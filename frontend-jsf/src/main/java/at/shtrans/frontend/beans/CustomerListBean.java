package at.shtrans.frontend.beans;

import at.shtrans.frontend.api.service.CustomerApiService;
import at.shtrans.frontend.model.Customer;
import jakarta.annotation.ManagedBean;
import jakarta.faces.view.ViewScoped;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;

import java.io.Serializable;
import java.util.List;

@ManagedBean
@ViewScoped
public class CustomerListBean implements Serializable {

    private static Log log = LogFactory.getLog(CustomerListBean.class);

    @Autowired
    private CustomerApiService customerApiService;

    public List<Customer> getCustomers() {
        List<Customer> customers = customerApiService.getAll();
        log.info("getAll() -> " + customers.toString());

        return customers;
    }

}
