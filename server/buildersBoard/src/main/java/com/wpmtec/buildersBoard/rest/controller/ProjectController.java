package com.wpmtec.buildersBoard.rest.controller;

import com.wpmtec.buildersBoard.data.entity.Project;
import com.wpmtec.buildersBoard.data.entity.User;
import com.wpmtec.buildersBoard.rest.data.ProjectRestData;
import com.wpmtec.buildersBoard.services.ProjectService;
import com.wpmtec.buildersBoard.services.UserService;
import com.wpmtec.buildersBoard.util.converter.ProjectConverter;
import javassist.NotFoundException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import javax.persistence.NoResultException;
import javax.validation.ValidationException;
import java.text.ParseException;
import java.util.List;
import java.util.stream.Collectors;


@RestController
@RequestMapping("/project")
@Slf4j
public class ProjectController implements RestControllerInterface<Project> {

    private final ProjectService projectService;
    private final UserService userService;

    public ProjectController(ProjectService projectService, UserService userService) {
        this.projectService = projectService;
        this.userService = userService;
    }

    @GetMapping()
    @ResponseBody()
    public List<ProjectRestData> loadAll() {
        log.info("Load all Projects were triggered");
        return projectService.getAll().stream().map(ProjectConverter::toRest).collect(Collectors.toList());
    }

    @GetMapping("/{id}")
    @ResponseBody
    public ProjectRestData forId(@PathVariable("id") Long id) {
        Project project = projectService.getForId(id);
        return ProjectConverter.toRest(project);
    }

    @PutMapping
    @ResponseBody
    public ProjectRestData update(@RequestBody ProjectRestData restData) throws ParseException {
        List<User> userForData = getUserForData(restData.getResponsiblePersonName());
        User user = null;
        if (userForData.size() == 1) {
            user = userForData.get(0);
        }
        return ProjectConverter.toRest(projectService.saveOrUpdate(ProjectConverter.toDB(restData, user)));
    }

    @PostMapping()
    @ResponseBody
    public ProjectRestData create(@RequestBody ProjectRestData restData) {
        if (restData == null) {
            log.warn("Input Data was null");
            throw new NullPointerException();
        }

        User user = getUserForData(restData.getResponsiblePersonName());
        if (user != null)
            throw new NoResultException("User couldn't found for Name: " + restData.getResponsiblePersonName());
        Project project = null;
        try {
            project = ProjectConverter.toDB(restData, );
        } catch (ParseException e) {
            log.error("While converting ProjectRestData to ProjectData ParseException was thrown", e);
        }
        if (project != null && validateInput(project))
            return ProjectConverter.toRest(projectService.saveOrUpdate(project));
        log.warn("Validation of data failed -> cause reminder configuration is wrong");
        throw new ValidationException("Reminder configuration is not valid");
    }

    private User getUserForData(String responsiblePersonName) {
        int indexOfSplitter = responsiblePersonName.indexOf('@');
        String firstName = responsiblePersonName.substring(0, indexOfSplitter);
        String lastName = responsiblePersonName.substring(indexOfSplitter + 1);
        return userService.loadForName(firstName, lastName);
    }

    @DeleteMapping("/{orderNumber}")
    public void delete(@PathVariable("orderNumber") Long orderNumber) throws NotFoundException {
        try {
            Project project = this.projectService.getForOrderNumber(orderNumber);
            this.projectService.remove(project);
        } catch (NoResultException nre) {
            throw new NotFoundException("Project for orderNumber '" + orderNumber + "' not found");
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
