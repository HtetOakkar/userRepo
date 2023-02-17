package com.test.main.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.test.main.model.entity.User;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
	User getUserDetialsById(Long id);

	@Query(value = "SELECT u FROM User u WHERE u.username = :username")
	Optional<User> getUserByUserName(String username);

}
