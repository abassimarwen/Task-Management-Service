package com.example.TaskManagementService.repository;

import com.example.TaskManagementService.domain.entity.Sprint;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SprintRepository extends JpaRepository<String, Sprint> {
}
