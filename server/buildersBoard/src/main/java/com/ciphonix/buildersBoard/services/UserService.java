package com.ciphonix.buildersBoard.services;

import com.ciphonix.buildersBoard.data.entity.User;
import com.ciphonix.buildersBoard.data.repository.UserRepository;
import com.ciphonix.buildersBoard.util.ExceptionMessages;
import javassist.NotFoundException;
import lombok.extern.slf4j.Slf4j;
import org.omg.CosNaming.NamingContextPackage.NotFound;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@Slf4j
public class UserService {

    private final UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public User saveUser(User user) {
        return this.userRepository.save(user);
    }

    public Iterable<User> loadAll() {
        return this.userRepository.findAll();
    }

    public Optional<User> loadForId(Long id) {
        return this.userRepository.findById(id);
    }

    public User loadForName(String firstName, String lastName) {
        return this.userRepository.findByFirstNameAndLastName(firstName, lastName);
    }

    public void delete(long userId) throws NotFoundException {
        Optional<User> user = this.userRepository.findById(userId);
        if (user.isPresent()) {
            this.userRepository.delete(user.get());
        } else {
            throw new NotFoundException(String.format(ExceptionMessages.USER_NOT_FOUND_EXEC, userId));
        }
    }
}
