package com.test.julyOld.repository;

import com.test.julyOld.entity.Role;
import com.test.julyOld.entity.User;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface UserRepository extends CrudRepository<User, Long> {

    List<User> findAll();

    boolean existsByUserName(String userName);

    User findByUserName(String userName);

    List<User> findAllByRole(Role role);

//    Iterable<User> findAll();
}