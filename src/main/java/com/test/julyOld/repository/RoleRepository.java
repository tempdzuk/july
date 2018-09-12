package com.test.julyOld.repository;

import com.test.julyOld.entity.Role;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface RoleRepository extends JpaRepository<Role, Long> {
    boolean existsByRoleName(String roleName);

    Role findByRoleName(String roleName);

    List<Role> findAll();
}
