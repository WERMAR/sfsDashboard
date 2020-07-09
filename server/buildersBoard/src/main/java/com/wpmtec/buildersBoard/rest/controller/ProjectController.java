package com.wpmtec.buildersBoard.rest.controller;

import com.wpmtec.buildersBoard.entity.data.Project;
import com.wpmtec.buildersBoard.services.ProjectService;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping("/project")
public class ProjectController implements RestControllerInterface<Project> {

    private final ProjectService projectService;

    public ProjectController(ProjectService projectService) {
        this.projectService = projectService;
    }

    @GetMapping()
    @ResponseBody()
    public List<Project> loadAll() {
        return projectService.getAll();
    }

    @GetMapping("/{id}")
    @ResponseBody
    public Project forId(@PathVariable("id") Long id) {
        return projectService.getForId(id);
    }

    @PutMapping("")
    @ResponseBody
    public Project update(@RequestBody Project project) {
        return projectService.updateProject(project);
    }

    @PostMapping()
    @ResponseBody
    public Project create(@RequestBody Project project) {
        return projectService.createProject(project);
    }

    @Override
    public boolean validateInput(Project inputData) {
        if (!inputData.isReminder()) {
            return inputData.getStartReminder() == 0 && inputData.getEndReminder() == 0;
        } else {
            return inputData.getStartReminder() > 0 || inputData.getEndReminder() > 0;
        }
    }
}
