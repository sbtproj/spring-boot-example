package at.shtrans.rest.controller;

import at.shtrans.dto.CustomerDTO;
import at.shtrans.exception.ServiceException;
import at.shtrans.rest.mapper.CustomerRequestResponseMapper;
import at.shtrans.service.CustomerService;
import jakarta.validation.Valid;
import org.mapstruct.factory.Mappers;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Objects;


@RestController
@RequestMapping(value = "/customer")
public class CustomerRestController {

    @Autowired
    private CustomerService customerService;

    private final CustomerRequestResponseMapper mapper
            = Mappers.getMapper(CustomerRequestResponseMapper.class);


    @RequestMapping(value = "/", method = RequestMethod.GET, produces = "application/json")
    public ResponseEntity<List<CustomerResource>> allCustomers(@RequestParam("role") String role) {

        List<CustomerDTO> resultList = customerService.findAll();

        return ResponseEntity
                .status(HttpStatus.OK)
                .body(mapper.toResourceList(resultList));
    }

    @GetMapping(value = "/{id}", produces = "application/json", params = {"role"} )
    public ResponseEntity<CustomerResource> findById(@PathVariable(value = "id") Long id, @RequestParam("role") String role){

        try {
            CustomerDTO customer = customerService.findById(id);
            // Return the updated resource with a 200 (OK) status
            return ResponseEntity
                    .status(HttpStatus.OK)
                    .body(mapper.toResource(customer));
        } catch (ServiceException e){
            return ResponseEntity
                    .status(HttpStatus.NOT_FOUND)
                    .build();
        }
    }

    @GetMapping(value = "/firstName/{firstName}", produces = "application/json")
    public ResponseEntity<List<CustomerResource>> findByFirstName(@PathVariable(value = "firstName") String firstName, @RequestParam("role") String role){

        List<CustomerDTO> resultList = customerService.findByFirstName(firstName);

        return ResponseEntity
                .status(HttpStatus.OK)
                .body(mapper.toResourceList(resultList));
    }

    @GetMapping(value = "/lastName/{lastName}", produces = "application/json")
    public ResponseEntity<List<CustomerResource>> findByLastName(@PathVariable(value = "lastName") String lastName, @RequestParam("role") String role){

        List<CustomerDTO> resultList = customerService.findByLastName(lastName);

        return ResponseEntity
                .status(HttpStatus.OK)
                .body(mapper.toResourceList(resultList));
    }

    @GetMapping(value = "/version/{version}", produces = "application/json", params = {"role"} )
    public ResponseEntity<List<CustomerResource>> findByVersion(@PathVariable(value = "version") Integer version, @RequestParam("role") String role){

        List<CustomerDTO> resultList = customerService.findByVersion(version);

        return ResponseEntity
                .status(HttpStatus.OK)
                .body(mapper.toResourceList(resultList));
    }

    @PostMapping(value = "/create", produces = "application/json")
    public ResponseEntity<CustomerResource> create(@Valid @RequestBody CustomerResource customerResource, @RequestParam("role") String role){

        CustomerDTO customerDTO = customerService.create(mapper.toDto(customerResource));
        // Return the created resource with a 201 (created) status code
        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(mapper.toResource(customerDTO));
    }

    @PutMapping(value = "/update", produces = "application/json")
    public ResponseEntity<CustomerResource> update(@Valid @RequestBody CustomerResource customerResource, @RequestParam("role") String role){

        try {
            CustomerDTO customerDTO = customerService.update(mapper.toDto(customerResource));
            // Return the updated resource with a 200 (OK) status code
            return ResponseEntity
                    .status(HttpStatus.OK)
                    .body(mapper.toResource(customerDTO));
        } catch (ServiceException e){
            return ResponseEntity
                    .status(HttpStatus.NOT_FOUND)
                    .build();
        }
    }

    @DeleteMapping(value = "/delete/{id}", produces = "application/json")
    public ResponseEntity<Long> deleteById(@PathVariable(value = "id") Long id, @RequestParam("role") String role){

        try {
            Long deletedObjectId = customerService.deleteById(id);
            // Return a 204 (no content) status code
            return ResponseEntity
                    .status(HttpStatus.NO_CONTENT)
                    .body(deletedObjectId);
        } catch (ServiceException e){
            return ResponseEntity
                    .status(HttpStatus.NOT_FOUND)
                    .build();
        }

    }

    @DeleteMapping(value = "/delete/", produces = "application/json")
    public ResponseEntity<Long> delete(@Valid @RequestBody CustomerResource customerResource, @RequestParam("role") String role){

        try {
            Long deletedObjectId = customerService.delete(mapper.toDto(customerResource));
            // Return a 204 (no content) status code
            return ResponseEntity
                    .status(HttpStatus.NO_CONTENT)
                    .body(deletedObjectId);
        } catch (ServiceException e){
            return ResponseEntity
                    .status(HttpStatus.NOT_FOUND)
                    .build();
        }
    }

    private boolean checkRole(String expectedRole,  String queryParamRole){
        Objects.requireNonNull(expectedRole, "Parameter with name [" + expectedRole + "] cannot be NULL!");
       // new ResponseEntity<>(HttpStatus.METHOD_NOT_ALLOWED);
        return expectedRole.equals(queryParamRole);
    }

}
