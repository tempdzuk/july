package com.test.julyOld.repository;

import com.test.julyOld.entity.Project;
import com.test.julyOld.entity.Task;
import com.test.julyOld.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface TaskRepository extends JpaRepository<Task, Long> {

    boolean existsById(Long id);

    boolean existsByProject_Id(Long id);

    boolean existsByUser_Id(Long id);

    List<Task> findAll();

    List<Task> findAllByProject_Id(Long projectId);

    List<Task> findAllByUser_Id(Long id);


}
