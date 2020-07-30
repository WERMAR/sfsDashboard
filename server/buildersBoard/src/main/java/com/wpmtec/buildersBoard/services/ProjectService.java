package com.wpmtec.buildersBoard.services;

import com.wpmtec.buildersBoard.entity.controller.ProjectJpaController;
import com.wpmtec.buildersBoard.entity.data.Project;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Slf4j
public class ProjectService {

    private final ProjectJpaController jpaController;

    public ProjectService(ProjectJpaController jpaController) {
        this.jpaController = jpaController;
    }

    /**
     * returns the created or updated project
     *
     * @param project - object that will be updated or created
     * @return - the created project
     */
    public Project saveOrUpdate(Project project) {
        return jpaController.saveOrUpdate(project);
    }

    /**
     * return all projects of database
     *
     * @return - list of all projects
     */
    public List<Project> getAll() {
        return jpaController.getAll();
    }

    /**
     * return project for param id
     *
     * @param id - project id
     * @return - project with param id
     */
    public Project getForId(Long id) {
        return jpaController.getProjectForId(id);
    }

    public Project getForOrderNumber(long orderNumber) {
        return jpaController.getProjectForOrderNumber(orderNumber);
    }


    public void remove(Project project) {
        jpaController.remove(project);
    }
}
