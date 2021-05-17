package com.youmesh.buildersBoard.rest.controller.impl;

import com.youmesh.buildersBoard.data.entity.Project;
import com.youmesh.buildersBoard.data.entity.User;
import com.youmesh.buildersBoard.rest.data.ProjectRestData;
import com.youmesh.buildersBoard.services.ProjectService;
import com.youmesh.buildersBoard.services.UserService;
import com.youmesh.buildersBoard.util.ExceptionMessages;
import com.youmesh.buildersBoard.util.converter.ProjectConverter;
import com.youmesh.buildersBoard.util.StreamUtilFactory;
import javassist.NotFoundException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import javax.persistence.NoResultException;
import javax.validation.ValidationException;
import java.text.ParseException;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

/**
 * <h3>Description</h3>
 * The ProjectController is the specific RestController for the Entity {@link Project}.
 *
 * @author wermar
 * @version 1.2
 */
@RestController
@RequestMapping("/project")
@Slf4j
public class ProjectController {

    private final ProjectService projectService;
    private final UserService userService;

    /**
     * <h3>Description</h3>
     * Default Constructor, which is used from the Spring-Context. Both parameter will be set from the Spring-Context.
     *
     * @param projectService - {@link ProjectService} instance to handle logical calls to the underlying tier
     * @param userService    - {@link UserService} instance to handle logical calls to the underlying tier
     * @see ProjectService
     * @see UserService
     */
    public ProjectController(ProjectService projectService, UserService userService) {
        this.projectService = projectService;
        this.userService = userService;
    }

    /**
     * <h3>Description</h3>
     * returns a List of {@link ProjectRestData}. This method call the logical tier ({@link ProjectService}) to load all existing {@link Project}'s
     * from the datasource. For an easier processing of the json on the client side, the base object ({@link Project}) will be convert
     * to the TransferObject "{@link ProjectRestData}".
     *
     * @return List of {@link ProjectRestData}
     * @see ProjectService#getAll()
     * @see Project
     * @see ProjectRestData
     * @see ProjectConverter#toRest(Project)
     * @see StreamUtilFactory#generateStream(Iterable)
     */
    @GetMapping
    @ResponseBody
    public List<ProjectRestData> loadAll() {
        log.info("Load all Projects were triggered");
        return StreamUtilFactory.generateStream(this.projectService.getAll()).map(ProjectConverter::toRest).collect(Collectors.toList());
    }

    /**
     * <h3>Description</h3>
     * returns the concrete {@link ProjectRestData} Object fits to the submitted id.
     * <p/>
     * The logical tier try to find a object in the datasource for the submitted id. Is this finished, the {@link Project} Object
     * will be converted to the {@link ProjectRestData}.
     *
     * @param id - submitted id of the searched item
     * @return Converted {@link ProjectRestData} match to the searched item
     * @throws NotFoundException - If the System were not able to find a data record for the given id
     * @see ProjectService#getForId(Long)
     * @see ProjectRestData
     * @see ProjectConverter#toRest(Project)
     */
    @GetMapping("/{id}")
    @ResponseBody
    public ProjectRestData loadForId(@PathVariable("id") Long id) throws NotFoundException {
        Optional<Project> project = projectService.getForId(id);
        if (!project.isPresent())
            throw new NotFoundException(String.format(ExceptionMessages.PROJECT_NOT_FOUND_EXEC, id));
        return ProjectConverter.toRest(project.get());
    }

    /**
     * <h3>Description</h3>
     * returns the updated object after processing.
     * <p/>
     * Logical tier update the submitted object. Before calling the logical tier method, the submitted data is converted from {@link ProjectRestData} to {@link Project}.
     * After performing the update, the returned entity is converted back again to the TransferObject and returned to the requested client.
     *
     * @param restData - submitted data, which should updated
     * @return updated {@link ProjectRestData} object
     * @throws ParseException - when a date is not in the correct format. For more Details: {@link ProjectConverter#toDB(ProjectRestData, User)}
     * @see Project
     * @see ProjectRestData
     * @see ProjectConverter#toDB(ProjectRestData, User)
     * @see ProjectService#save(Project)
     */
    @PutMapping
    @ResponseBody
    public ProjectRestData update(@RequestBody ProjectRestData restData) throws ParseException {
        User user = getUserForData(restData.getResponsiblePersonName());
        return ProjectConverter.toRest(projectService.save(ProjectConverter.toDB(restData, user)));
    }

