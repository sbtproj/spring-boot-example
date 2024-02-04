package at.shtrans.rest.mapper;

import at.shtrans.dto.CustomerDTO;
import at.shtrans.rest.request.CustomerRequest;
import at.shtrans.rest.response.CustomerResponse;
import org.mapstruct.Mapper;

@Mapper
public interface CustomerRequestResponseMapper {

    CustomerResponse toResponse(CustomerDTO customerDTO);

    CustomerDTO toDto(CustomerRequest customerRequest);

}
