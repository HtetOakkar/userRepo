package com.test.main.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import com.test.main.model.dto.AddressDto;
import com.test.main.model.entity.Address;
import com.test.main.model.entity.User;
import com.test.main.model.mapper.AddressMapper;
import com.test.main.repository.AddressRepository;
import com.test.main.repository.UserRepository;
import com.test.main.security.UserPrincipal;

import lombok.AllArgsConstructor;

@AllArgsConstructor
@Service
public class AddressServiceImpl implements AddressService {

	private AddressMapper  addressMapper;
	
	private AddressRepository addressRepository;
	
	private UserRepository userRepository;
	
	@Override
	public AddressDto create(AddressDto addressDto) {
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		UserPrincipal userPrincipal = (UserPrincipal) auth.getPrincipal();
		Address address = addressMapper.toEntity(addressDto);
		User user = userRepository.getById(userPrincipal.getId());
		address.setUsers(user);
		Address savedAddress = addressRepository.save(address);
		AddressDto savedAddressDto = addressMapper.toDto(savedAddress);
		return savedAddressDto;
	}

	@Override
	public AddressDto update(Long id, AddressDto addressDto) {
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		UserPrincipal userPrincipal = (UserPrincipal) auth.getPrincipal();
		User user = userRepository.findById(userPrincipal.getId()).get();
		Address address = addressRepository.getById(id);
		address.setStreet(addressDto.getStreet());
		address.setCity(addressDto.getCity());
		address.setType(addressDto.getType());
		address.setUsers(user);
		Address savedAddress = addressRepository.save(address);
		AddressDto savedAddressDto = addressMapper.toDto(savedAddress);
		return savedAddressDto;
	}

	@Override
	public List<AddressDto> getAddresses() {
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		UserPrincipal userPrincipal = (UserPrincipal) auth.getPrincipal();
		List<AddressDto> addressDtoList = new ArrayList<>();
		User user = userRepository.getById(userPrincipal.getId());
		List<Address> addressList = user.getAddresses();
		for (Address address : addressList) {
			AddressDto addressDto = addressMapper.toDto(address);
			addressDtoList.add(addressDto);
		}
		return addressDtoList;
	}

	@Override
	public AddressDto getUserAddress(Long id) {
		Address address = addressRepository.findById(id).get();
		AddressDto addressDto = addressMapper.toDto(address);
		return addressDto;
	}

	@Override
	public void delete(Long id) {
		Address address = addressRepository.findById(id).get();
		addressRepository.delete(address);
	}

}
