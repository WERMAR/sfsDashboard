package com.wpmtec.buildersBoard.services;

import com.wpmtec.buildersBoard.data.entity.User;
import com.wpmtec.buildersBoard.data.repository.UserRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.persistence.NoResultException;
import java.util.ArrayList;
import java.util.List;

@Service
@Slf4j
public class UserService {

    private final UserRepository repository;

    public UserService(UserRepository repository) {
        this.repository = repository;
    }

    public User saveUser(User user) {
        return this.repository.save(user);
    }

    public boolean isUserExisting(User user) {
        return this.repository.existsById(user.getId());
    }

    public List<User> loadAll() {
        List<User> returnList = new ArrayList<>();
        this.repository.findAll().iterator().forEachRemaining(returnList::add);
        return returnList;
    }

    public User loadForId(Long id) {
        return this.repository.findById(id).orElseThrow(NoResultException::new);
    }

    public User loadForName(String firstName, String lastName) {
        return this.repository.findByFirstNameAndLastName(firstName, lastName);
    }
}
