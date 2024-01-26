package at.shtrans.service;

import at.shtrans.dto.CustomerDTO;
import at.shtrans.repository.CustomerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CustomerService {

    @Autowired
    private CustomerRepository customerRepository;

    public CustomerDTO save(CustomerDTO customerDTO){
        //throw new NotImplementedException("save");
        return null;
    }

    public CustomerDTO update(CustomerDTO customerDTO){
        //throw new NotImplementedException("update");
        return null;
    }

    public Long delete(CustomerDTO customerDTO){
        //throw new NotImplementedException("delete");
        return null;
    }

    public Long deleteById(Long customerId){
        //throw new NotImplementedException("customerId");
        return null;
    }
}
