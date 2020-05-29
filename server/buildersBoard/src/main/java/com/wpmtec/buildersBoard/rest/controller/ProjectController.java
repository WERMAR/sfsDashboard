package com.wpmtec.buildersBoard.rest.controller;

import com.wpmtec.buildersBoard.entity.data.Project;
import com.wpmtec.buildersBoard.services.ProjectService;
import org.springframework.web.bind.annotation.*;

import java.util.List;


// TODO Need an DTO and Date must be an Unix-Timestamp
@RestController
@RequestMapping("/project")
public class ProjectController {

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
}
