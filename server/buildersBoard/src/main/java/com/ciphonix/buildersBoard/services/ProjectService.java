package com.ciphonix.buildersBoard.services;

import com.ciphonix.buildersBoard.data.entity.Project;
import com.ciphonix.buildersBoard.data.repository.ProjectRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.persistence.NoResultException;
import java.util.Optional;

@Service
@Slf4j
public class ProjectService {

    private final ProjectRepository projectRepository;

    public ProjectService(ProjectRepository projectRepository) {
        this.projectRepository = projectRepository;
    }

    /**
     * returns the created or updated project
     *
     * @param project - object that will be updated or created
     * @return - the created project
     */
    public Project saveOrUpdate(Project project) {
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
