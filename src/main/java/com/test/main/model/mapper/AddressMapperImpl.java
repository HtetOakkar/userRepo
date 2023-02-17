package com.test.main.model.mapper;

import org.springframework.stereotype.Component;

import com.test.main.model.dto.AddressDto;
import com.test.main.model.entity.Address;
import com.test.main.model.request.AddressRequest;
import com.test.main.model.rsponse.AddressResponse;

@Component
public class AddressMapperImpl implements AddressMapper{

	@Override
	public AddressDto toDto(AddressRequest request) {
		AddressDto addressDto = new AddressDto();
		addressDto.setCity(request.getCity());
		addressDto.setStreet(request.getStreet());
		addressDto.setType(request.getType());
		return addressDto;
	}

	@Override
	public AddressResponse toResponse(AddressDto addressDto) {
		AddressResponse response = new AddressResponse();
		response.setId(addressDto.getId());
		response.setStreet(addressDto.getStreet());
		response.setCity(addressDto.getCity());
		response.setType(addressDto.getType());
		return response;
	}

	@Override
	public Address toEntity(AddressDto addressDto) {
		Address address = new Address();
		address.setId(addressDto.getId());
		address.setStreet(addressDto.getStreet());
		address.setCity(addressDto.getCity());
		address.setType(addressDto.getType());
		return address;
	}

	@Override
	public AddressDto toDto(Address address) {
		AddressDto addressDto = new AddressDto();
		addressDto.setId(address.getId());
		addressDto.setCity(address.getCity());
		addressDto.setStreet(address.getStreet());
		addressDto.setType(address.getType());
		return addressDto;
	}

}
