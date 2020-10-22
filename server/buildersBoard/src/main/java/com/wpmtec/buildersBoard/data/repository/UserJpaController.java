package com.wpmtec.buildersBoard.data.repository;

import com.wpmtec.buildersBoard.data.entity.User;
import org.springframework.stereotype.Service;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;
import java.util.List;

@Service
public class UserJpaController {

    @PersistenceContext
    EntityManager entityManager;

    public List<User> getAll() {
        return entityManager.createNamedQuery("User.getAll", User.class).getResultList();
    }

    public User getForId(long id) {
        return entityManager.createNamedQuery("User.getForId", User.class).setParameter("id", id).getSingleResult();
    }

    public List<User> getForName(String firstName, String lastName) {
        return entityManager.createNamedQuery("User.findForName", User.class).setParameter("firstName", firstName).setParameter("lastName", lastName).getResultList();
    }

    @Transactional
    public User saveOrUpdate(User user) {
        entityManager.merge(user);
        return user;
    }

    public boolean isExist(User user) {
        return entityManager.find(User.class, user.getId()) != null;
    }
}
