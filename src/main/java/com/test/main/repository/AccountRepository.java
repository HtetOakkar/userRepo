package com.test.main.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.test.main.model.entity.Account;

@Repository
public interface AccountRepository extends JpaRepository<Account, Long> {

}
