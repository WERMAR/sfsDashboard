package com.wpmtec.buildersBoard.data.repository;

import com.wpmtec.buildersBoard.data.entity.Project;
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
     * update the project
     *
     * @param project - modified project
     * @return - updated project
     */
    @Transactional
    public Project saveOrUpdate(Project project) {
        entityManager.merge(project);
        return project;
    }

    /**
     * removed the over given project object from database
     *
     * @param project - removed object
     */
    @Transactional
    public void remove(Project project) {
        this.entityManager.remove(project);
    }

    public Project getProjectForOrderNumber(long orderNumber) {
        return this.entityManager.createNamedQuery("Project.getForOrderNumber", Project.class)
                .setParameter("orderNumber", orderNumber).getSingleResult();
    }
}
