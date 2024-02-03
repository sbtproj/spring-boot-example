package at.shtrans.rest.controller;

import at.shtrans.dto.CustomerDTO;
import at.shtrans.service.CustomerService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;


@RestController
@RequestMapping("/customer")
public class CustomerRestController {

    @Autowired
    private CustomerService customerService;

    @RequestMapping(value = "/", method = RequestMethod.GET, produces = "application/json")
    public List<CustomerDTO> allCustomers() {
        return customerService.findAll();
    }

    @GetMapping(value = "/{id}", produces = "application/json")
    public CustomerDTO findById(@PathVariable(value = "id") Long id) {
        return getTestDto();
    }

    @GetMapping(value = "/firstName/{firstName}", produces = "application/json")
    public CustomerDTO findByFirstName(@PathVariable(value = "firstName") String firstName) {
        return getTestDto();
    }

    @GetMapping(value = "/lastName/{lastName}", produces = "application/json")
    public CustomerDTO findByLastName(@PathVariable(value = "lastName") String lastName) {
        return getTestDto();
    }

    @GetMapping(value = "/version/{version}", produces = "application/json")
    public CustomerDTO findByVersion(@PathVariable(value = "version") Integer version) {
        return getTestDto();
    }

    @PostMapping(value = "/create", produces = "application/json")
    public @ResponseBody CustomerDTO create(@RequestBody @Valid CustomerDTO customer) {
        return getTestDto();
    }

    @PostMapping(value = "/update", produces = "application/json")
    public @ResponseBody CustomerDTO update(@RequestBody @Valid CustomerDTO customer) {
        return getTestDto();
    }

    @DeleteMapping(value ="/delete/{id}",  produces = "application/json")
    public Long deleteById(@PathVariable(value = "id") Long id){
        return 8L;
    }

    @DeleteMapping(value ="/delete/", produces = "application/json")
    public Long delete(@RequestBody @Valid CustomerDTO customer) {
        return 8L;
    }

     private CustomerDTO getTestDto(){
        CustomerDTO dto = new CustomerDTO();
        dto.setId(1L);
        dto.setVersion(1);
        dto.setFirstName("FirstName");
        dto.setLastName("LastName");
        return dto;
    }

}
