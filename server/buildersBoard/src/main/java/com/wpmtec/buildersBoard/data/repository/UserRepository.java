package com.wpmtec.buildersBoard.data.repository;

import com.wpmtec.buildersBoard.data.entity.User;
import org.springframework.data.repository.CrudRepository;

public interface UserRepository extends CrudRepository<User, Long> {
}
