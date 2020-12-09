package com.ciphonix.buildersBoard.data.repository;

import com.ciphonix.buildersBoard.data.entity.User;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

public interface UserRepository extends CrudRepository<User, Long> {
    User findByFirstNameAndLastName(String firstName, String lastName);
    Optional<User> findById(Long id);
}