    /**
     * <h3>Description</h3>
     * returns the created object.
     * <p/>
     * This Method save the submitted data in the datasource. First step before the logical tier is called from the system, the submitted data will be validate.
     * If this okay, the logical tier will be called and save the data. Afterwards, the returned Object is converted to {@link ProjectRestData} and returned to the client.
     *
     * @param restData - submitted data which should be saved from the system.
     * @return saved object after converted to the TransferObject {@link ProjectRestData}
     */
    @PostMapping
    @ResponseBody
    public ProjectRestData create(@RequestBody ProjectRestData restData) {
        if (restData == null) {
            log.warn("Input Data was null");
            throw new NullPointerException();
        }

        User user = getUserForData(restData.getResponsiblePersonName());
        Project project = null;
        try {
            project = ProjectConverter.toDB(restData, user);
        } catch (ParseException e) {
            log.error("While converting ProjectRestData to ProjectData ParseException was thrown", e);
        }
        if (project != null && validateInput(project))
            return ProjectConverter.toRest(projectService.save(project));
        log.warn("Validation of data failed -> cause reminder configuration is wrong");
        throw new ValidationException("Reminder configuration is not valid");
    }

    /**
     * <h3>Description</h3>
     * returns the user for submitted person name.
     * <p/>
     * This Method extract the first and lastname of the submitted person name. The person name is submitted from the client in a special format.
     * An example for this is: <code>"max@mustermann"</code> -> For this example the firstname will be "max" and the lastname will be "Mustermann".
     * <p/>
     * The logical tier will be called with the extract names and it will be tried to find a match to the first and lastname.
     * <p/>
     * Notice: the current version returns a List of User, because it is possible to retrieve more than one record for the combined name.
     *
     * @param responsiblePersonName - submitted person name of the responsible person of the project
     * @return {@link User}-Object which matched to the submitted person name
     * @see User
     * @see UserService#loadForName(String, String)
     */
    private User getUserForData(String responsiblePersonName) {
        int indexOfSplitter = responsiblePersonName.indexOf('@');
        String firstName = responsiblePersonName.substring(0, indexOfSplitter);
        String lastName = responsiblePersonName.substring(indexOfSplitter + 1);
        return userService.loadForName(firstName, lastName);
    }

    /**
     * <h3>Description</h3>
     * deletes a {@link Project}, which matched to the submitted order number.
     *
     * @param orderNumber - indicator for the deleted record in the datasource
     * @throws NotFoundException - could be thrown, when the logical tier was not able to find a matching record to the submitted order number
     * @see Project
     * @see ProjectService#remove(Project)
     */
    @DeleteMapping("/{orderNumber}")
    public void delete(@PathVariable("orderNumber") Long orderNumber) throws NotFoundException {
        try {
            Project project = this.projectService.getForOrderNumber(orderNumber);
            this.projectService.remove(project);
        } catch (NoResultException nre) {
            throw new NotFoundException("Project for orderNumber '" + orderNumber + "' not found");
        }
    }

    /**
     * <h3>Description</h3>
     * validation method to validate the submitted data
     * <p/>
     * The Method is used in the {@link ProjectController#create(ProjectRestData)} to validate the reminder setup. It is not allowed to set an reminder on zero, when a reminder is activated.
     * Instead of that, it is not allowed to deactivate the reminder but the value of the reminder is greater than zero
     *
     * @param inputData - submitted data from the client
     * @return if the input data is valid or not
     * @see ProjectController#create(ProjectRestData)
     */
    public boolean validateInput(Project inputData) {
       /* if (!inputData.isReminder()) {
            return inputData.getStartReminder() == 0 && inputData.getEndReminder() == 0;
        } else {
            return inputData.getStartReminder() > 0 || inputData.getEndReminder() > 0;
        }*/
        return true;
    }
}
