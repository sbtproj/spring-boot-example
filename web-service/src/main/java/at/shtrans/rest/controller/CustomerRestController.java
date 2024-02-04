package at.shtrans.rest.controller;

import at.shtrans.dto.CustomerDTO;
import at.shtrans.rest.mapper.CustomerRequestResponseMapper;
import at.shtrans.rest.request.CustomerRequest;
import at.shtrans.rest.response.CustomerResponse;
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
    public List<CustomerResponse> allCustomers() {

        List<CustomerDTO> resultList = customerService.findAll();

        return mapper.toResponseList(resultList);
    }

    @GetMapping(value = "/{id}", produces = "application/json")
    public CustomerResponse findById(@PathVariable(value = "id") Long id) {

        CustomerDTO result= customerService.findById(id);

        return mapper.toResponse(result);
    }

    @GetMapping(value = "/firstName/{firstName}", produces = "application/json")
    public List<CustomerResponse> findByFirstName(@PathVariable(value = "firstName") String firstName) {

        List<CustomerDTO> resultList = customerService.findByFirstName(firstName);

        return mapper.toResponseList(resultList);
    }

    @GetMapping(value = "/lastName/{lastName}", produces = "application/json")
    public List<CustomerResponse> findByLastName(@PathVariable(value = "lastName") String lastName) {

        List<CustomerDTO> resultList = customerService.findByLastName(lastName);

        return mapper.toResponseList(resultList);
    }

    @GetMapping(value = "/version/{version}", produces = "application/json")
    public List<CustomerResponse> findByVersion(@PathVariable(value = "version") Integer version) {

        List<CustomerDTO> resultList = customerService.findByVersion(version);

        return mapper.toResponseList(resultList);
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
        return customerService.deleteById(id);
    }

    @DeleteMapping(value ="/delete/", produces = "application/json")
    public Long delete(@RequestBody @Valid CustomerRequest customerRequest) {
        return customerService.delete(mapper.toDto(customerRequest));
    }

}
