package com.example.TaskManagementService.service.serviceImp;

import com.example.TaskManagementService.domain.dto.SprintDto;
import com.example.TaskManagementService.domain.entity.Sprint;
import com.example.TaskManagementService.domain.entity.Task;
import com.example.TaskManagementService.domain.enums.SprintStatus;
import com.example.TaskManagementService.domain.mapper.SprintMapper;
import com.example.TaskManagementService.domain.mapper.TaskMapper;
import com.example.TaskManagementService.error_handling.EntityNotFoundException;
import com.example.TaskManagementService.error_handling.ValidationException;
import com.example.TaskManagementService.repository.SprintRepository;
import com.example.TaskManagementService.repository.TaskRepository;
import com.example.TaskManagementService.service.IService.ISprintService;
import jakarta.transaction.Transactional;
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
    private final TaskRepository taskRepository;
    @Autowired
    public SprintServiceImp(SprintRepository sprintRepository, SprintMapper sprintMapper,TaskMapper taskMapper,TaskRepository taskRepository) {
        this.sprintRepository = sprintRepository;
        this.sprintMapper = sprintMapper;
        this.taskMapper = taskMapper;
        this.taskRepository = taskRepository;
    }

    @Override
    public SprintDto createSprint(SprintDto sprintDto) {
        this.sprintRepository.save(this.sprintMapper.toEntity(sprintDto));
        return sprintDto;
    }

    @Override
    @Transactional
    public SprintDto fillSprint(String taskId, String sprintId) {
        Optional<Sprint> sprintToFill = this.sprintRepository.findById(sprintId);
        Task   taskToAffect = this.taskRepository.findById(taskId).orElseThrow(()->new EntityNotFoundException("task not found"));
        if(sprintToFill.isPresent()){
            Float sprintDuration = this.getSprintDurationPerHours(sprintToFill.get());
            if(sprintDuration > 0.0 & sprintDuration > taskToAffect.getTaskTime()){
                sprintToFill.get().getTasks().add(taskToAffect);
                 taskToAffect.setSprint(sprintToFill.get());
                this.sprintRepository.save(sprintToFill.get());
                this.taskRepository.save(taskToAffect);
                return this.sprintMapper.toDto(sprintToFill.get());
            }else {
                throw new ValidationException("task time not valid");
            }
        }else {
            throw new EntityNotFoundException("Sprint Not Found");
        }
    }

    @Override
    @Transactional
    public SprintDto deleteSprint(String sprintId) {
       Optional<Sprint> sprintToDelete = this.sprintRepository.findById(sprintId);
       if(sprintToDelete.isPresent()){
           sprintToDelete.get().setDeleted(true);
           sprintToDelete.get().setDeleted_at(Timestamp.valueOf(LocalDateTime.now()));
           this.sprintRepository.save(sprintToDelete.get());
           return this.sprintMapper.toDto(sprintToDelete.get());
       }else{
           throw new EntityNotFoundException("Sprint Not Found");
       }
    }

    @Override
    @Transactional
    public SprintDto updateSprint(String sprintId, SprintDto sprintDto) {
        Optional<Sprint> sprintToUpdate = this.sprintRepository.findById(sprintId);
        if(sprintToUpdate.isPresent()){
            sprintToUpdate.get().setModified_at(Timestamp.valueOf(LocalDateTime.now()));
            this.sprintRepository.save(this.sprintMapper.toExistingEntity(sprintDto,sprintToUpdate.get()));
            return this.sprintMapper.toDto(sprintToUpdate.get());
        }else{
            throw new EntityNotFoundException("Sprint Not Found");
        }
    }

    @Override
    public SprintDto getSprint(String sprintId) {
        Optional<Sprint> sprint = this.sprintRepository.findById(sprintId);
        if(sprint.isPresent()){
            return this.sprintMapper.toDto(sprint.get());
        }
      else {
          throw new EntityNotFoundException("sprint not found");
        }
    }

    @Override
    public Set<SprintDto> getSprints() {
        Set<SprintDto> sprintsDto = new HashSet<>();
        this.sprintRepository.findAll()
                .forEach(sprint ->
                {

                    sprintsDto
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
    @Transactional
    public SprintDto updateSprintStatus(SprintStatus sprintStatus, String sprintId) {
        Optional<Sprint> sprint = this.sprintRepository.findById(sprintId);
        if(sprint.isPresent()){
            sprint.get().setModified_at(Timestamp.valueOf(LocalDateTime.now()));
            sprint.get().setSprintStatus(sprintStatus);
            this.sprintRepository.save(sprint.get());
            return this.sprintMapper.toDto(sprint.get());
        }else{
            throw new EntityNotFoundException("sprint not found");
        }

    }

    public Float getSprintDurationPerHours(Sprint sprint){
        Long time = sprint.getEndDate().getTime()
                       - sprint.getStartDate().getTime();
        Long seconds = time/1000;
        Long minutes = seconds/60;
        Long hours = minutes/60;
        Long days = hours/24;
        Long workHors = days*8;

        AtomicReference<Float> duration = new AtomicReference<>(hours> 24 ? workHors.floatValue() : hours.floatValue());

        sprint.getTasks().forEach(task -> {
           duration.updateAndGet(currentValue ->currentValue - task.getTaskTime());
        });
        return duration.get() ;
    }
}
