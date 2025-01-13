package at.shtrans.rest.mapper;

import at.shtrans.dto.CustomerDTO;
import at.shtrans.rest.controller.CustomerResource;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper
public interface CustomerRequestResponseMapper {

    CustomerResource toResource(CustomerDTO customerDTO);

    CustomerDTO toDto(CustomerResource customerResource);

    List<CustomerResource> toResourceList(List<CustomerDTO> customerDTOList);

}
