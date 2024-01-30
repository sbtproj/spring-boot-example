package at.shtrans.mapper;

import at.shtrans.domain.Customer;
import at.shtrans.dto.CustomerDTO;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper
public interface CustomerMapper {

    CustomerDTO toDto(Customer customer);

    List<CustomerDTO> toDtoList(List<Customer> customerList);

}
