package at.shtrans.rest.mapper;

import at.shtrans.domain.Customer;
import at.shtrans.dto.CustomerDTO;
import at.shtrans.rest.request.CustomerRequest;
import at.shtrans.rest.response.CustomerResponse;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper
public interface CustomerRequestResponseMapper {

    CustomerResponse toResponse(CustomerDTO customerDTO);

    CustomerDTO toDto(CustomerRequest customerRequest);

    List<CustomerResponse> toResponseList(List<CustomerDTO> customerDTOList);

}
