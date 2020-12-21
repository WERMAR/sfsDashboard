package com.ciphonix.buildersBoard.data.repository;

import com.ciphonix.buildersBoard.data.entity.Project;
import org.springframework.data.repository.CrudRepository;

import javax.validation.constraints.NotNull;

public interface ProjectRepository extends CrudRepository<Project, Long> {

    boolean existsByOrderNumber(@NotNull Long orderNumber);
    Project findByOrderNumber(Long orderNumber);

}
