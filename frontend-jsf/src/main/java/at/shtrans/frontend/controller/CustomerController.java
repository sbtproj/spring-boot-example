package at.shtrans.frontend.controller;

import at.shtrans.frontend.model.Customer;
import jakarta.faces.view.ViewScoped;
import org.springframework.stereotype.Controller;

import java.util.ArrayList;
import java.util.List;

import java.io.Serializable;

@ViewScoped
@Controller
public class CustomerController implements Serializable {

 //   @Autowired
 //   private FormBean formBean;

    private Customer customer;

    public List<Customer> getList() {
        return null;
    }
    public void save() {
        //personRepository.save(person);
        //person = new Person();
    }
    public void delete(Customer customer) {
        //personRepository.delete(person);
    }

    public Customer getCustomer() {
        return customer;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }
}
