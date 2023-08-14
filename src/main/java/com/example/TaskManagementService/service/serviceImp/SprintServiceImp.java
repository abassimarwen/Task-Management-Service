package com.example.TaskManagementService.service.serviceImp;

import com.example.TaskManagementService.domain.dto.SprintDto;
import com.example.TaskManagementService.domain.dto.TaskDto;
import com.example.TaskManagementService.domain.entity.Sprint;
import com.example.TaskManagementService.domain.enums.SprintStatus;
import com.example.TaskManagementService.domain.mapper.SprintMapper;
import com.example.TaskManagementService.domain.mapper.TaskMapper;
import com.example.TaskManagementService.repository.SprintRepository;
import com.example.TaskManagementService.service.IService.ISprintService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.*;
import java.util.concurrent.atomic.AtomicReference;

@Service
public class SprintServiceImp implements ISprintService {
    private final SprintRepository sprintRepository;
    private final SprintMapper sprintMapper;
    private final TaskMapper taskMapper;
    @Autowired
    public SprintServiceImp(SprintRepository sprintRepository, SprintMapper sprintMapper,TaskMapper taskMapper) {
        this.sprintRepository = sprintRepository;
        this.sprintMapper = sprintMapper;
        this.taskMapper = taskMapper;
    }

    @Override
    public Sprint createSprint(SprintDto sprintDto) {
        return this.sprintRepository.save(this.sprintMapper.toEntity(sprintDto));
    }

    @Override
    public void fillSprint(TaskDto taskDto, String sprintId) {
        Optional<Sprint> sprintToFill = this.sprintRepository.findById(sprintId);
        if(sprintToFill.isPresent()){
            Float sprintDuration = this.getSprintDurationPerHours(sprintToFill.get());
            if(sprintDuration > 0.0){
                sprintToFill.get().getTasks().add(this.taskMapper.toEntity(taskDto));
                this.sprintRepository.save(sprintToFill.get());
            }
        }
    }

    @Override
    public void deleteSprint(String sprintId) {
       Optional<Sprint> sprintToDelete = this.sprintRepository.findById(sprintId);
       if(sprintToDelete.isPresent()){
           sprintToDelete.get().setDeleted(true);
           sprintToDelete.get().setDeleted_at(Timestamp.valueOf(LocalDateTime.now()));
           this.sprintRepository.save(sprintToDelete.get());
       }
    }

    @Override
    public void updateSprint(String sprintId, SprintDto sprintDto) {
        Optional<Sprint> sprintToUpdate = this.sprintRepository.findById(sprintId);
        if(sprintToUpdate.isPresent()){
            sprintToUpdate.get().setModified_at(Timestamp.valueOf(LocalDateTime.now()));
        this.sprintRepository.save(this.sprintMapper.toExistingEntity(sprintDto,sprintToUpdate.get()));
        }
    }

    @Override
    public SprintDto getSprint(String sprintId) {
        return this.sprintMapper.toDto(this.sprintRepository.findById(sprintId).orElseThrow());
    }

    @Override
    public Set<SprintDto> getSprints() {
        Set<SprintDto> sprintsDto = new HashSet<>();
        this.sprintRepository.findAll()
                .forEach(sprint ->
                {sprintsDto
                        .add(this.sprintMapper
                                .toDto(sprint));
                });
        return sprintsDto;
    }

    @Override
    public Set<SprintDto> getUndeletedSprints() {
        Set<SprintDto> undeletedSprintsDto = new HashSet<>();
         this.sprintRepository.getAllUndeletedSprints().forEach(sprint -> {
             undeletedSprintsDto.add(this.sprintMapper.toDto(sprint));
         });
         return undeletedSprintsDto;
    }

    @Override
    public void updateSprintStatus(SprintStatus sprintStatus, String sprintId) {
        Optional<Sprint> sprint = this.sprintRepository.findById(sprintId);
        if(sprint.isPresent()){
            sprint.get().setModified_at(Timestamp.valueOf(LocalDateTime.now()));
            sprint.get().setSprintStatus(sprintStatus);
            this.sprintRepository.save(sprint.get());
        }

    }

    public Float getSprintDurationPerHours(Sprint sprint){
        int hours = sprint.getEndDate().getHours() - sprint.getStartDate().getHours();
        float minutes = (float) (sprint.getEndDate().getMinutes() - sprint.getStartDate().getMinutes() - (hours/60)) / 60;
        AtomicReference<Float> duration = new AtomicReference<Float>();
        sprint.getTasks().forEach(task -> {
            duration.updateAndGet(oldTimeValue -> oldTimeValue += task.getTaskTime());
        });
        return ((float) hours+minutes)-duration.get() ;
    }
}
