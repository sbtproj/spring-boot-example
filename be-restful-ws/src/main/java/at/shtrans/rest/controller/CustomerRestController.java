package at.shtrans.rest.controller;

import at.shtrans.dto.CustomerDTO;
import at.shtrans.exception.ServiceException;
import at.shtrans.rest.mapper.CustomerRequestResponseMapper;
import at.shtrans.service.CustomerService;
import io.swagger.v3.oas.annotations.Parameter;
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
import java.util.Objects;


@RestController
@RequestMapping(value = "/customer")
public class CustomerRestController {

    @Autowired
    private CustomerService customerService;

    private final CustomerRequestResponseMapper mapper
            = Mappers.getMapper(CustomerRequestResponseMapper.class);

    @Parameter(name = "role", required = true, example = "ADMIN, ADVISOR", description = "Just add one Role")
    @RequestMapping(value = "/", method = RequestMethod.GET, produces = "application/json")
    public ResponseEntity<List<CustomerResource>> allCustomers() {

        List<CustomerDTO> resultList = customerService.findAll();

        return ResponseEntity
                .status(HttpStatus.OK)
                .body(mapper.toResourceList(resultList));
    }

    @Parameter(name = "role", required = true, example = "ADMIN, ADVISOR", description = "Just add one Role")
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

    @Parameter(name = "role", required = true, example = "ADMIN, ADVISOR", description = "Just add one Role")
    @GetMapping(value = "/firstName/{firstName}", produces = "application/json")
    public ResponseEntity<List<CustomerResource>> findByFirstName(@PathVariable(value = "firstName") String firstName) {

        List<CustomerDTO> resultList = customerService.findByFirstName(firstName);

        return ResponseEntity
                .status(HttpStatus.OK)
                .body(mapper.toResourceList(resultList));
    }

    @Parameter(name = "role", required = true, example = "ADMIN, ADVISOR", description = "Just add one Role")
    @GetMapping(value = "/lastName/{lastName}", produces = "application/json")
    public ResponseEntity<List<CustomerResource>> findByLastName(@PathVariable(value = "lastName") String lastName) {

        List<CustomerDTO> resultList = customerService.findByLastName(lastName);

        return ResponseEntity
                .status(HttpStatus.OK)
                .body(mapper.toResourceList(resultList));
    }

    @Parameter(name = "role", required = true, example = "ADMIN, ADVISOR", description = "Just add one Role")
    @GetMapping(value = "/version/{version}", produces = "application/json")
    public ResponseEntity<List<CustomerResource>> findByVersion(@PathVariable(value = "version") Integer version) {

        List<CustomerDTO> resultList = customerService.findByVersion(version);

        return ResponseEntity
                .status(HttpStatus.OK)
                .body(mapper.toResourceList(resultList));
    }

    @Parameter(name = "role", required = true, example = "ADMIN", description = "Just add one Role")
    @PostMapping(value = "/create", produces = "application/json")
    public ResponseEntity<CustomerResource> create(@Valid @RequestBody CustomerResource customerResource) {

        CustomerDTO customerDTO = customerService.create(mapper.toDto(customerResource));
        // Return the created resource with a 201 (created) status code
        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(mapper.toResource(customerDTO));
    }

    @Parameter(name = "role", required = true, example = "ADMIN", description = "Just add one Role")
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

    @Parameter(name = "role", required = true, example = "ADMIN", description = "Just add one Role")
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

    @Parameter(name = "role", required = true, example = "ADMIN", description = "Just add one Role")
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

    private boolean checkRole(String expectedRole, String queryParamRole) {
        Objects.requireNonNull(expectedRole, "Parameter with name [" + expectedRole + "] cannot be NULL!");
        // new ResponseEntity<>(HttpStatus.METHOD_NOT_ALLOWED);
        return expectedRole.equals(queryParamRole);
    }

}
