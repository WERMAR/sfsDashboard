package com.youmesh.buildersBoard.data.repository;

import com.youmesh.buildersBoard.data.entity.User;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

public interface UserRepository extends CrudRepository<User, Long> {
    User findByFirstnameAndLastname(String firstName, String lastName);
    Optional<User> findById(Long id);
}
