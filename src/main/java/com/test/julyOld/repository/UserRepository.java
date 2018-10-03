package com.test.julyOld.repository;

import com.test.julyOld.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, String> {

}
