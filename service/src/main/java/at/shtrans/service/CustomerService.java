package at.shtrans.service;

import at.shtrans.domain.Customer;
import at.shtrans.dto.CustomerDTO;
import at.shtrans.mapper.CustomerMapper;
import at.shtrans.repository.CustomerRepository;
import org.mapstruct.factory.Mappers;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

import static at.shtrans.ParameterChecker.checkParameterNonNull;

@Service
public class CustomerService {


    @Autowired
    private CustomerRepository customerRepository;

    private final CustomerMapper customerMapper = Mappers.getMapper(CustomerMapper.class);

    public CustomerDTO create(CustomerDTO customerDTO) {
        checkParameterNonNull(customerDTO, "customerDTO");
        checkParameterNonNull(customerDTO.getFirstName(), "firstName");
        checkParameterNonNull(customerDTO.getLastName(), "lastName");

        Customer customer = customerRepository.saveAndFlush(customerMapper.toDomain(customerDTO));

        return customerMapper.toDto(customer);
    }

    public CustomerDTO update(CustomerDTO customerDTO) {
        checkParameterNonNull(customerDTO, "customerDTO");
        checkParameterNonNull(customerDTO.getId(), "id");
        checkParameterNonNull(customerDTO.getFirstName(), "firstName");
        checkParameterNonNull(customerDTO.getLastName(), "lastName");

        if (customerRepository.existsById(customerDTO.getId())) {

            Optional<Customer> customerOptional = customerRepository.findById(customerDTO.getId());
            Customer customer
                    = customerRepository.saveAndFlush(customerMapper.toDomain(customerDTO, customerOptional.get()));

            return customerMapper.toDto(customer);
        }

        return null;
    }

    public Long delete(CustomerDTO customerDTO) {
        checkParameterNonNull(customerDTO, "customerDTO");
        checkParameterNonNull(customerDTO.getId(), "id");

        if (customerRepository.existsById(customerDTO.getId())) {
            customerRepository.deleteById(customerDTO.getId());
        }

        return customerDTO.getId();
    }

    public Long deleteById(Long customerId) {
        checkParameterNonNull(customerId, "customerId");

        if (customerRepository.existsById(customerId)) {
            customerRepository.deleteById(customerId);
        }

        return customerId;
    }

    public List<CustomerDTO> findAll() {

        List<Customer> customerList = customerRepository.findAll();

        return customerMapper.toDtoList(customerList);
    }

    public List<CustomerDTO> findByFirstName(String firstName) {
        checkParameterNonNull(firstName, "firstName");

        List<Customer> customerList = customerRepository.findByFirstName(firstName);

        return customerMapper.toDtoList(customerList);
    }

    public List<CustomerDTO> findByLastName(String lastName) {
        checkParameterNonNull(lastName, "lastName");

        List<Customer> customerList = customerRepository.findByLastName(lastName);

        return customerMapper.toDtoList(customerList);
    }

    public List<CustomerDTO> findByVersion(Integer version) {
        checkParameterNonNull(version, "version");

        List<Customer> customerList = customerRepository.findByVersion(version);

        return customerMapper.toDtoList(customerList);
    }

}
