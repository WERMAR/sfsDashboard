package com.wpmtec.buildersBoard.services;

import com.wpmtec.buildersBoard.entity.controller.UserJpaController;
import com.wpmtec.buildersBoard.entity.data.User;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class UserService {

    private final UserJpaController jpaController;

    public UserService(UserJpaController jpaController) {
        this.jpaController = jpaController;
    }

    public User saveUser(User user) {
        return jpaController.saveOrUpdate(user);
    }

    public boolean isUserExisting(User user) {
        return jpaController.isExist(user);
    }
}
