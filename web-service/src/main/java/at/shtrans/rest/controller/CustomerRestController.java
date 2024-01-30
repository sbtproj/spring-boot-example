package at.shtrans.rest.controller;

import at.shtrans.dto.CustomerDTO;
import at.shtrans.service.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class CustomerRestController {

    @Autowired
    private CustomerService customerService;

    @GetMapping(value = "/", produces = MediaType.TEXT_PLAIN_VALUE)
    public String index() {

        return "Hello there";
    }

    @GetMapping(value = "/customers", produces = MediaType.TEXT_PLAIN_VALUE)
    public List<CustomerDTO> customers() {

        return customerService.findAll();
    }

}
