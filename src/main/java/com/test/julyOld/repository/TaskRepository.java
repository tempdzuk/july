package com.test.julyOld.repository;

import com.test.julyOld.entity.Task;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface TaskRepository extends JpaRepository<Task, String> {
    List<Task> findAllByUser_Id(String id);
}
