package com.wpmtec.buildersBoard.entity.controller;

import com.wpmtec.buildersBoard.entity.data.Project;
import org.springframework.stereotype.Service;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;
import java.util.List;

@Service
public class ProjectJpaController {

  @PersistenceContext
  EntityManager entityManager;

  /**
   * returns the complete list of projects
   *
   * @return - list of all projects
   */
  public List<Project> getAll() {
    return entityManager.createNamedQuery("Project.getAll", Project.class).getResultList();
  }


  /**
   * returns the project based on the id
   */
  public Project getProjectForId(Long id) {
    return entityManager.createNamedQuery("Project.getForId", Project.class)
      .setParameter("id", id).getSingleResult();
  }

  /**
   * save a new created project
   * @param project - created project
   * @return - the saved project
   */
  @Transactional
  public Project save(Project project) {
    entityManager.persist(project);
    return project;
  }

  /**
   * update the project
   * @param project - modified project
   * @return - updated project
   */
  @Transactional
  public Project update(Project project) {
    entityManager.merge(project);
    return project;
  }

  /**
   * return if param based object exists
   * @param project - searched object
   * @return - boolean if exists
   */
  public boolean isExist(Project project) {
    return entityManager.find(Project.class, project) != null;
  }
}
