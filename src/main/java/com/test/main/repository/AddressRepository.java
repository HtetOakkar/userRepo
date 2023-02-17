package com.test.main.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.test.main.model.entity.Address;

public interface AddressRepository extends JpaRepository<Address, Long> {
	
	

}
