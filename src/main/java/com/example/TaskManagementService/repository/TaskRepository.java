package com.example.TaskManagementService.repository;

import com.example.TaskManagementService.domain.entity.Task;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Set;

@Repository
public interface TaskRepository extends JpaRepository<Task, String> {
    @Query("SELECT t from Task t where t.isDeleted = false ")
    Set<Task> getAllUndeletedTasks();
}
