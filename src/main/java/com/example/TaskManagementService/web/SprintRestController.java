package com.example.TaskManagementService.web;

import com.example.TaskManagementService.domain.dto.SprintDto;
import com.example.TaskManagementService.domain.dto.TaskDto;
import com.example.TaskManagementService.domain.entity.Sprint;
import com.example.TaskManagementService.domain.enums.SprintStatus;
import com.example.TaskManagementService.service.IService.ISprintService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Set;

@RestController
@RequestMapping("/sprints")
public class SprintRestController {
    private final ISprintService sprintService;
    @Autowired
    public SprintRestController(ISprintService sprintService) {
        this.sprintService = sprintService;
    }
    @PostMapping
    public Sprint createSprint(@RequestBody SprintDto sprintDto){
        return this.sprintService.createSprint(sprintDto);
    }
    @PutMapping("/fill/{sprintId}")
    public void fillSprint(@PathVariable("sprintId") String sprintId,@RequestBody TaskDto taskDto){
        this.sprintService.fillSprint(taskDto,sprintId);
    }
    @PutMapping("/delete/{sprintId}")
    public void deleteSprint(@PathVariable("sprintId")String sprintId){
        this.sprintService.deleteSprint(sprintId);
    }
    @PutMapping("/update/{sprintId}")
    public void updateSprint(@PathVariable("sprintId") String sprintId,@RequestBody SprintDto sprintDto){
        this.sprintService.updateSprint(sprintId,sprintDto);
    }
    @GetMapping("/{sprintId}")
    public SprintDto getSprint(@PathVariable("sprintId") String sprintId){
        return  this.sprintService.getSprint(sprintId);
    }
    @GetMapping
    public Set<SprintDto> getAllSprints(){
        return this.sprintService.getSprints();
    }
    @GetMapping("/all-undeleted")
    public Set<SprintDto> getAllUndeletedSprints(){
        return this.sprintService.getUndeletedSprints();
    }
    @PutMapping("/update-status/{sprintId}")
    public void updateSprintStatus(@PathVariable("sprintId")String sprintId, @RequestParam SprintStatus sprintStatus){
        this.sprintService.updateSprintStatus(sprintStatus,sprintId);
    }
}
