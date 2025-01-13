package at.shtrans.service;

import at.shtrans.domain.Customer;
import at.shtrans.dto.CustomerDTO;
import at.shtrans.exception.ServiceException;
import at.shtrans.mapper.CustomerMapper;
import at.shtrans.repository.CustomerRepository;
import org.mapstruct.factory.Mappers;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

import static at.shtrans.ParameterChecker.checkParameterNonNull;

@Service
public class CustomerService {

    private final static Logger LOGGER = LoggerFactory.getLogger(CustomerService.class);

    @Autowired
    private CustomerRepository customerRepository;

    private final CustomerMapper customerMapper = Mappers.getMapper(CustomerMapper.class);

    public CustomerDTO create(CustomerDTO customerDTO) {
        LOGGER.info("BEGIN : create -> {}", customerDTO);
        checkParameterNonNull(customerDTO, "customerDTO");
        checkParameterNonNull(customerDTO.getFirstName(), "firstName");
        checkParameterNonNull(customerDTO.getLastName(), "lastName");

        Customer customer = customerRepository.saveAndFlush(customerMapper.toDomain(customerDTO));

        customerDTO = customerMapper.toDto(customer);

        LOGGER.info("END : create -> {}", customerDTO);
        return customerDTO;
    }

    public CustomerDTO update(CustomerDTO customerDTO) throws ServiceException {
        LOGGER.info("BEGIN : update -> {}", customerDTO);
        checkParameterNonNull(customerDTO, "customerDTO");
        checkParameterNonNull(customerDTO.getId(), "id");
        checkParameterNonNull(customerDTO.getFirstName(), "firstName");
        checkParameterNonNull(customerDTO.getLastName(), "lastName");

        if (!customerRepository.existsById(customerDTO.getId())) {
            throw new ServiceException("Customer", customerDTO.getId());
        }

        Optional<Customer> customerOptional = customerRepository.findById(customerDTO.getId());
        Customer customer
                = customerRepository.saveAndFlush(customerMapper.toDomain(customerDTO, customerOptional.get()));

        customerDTO = customerMapper.toDto(customer);

        LOGGER.info("BEGIN : update -> {}", customerDTO);
        return customerDTO;
    }

    public Long delete(CustomerDTO customerDTO) throws ServiceException {
        LOGGER.info("BEGIN : delete -> {}", customerDTO);
        checkParameterNonNull(customerDTO, "customerDTO");
        checkParameterNonNull(customerDTO.getId(), "id");

        if (!customerRepository.existsById(customerDTO.getId())) {
            throw new ServiceException("Customer", customerDTO.getId());
        }

        customerRepository.deleteById(customerDTO.getId());

        LOGGER.info("END : delete -> {}", customerDTO);
        return customerDTO.getId();
    }

    public Long deleteById(Long customerId) throws ServiceException {
        LOGGER.info("BEGIN : deleteById -> {}", customerId);
        checkParameterNonNull(customerId, "customerId");

        if (customerRepository.existsById(customerId)) {
            customerRepository.deleteById(customerId);
        } else {
            throw new ServiceException("Customer", customerId);
        }

        LOGGER.info("END : deleteById -> {}", customerId);
        return customerId;
    }

    public List<CustomerDTO> findAll() {
        LOGGER.info("BEGIN : findAll ");

        List<Customer> customerList = customerRepository.findAll();

        List<CustomerDTO> customerDTOList = customerMapper.toDtoList(customerList);

        LOGGER.info("BEGIN : findAll -> {}", customerDTOList);
        return customerDTOList;
    }

    public CustomerDTO findById(Long customerId) throws ServiceException {
        LOGGER.info("BEGIN : findById -> {}", customerId);
        checkParameterNonNull(customerId, "customerId");

        Optional<Customer> customerOptional = customerRepository.findById(customerId);

        if (customerOptional.isEmpty()) {
            throw new ServiceException("Customer", customerId);
        }

        CustomerDTO customerDTO = customerMapper.toDto(customerOptional.get());

        LOGGER.info("END : findById -> {}", customerDTO);
        return customerDTO;
    }

    public List<CustomerDTO> findByFirstName(String firstName) {
        LOGGER.info("BEGIN : findByFirstName -> {}", firstName);
        checkParameterNonNull(firstName, "firstName");

        List<Customer> customerList = customerRepository.findByFirstName(firstName);

        List<CustomerDTO> customerDTOList = customerMapper.toDtoList(customerList);

        LOGGER.info("END : findByFirstName -> {}", customerDTOList);
        return customerDTOList;
    }

    public List<CustomerDTO> findByLastName(String lastName) {
        LOGGER.info("BEGIN : findByLastName -> {}", lastName);
        checkParameterNonNull(lastName, "lastName");

        List<Customer> customerList = customerRepository.findByLastName(lastName);

        List<CustomerDTO> customerDTOList = customerMapper.toDtoList(customerList);

        LOGGER.info("END : findByLastName -> {}", customerDTOList);
        return customerDTOList;
    }

    public List<CustomerDTO> findByVersion(Integer version) {
        LOGGER.info("BEGIN : findByVersion -> {}", version);
        checkParameterNonNull(version, "version");

        List<Customer> customerList = customerRepository.findByVersion(version);

        List<CustomerDTO> customerDTOList = customerMapper.toDtoList(customerList);

        LOGGER.info("END : findByVersion -> {}", customerDTOList);
        return customerDTOList;
    }

}
