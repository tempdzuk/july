package com.test.julyOld.repository;

import com.test.julyOld.entity.Project;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface ProjectRepository extends CrudRepository<Project, Long> {

    boolean existsByNameAndDescription(String name, String description);

    boolean existsByName(String name);

    boolean existsById(Long id);

    Project getProjectEntityById(Long id);

    Project findByNameAndDescription(String name, String description);

    Project findProjectById(Long id);

    Project findByName(String name);

    List<Project> findAll();
}
