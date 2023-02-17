package com.test.main.service;

import java.util.List;

import com.test.main.model.dto.AddressDto;

public interface AddressService {

	AddressDto create(AddressDto addressDto);

	AddressDto update(Long id,AddressDto addressDto);

	List<AddressDto> getAddresses();

	AddressDto getUserAddress(Long id);

	void delete(Long id);

}
