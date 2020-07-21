package com.wpmtec.buildersBoard.rest.controller.project;

import com.wpmtec.buildersBoard.entity.data.Project;
import com.wpmtec.buildersBoard.rest.controller.RestControllerInterface;
import com.wpmtec.buildersBoard.services.ProjectService;
import javassist.NotFoundException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import javax.persistence.NoResultException;
import javax.validation.ValidationException;
import java.util.List;


@RestController
@RequestMapping("/project")
@Slf4j
public class ProjectController implements RestControllerInterface<Project> {

    private final ProjectService projectService;

    public ProjectController(ProjectService projectService) {
        this.projectService = projectService;
    }

    @GetMapping()
    @ResponseBody()
    public List<Project> loadAll() {
        log.info("Load all Projects were triggered");
        return projectService.getAll();
    }

    @GetMapping("/{id}")
    @ResponseBody
    public Project forId(@PathVariable("id") Long id) {
        return projectService.getForId(id);
    }

    @PutMapping
    @ResponseBody
    public Project update(@RequestBody Project project) {
        return projectService.saveOrUpdate(project);
    }

    @PostMapping()
    @ResponseBody
    public Project create(@RequestBody Project project) {
        if (validateInput(project))
            return projectService.saveOrUpdate(project);
        log.warn("Validation of data failed -> cause reminder configuration is wrong");
        throw new ValidationException("Reminder configuration is not valid");
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable("id") Long id) throws NotFoundException {
        try {
            Project project = this.projectService.getForId(id);
            this.projectService.remove(project);
        } catch (NoResultException nre) {
            throw new NotFoundException("Project for id " + id + " not found");
        }
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
