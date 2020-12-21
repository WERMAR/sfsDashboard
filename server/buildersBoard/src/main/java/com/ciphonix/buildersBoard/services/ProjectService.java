package com.ciphonix.buildersBoard.services;

import com.ciphonix.buildersBoard.data.entity.Project;
import com.ciphonix.buildersBoard.data.repository.ProjectRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.persistence.NoResultException;
import java.util.Optional;

/**
 * <h3>Description</h3>
 * logical tier of {@link Project} - Process. It combines the RestController to the underlying DataRepository
 *
 * @author wermar
 * @see ProjectRepository
 * @see com.ciphonix.buildersBoard.rest.controller.impl.ProjectController
 * @see Project
 * @see Service
 * @see Slf4j
 */
@Service
@Slf4j
public class ProjectService {

    private final ProjectRepository projectRepository;

    /**
     * <h3>Description</h3>
     * Default Constructor which is used from the Spring-Context to initialize the Service with Dependency Injection.
     *
     * @param projectRepository - data repository for {@link Project}
     */
    public ProjectService(ProjectRepository projectRepository) {
        this.projectRepository = projectRepository;
    }

    /**
     * <h3>Description</h3>
     * returns the saved project. First of all it will checked, if the project exists. It will check by the orderNumber from the given project. Returns the query an Project, the system update the record. If not, the dataRecord will be save.
     *
     * @param project - object that will be updated or created
     * @return - the created project
     * @see Project
     * @see ProjectRepository#existsByOrderNumber(Long) 
     * @see ProjectRepository#findByOrderNumber(Long) 
     * @see ProjectRepository#save(Object)
     */
    public Project save(Project project) {
        try {
            Project searchedProject = projectRepository.findByOrderNumber(project.getOrderNumber());
            if (projectRepository.existsByOrderNumber(project.getOrderNumber())) {
                project.setId(searchedProject.getId());
            }
        } catch (NoResultException nre) {
            log.info("No existing data record found for orderNumber: " + project.getOrderNumber() + " the object will be saved now");
        }
        return projectRepository.save(project);
    }

    /**
     * return all projects of database
     *
     * @return - list of all projects
     */
    public Iterable<Project> getAll() {
        return projectRepository.findAll();
    }

    /**
     * return project for param id
     *
     * @param id - project id
     * @return - project with param id
     */
    public Optional<Project> getForId(Long id) {
        return projectRepository.findById(id);
    }

    public Project getForOrderNumber(long orderNumber) {
        return projectRepository.findByOrderNumber(orderNumber);
    }


    public void remove(Project project) {
        projectRepository.delete(project);
    }
}
