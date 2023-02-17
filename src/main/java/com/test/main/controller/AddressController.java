package com.test.main.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.test.main.model.dto.AddressDto;
import com.test.main.model.mapper.AddressMapper;
import com.test.main.model.request.AddressRequest;
import com.test.main.model.rsponse.AddressResponse;
import com.test.main.service.AddressService;

import lombok.AllArgsConstructor;

@AllArgsConstructor
@RestController
@RequestMapping(path = "/api")
public class AddressController {

	private AddressMapper addressMapper;

	private AddressService addressService;

	@PostMapping(path = "users/address/create")
	@PreAuthorize("hasRole('ROLE_USER')")
	public AddressResponse createAddress(@RequestBody AddressRequest request) {
		AddressDto addressDto = addressMapper.toDto(request);
		AddressDto createdAddress = addressService.create(addressDto);
		AddressResponse response = addressMapper.toResponse(createdAddress);
		return response;
	}

	@PutMapping(path = "users/address/{id}/update")
	@PreAuthorize("hasRole('ROLE_USER')")
	public AddressResponse updateAddress(@PathVariable Long id, @RequestBody AddressRequest request) {
		AddressDto addressDto = addressMapper.toDto(request);
		AddressDto createdAddress = addressService.update(id, addressDto);
		AddressResponse response = addressMapper.toResponse(createdAddress);
		return response;
	}
	
	@GetMapping(path= "users/address/get")
	@PreAuthorize("hasRole('ROLE_USER')")
	public List<AddressResponse> getUserAddresses() {
		List<AddressResponse> addresses = new ArrayList<>();
		List<AddressDto> addressDtos = addressService.getAddresses();
		for (AddressDto addressDto : addressDtos) {
			AddressResponse response = addressMapper.toResponse(addressDto);
			addresses.add(response);
		}
		return addresses;
	}
	
	@GetMapping(path="users/address/{id}/get")
	@PreAuthorize("hasRole('ROLE_USER')")
	public AddressResponse getAddress(@PathVariable Long id) {
		AddressDto addressDto = addressService.getUserAddress(id);
		AddressResponse response = addressMapper.toResponse(addressDto);
		return response;
	}
	
	@DeleteMapping(path="users/address/{id}/delete")
	@PreAuthorize("hasRole('ROLE_USER')")
	public ResponseEntity<?> deleteAddress(@PathVariable Long id) {
		addressService.delete(id);
		return new ResponseEntity<>("Adress successfully deleted!", HttpStatus.OK);
	}
}
