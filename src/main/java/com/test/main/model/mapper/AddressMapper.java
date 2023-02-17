package com.test.main.model.mapper;

import com.test.main.model.dto.AddressDto;
import com.test.main.model.entity.Address;
import com.test.main.model.request.AddressRequest;
import com.test.main.model.rsponse.AddressResponse;

public interface AddressMapper {

	AddressDto toDto(AddressRequest request);

	AddressResponse toResponse(AddressDto addressDto);

	Address toEntity(AddressDto addressDto);

	AddressDto toDto(Address address);
	
}
