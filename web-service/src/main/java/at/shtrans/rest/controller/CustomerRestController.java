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
    public ResponseEntity<List<CustomerResource>> allCustomers() {

        List<CustomerDTO> resultList = customerService.findAll();

        return ResponseEntity
                .status(HttpStatus.OK)
                .body(mapper.toResourceList(resultList));
    }

    @GetMapping(value = "/{id}", produces = "application/json")
    public ResponseEntity<CustomerResource> findById(@PathVariable(value = "id") Long id) {

        try {
            CustomerDTO customer = customerService.findById(id);
            // Return the updated resource with a 200 (OK) status
            return ResponseEntity
                    .status(HttpStatus.OK)
                    .body(mapper.toResource(customer));
        } catch (ServiceException e) {
            return ResponseEntity
                    .status(HttpStatus.NOT_FOUND)
                    .build();
        }
    }

    @GetMapping(value = "/firstName/{firstName}", produces = "application/json")
    public ResponseEntity<List<CustomerResource>> findByFirstName(@PathVariable(value = "firstName") String firstName) {

        List<CustomerDTO> resultList = customerService.findByFirstName(firstName);

        return ResponseEntity
                .status(HttpStatus.OK)
                .body(mapper.toResourceList(resultList));
    }

    @GetMapping(value = "/lastName/{lastName}", produces = "application/json")
    public ResponseEntity<List<CustomerResource>> findByLastName(@PathVariable(value = "lastName") String lastName) {

        List<CustomerDTO> resultList = customerService.findByLastName(lastName);

        return ResponseEntity
                .status(HttpStatus.OK)
                .body(mapper.toResourceList(resultList));
    }

    @GetMapping(value = "/version/{version}", produces = "application/json")
    public ResponseEntity<List<CustomerResource>> findByVersion(@PathVariable(value = "version") Integer version) {

        List<CustomerDTO> resultList = customerService.findByVersion(version);

        return ResponseEntity
                .status(HttpStatus.OK)
                .body(mapper.toResourceList(resultList));
    }

    @PostMapping(value = "/create", produces = "application/json")
    public ResponseEntity<CustomerResource> create(@Valid @RequestBody CustomerDTO customer) {

        CustomerDTO customerDTO = customerService.create(customer);
        // Return the created resource with a 201 (created) status code
        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(mapper.toResource(customer));
    }

    @PutMapping(value = "/update", produces = "application/json")
    public ResponseEntity<CustomerResource> update(@Valid @RequestBody CustomerResource customerResource) {

        try {
            CustomerDTO customerDTO = customerService.update(mapper.toDto(customerResource));
            // Return the updated resource with a 200 (OK) status code
            return ResponseEntity
                    .status(HttpStatus.OK)
                    .body(mapper.toResource(customerDTO));
        } catch (ServiceException e) {
            return ResponseEntity
                    .status(HttpStatus.NOT_FOUND)
                    .build();
        }
    }

    @DeleteMapping(value = "/delete/{id}", produces = "application/json")
    public ResponseEntity<Long> deleteById(@PathVariable(value = "id") Long id) {

        try {
            Long deletedObjectId = customerService.deleteById(id);
            // Return a 204 (no content) status code
            return ResponseEntity
                    .status(HttpStatus.NO_CONTENT)
                    .body(deletedObjectId);
        } catch (ServiceException e) {
            return ResponseEntity
                    .status(HttpStatus.NOT_FOUND)
                    .build();
        }

    }

    @DeleteMapping(value = "/delete/", produces = "application/json")
    public ResponseEntity<Long> delete(@Valid @RequestBody CustomerResource customerResource) {

        try {
            Long deletedObjectId = customerService.delete(mapper.toDto(customerResource));
            // Return a 204 (no content) status code
            return ResponseEntity
                    .status(HttpStatus.NO_CONTENT)
                    .body(deletedObjectId);
        } catch (ServiceException e) {
            return ResponseEntity
                    .status(HttpStatus.NOT_FOUND)
                    .build();
        }
    }

}
