package com.test.julyOld.repository;

import com.test.julyOld.entity.APIUserDetail;
import org.springframework.data.jpa.repository.JpaRepository;

public interface APIUserDetailRepository extends JpaRepository<APIUserDetail, String> {

    APIUserDetail findByUsernameAndDeletedFalse(String username);

    APIUserDetail findByUser_IdAndDeletedFalse(String userId);

}
