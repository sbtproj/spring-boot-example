package at.shtrans.mapper;

import at.shtrans.domain.Customer;
import at.shtrans.dto.CustomerDTO;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;

import java.util.List;

@Mapper
public interface CustomerMapper {

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "version", ignore = true)
    Customer toDomain(CustomerDTO customerDTO);

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "version", ignore = true)
    Customer toDomain(CustomerDTO customerDTO, @MappingTarget Customer customer);

    CustomerDTO toDto(Customer customer);

    List<CustomerDTO> toDtoList(List<Customer> customerList);

}
