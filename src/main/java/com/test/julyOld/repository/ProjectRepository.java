package com.test.julyOld.repository;

import com.test.julyOld.entity.Project;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface ProjectRepository extends JpaRepository<Project, Long> {

    boolean existsByName(String name);

    boolean existsById(Long id);

    Project findByName(String name);

    List<Project> findAll();
}
