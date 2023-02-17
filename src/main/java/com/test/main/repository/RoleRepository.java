package com.test.main.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.test.main.model.entity.Role;

@Repository
public interface RoleRepository extends JpaRepository<Role, Long> {

	Role getRoleDetailById(Long id);
	Role findByRoleName(String roleName);
	
	@Query(value = "SELECT r FROM Role r WHERE r.roleName=:roleName")
	Optional<Role> findByrName(String roleName);
}
