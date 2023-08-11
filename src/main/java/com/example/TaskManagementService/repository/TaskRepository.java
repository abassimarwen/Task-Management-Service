package com.example.TaskManagementService.repository;

import com.example.TaskManagementService.domain.entity.Task;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TaskRepository extends JpaRepository<String, Task> {
}
