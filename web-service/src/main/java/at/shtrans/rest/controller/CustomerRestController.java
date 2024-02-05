package at.shtrans.rest.controller;

import at.shtrans.dto.CustomerDTO;
import at.shtrans.exception.ServiceException;
import at.shtrans.service.CustomerService;
import jakarta.validation.Valid;
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

    @RequestMapping(value = "/", method = RequestMethod.GET, produces = "application/json")
    public ResponseEntity<List<CustomerDTO>> allCustomers() {

        List<CustomerDTO> resultList = customerService.findAll();

        return ResponseEntity
                .status(HttpStatus.OK)
                .body(resultList);
    }

    @GetMapping(value = "/{id}", produces = "application/json")
    public ResponseEntity<CustomerDTO> findById(@PathVariable(value = "id") Long id) {

        try {
            CustomerDTO customer = customerService.findById(id);
            // Return the updated resource with a 200 (OK) status code
            return ResponseEntity
                    .status(HttpStatus.OK)
                    .body(customer);
        } catch (ServiceException e) {
            return ResponseEntity
                    .status(HttpStatus.NOT_FOUND)
                    .build();
        }
    }

    @GetMapping(value = "/firstName/{firstName}", produces = "application/json")
    public ResponseEntity<List<CustomerDTO>> findByFirstName(@PathVariable(value = "firstName") String firstName) {

        List<CustomerDTO> resultList = customerService.findByFirstName(firstName);

        return ResponseEntity
                .status(HttpStatus.OK)
                .body(resultList);
    }

    @GetMapping(value = "/lastName/{lastName}", produces = "application/json")
    public ResponseEntity<List<CustomerDTO>> findByLastName(@PathVariable(value = "lastName") String lastName) {

        List<CustomerDTO> resultList = customerService.findByLastName(lastName);

        return ResponseEntity
                .status(HttpStatus.OK)
                .body(resultList);
    }

    @GetMapping(value = "/version/{version}", produces = "application/json")
    public ResponseEntity<List<CustomerDTO>> findByVersion(@PathVariable(value = "version") Integer version) {

        List<CustomerDTO> resultList = customerService.findByVersion(version);

        return ResponseEntity
                .status(HttpStatus.OK)
                .body(resultList);
    }

    @PostMapping(value = "/create", produces = "application/json")
    public ResponseEntity<CustomerDTO> create(@Valid @RequestBody CustomerDTO customer) {

        CustomerDTO customerDTO = customerService.create(customer);
        // Return the created resource with a 201 (created) status code
        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(customerDTO);
    }

    @PutMapping(value = "/update", produces = "application/json")
    public ResponseEntity<CustomerDTO> update(@Valid @RequestBody CustomerDTO customer) {

        try {
            CustomerDTO customerDTO = customerService.update(customer);
            // Return the updated resource with a 200 (OK) status code
            return ResponseEntity
                    .status(HttpStatus.OK)
                    .body(customerDTO);
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
    public ResponseEntity<Long> delete(@Valid @RequestBody CustomerDTO customer) {

        try {
            Long deletedObjectId = customerService.delete(customer);
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
