package com.wpmtec.buildersBoard.services;

import com.wpmtec.buildersBoard.entity.controller.ProjectJpaController;
import com.wpmtec.buildersBoard.entity.data.Project;
import org.springframework.stereotype.Service;

import javax.inject.Inject;
import java.util.List;

@Service
public class ProjectService {

  @Inject
  ProjectJpaController jpaController;

  /**
   * this method creates the project
   *
   * @param project - new project
   */
  public Project createProject(Project project) {
    return jpaController.save(project);
  }

  /**
   * trigger the update of a project and returns a http code as int
   *
   * @param project - object which will be updated
   * @return - http code
   */
  public Project updateProject(Project project) {
    Project savedProject = null;
    if (jpaController.isExist(project)) {
      savedProject = jpaController.save(project);
    } else {
      savedProject = jpaController.update(project);
    }
    return savedProject;
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


}
