package com.wpmtec.buildersBoard.services;

import com.wpmtec.buildersBoard.data.entity.Project;
import com.wpmtec.buildersBoard.data.repository.ProjectRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.persistence.NoResultException;
import java.util.ArrayList;
import java.util.List;

@Service
@Slf4j
public class ProjectService {

    private final ProjectRepository repository;

    public ProjectService(ProjectRepository repository) {
        this.repository = repository;
    }

    /**
     * returns the created or updated project
     *
     * @param project - object that will be updated or created
     * @return - the created project
     */
    public Project saveOrUpdate(Project project) {
        try {
            Project searchedProject = repository.findByOrderNumber(project.getOrderNumber());
            if (searchedProject != null) {
                project.setId(searchedProject.getId());
            }
        } catch (NoResultException nre) {
            log.info("No existing data record found for orderNumber: " + project.getOrderNumber() + " the object will be saved now");
        }
        return repository.save(project);
    }

    /**
     * return all projects of database
     *
     * @return - list of all projects
     */
    public List<Project> getAll() {
        List<Project> returnList = new ArrayList<>();
        repository.findAll().iterator().forEachRemaining(returnList::add);
        return returnList;
    }

    /**
     * return project for param id
     *
     * @param id - project id
     * @return - project with param id
     */
    public Project getForId(Long id) {
        return repository.findById(id).orElseThrow(NoResultException::new);
    }

    public Project getForOrderNumber(long orderNumber) {
        return repository.findByOrderNumber(orderNumber);
    }


    public void remove(Project project) {
        repository.delete(project);
    }
}
