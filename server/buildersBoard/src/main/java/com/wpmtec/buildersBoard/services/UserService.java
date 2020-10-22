package com.wpmtec.buildersBoard.services;

import com.wpmtec.buildersBoard.data.repository.UserJpaController;
import com.wpmtec.buildersBoard.data.entity.User;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Slf4j
public class UserService {

    private final UserJpaController jpaController;

    public UserService(UserJpaController jpaController) {
        this.jpaController = jpaController;
    }

    public User saveUser(User user) {
        return this.jpaController.saveOrUpdate(user);
    }

    public boolean isUserExisting(User user) {
        return this.jpaController.isExist(user);
    }

    public List<User> loadAll() {
        return this.jpaController.getAll();
    }

    public User loadForId(Long id) {
        return this.jpaController.getForId(id);
    }

    public List<User> loadForName(String firstName, String lastName) {
        return this.jpaController.getForName(firstName, lastName);
    }
}
