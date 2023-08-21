package com.example.TaskManagementService.web;

import com.example.TaskManagementService.domain.dto.SprintDto;
import com.example.TaskManagementService.domain.dto.TaskDto;
import com.example.TaskManagementService.domain.entity.Sprint;
import com.example.TaskManagementService.domain.enums.SprintStatus;
import com.example.TaskManagementService.service.IService.ISprintService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Set;

@RestController
@RequestMapping("/sprints")
@CrossOrigin("*")
public class SprintRestController {
    private final ISprintService sprintService;
    @Autowired
    public SprintRestController(ISprintService sprintService) {
        this.sprintService = sprintService;
    }
    @PostMapping
    public ResponseEntity<SprintDto> createSprint(@RequestBody SprintDto sprintDto){
        return new ResponseEntity<>(this.sprintService.createSprint(sprintDto), HttpStatus.CREATED) ;
    }
    @PutMapping("/fill/{sprintId}/{taskId}")
    public ResponseEntity<SprintDto> fillSprint(@PathVariable("sprintId") String sprintId,
                                                @PathVariable("taskId") String taskId
    ){
       return new ResponseEntity<>(this.sprintService.fillSprint(taskId,sprintId),
                                   HttpStatus.OK) ;
    }
    @PutMapping("/delete/{sprintId}")
    public ResponseEntity<SprintDto> deleteSprint(@PathVariable("sprintId")String sprintId){
        return new ResponseEntity<>(this.sprintService.deleteSprint(sprintId),HttpStatus.OK);
    }
    @PutMapping("/update/{sprintId}")
    public ResponseEntity<SprintDto> updateSprint(@PathVariable("sprintId") String sprintId,
                                                  @RequestBody SprintDto sprintDto){
     return new ResponseEntity<>(this.sprintService.updateSprint(sprintId,sprintDto),HttpStatus.OK);
    }
    @GetMapping("/{sprintId}")
    public ResponseEntity<SprintDto> getSprint(@PathVariable("sprintId") String sprintId){
        return  new ResponseEntity<>(this.sprintService.getSprint(sprintId),HttpStatus.OK);
    }
    @GetMapping
    public ResponseEntity<Set<SprintDto>> getAllSprints(){

        return new ResponseEntity<>(this.sprintService.getSprints(),HttpStatus.OK);
    }
    @GetMapping("/all-undeleted")
    public ResponseEntity<Set<SprintDto>> getAllUndeletedSprints(){

        return new ResponseEntity<>(this.sprintService.getUndeletedSprints(),HttpStatus.OK) ;
    }
    @PutMapping("/update-status/{sprintId}")
    public ResponseEntity<SprintDto> updateSprintStatus(@PathVariable("sprintId")String sprintId,
                                                        @RequestParam SprintStatus sprintStatus){
       return new ResponseEntity<>(this.sprintService.updateSprintStatus(sprintStatus,sprintId),HttpStatus.OK) ;
    }
}
