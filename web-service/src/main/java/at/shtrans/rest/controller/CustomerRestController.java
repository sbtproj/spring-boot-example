package at.shtrans.rest.controller;

import at.shtrans.dto.CustomerDTO;
import at.shtrans.rest.mapper.CustomerRequestResponseMapper;
import at.shtrans.rest.request.CustomerRequest;
import at.shtrans.rest.response.RestResponse;
import at.shtrans.service.CustomerService;
import jakarta.validation.Valid;
import org.mapstruct.factory.Mappers;
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

    private final CustomerRequestResponseMapper mapper
            = Mappers.getMapper(CustomerRequestResponseMapper.class);


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
    public @ResponseBody RestResponse create(@RequestBody @Valid CustomerRequest customerRequest) {

        CustomerDTO customerDTO = customerService.create(mapper.toDto(customerRequest));

        return mapper.toResponse(customerDTO);
    }

    @PostMapping(value = "/update", produces = "application/json")
    public @ResponseBody RestResponse update(@RequestBody @Valid CustomerRequest customerRequest) {

        CustomerDTO customerDTO = customerService.update(mapper.toDto(customerRequest));

        return mapper.toResponse(customerDTO);
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
