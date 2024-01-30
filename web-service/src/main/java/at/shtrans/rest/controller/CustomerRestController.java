package at.shtrans.rest.controller;

import at.shtrans.dto.CustomerDTO;
import at.shtrans.service.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
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

    @RequestMapping(value = "/customers", method = RequestMethod.GET, produces = "application/json")
    public List<CustomerDTO> customers() {

        List<CustomerDTO> dtoList = customerService.findAll();

        CustomerDTO dto = new CustomerDTO();
        dto.setId(1L);
        dto.setVersion(1);
        dto.setFirstName("FirstName");
        dto.setLastName("LastName");

        dtoList.add(dto);

        return dtoList;
    }

}
