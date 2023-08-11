package com.example.TaskManagementService.repository;

import com.example.TaskManagementService.domain.entity.Sprint;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Set;

@Repository
public interface SprintRepository extends JpaRepository<Sprint, String> {
    @Query("SELECT s from Sprint s where s.isDeleted = false ")
    Set<Sprint> getAllUndeletedSprints();
}
