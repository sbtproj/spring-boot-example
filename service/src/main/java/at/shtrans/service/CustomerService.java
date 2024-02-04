package at.shtrans.service;

import at.shtrans.domain.Customer;
import at.shtrans.dto.CustomerDTO;
import at.shtrans.mapper.CustomerMapper;
import at.shtrans.repository.CustomerRepository;
import org.mapstruct.factory.Mappers;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CustomerService {

    @Autowired
    private CustomerRepository customerRepository;

    private final CustomerMapper customerMapper = Mappers.getMapper(CustomerMapper.class);


    public CustomerDTO create(CustomerDTO customerDTO){

        Customer customer = customerRepository.saveAndFlush(customerMapper.toDomain(customerDTO));

        return customerMapper.toDto(customer);
    }

    public CustomerDTO update(CustomerDTO customerDTO){

        if(customerRepository.existsById(customerDTO.getId())){

        }

        Customer customer = customerRepository.saveAndFlush(customerMapper.toDomain(customerDTO));

        return customerMapper.toDto(customer);
    }

    public Long delete(CustomerDTO customerDTO){

        if(customerRepository.existsById(customerDTO.getId())){
            customerRepository.deleteById(customerDTO.getId());
        }

        return customerDTO.getId();
    }

    public Long deleteById(Long customerId){

        if(customerRepository.existsById(customerId)){
            customerRepository.deleteById(customerId);
        }

        return customerId;
    }

    public List<CustomerDTO> findAll(){

        List<Customer> customerList = customerRepository.findAll();

        return customerMapper.toDtoList(customerList);
    }
}
