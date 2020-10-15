package com.wpmtec.buildersBoard.data.repository;

import com.wpmtec.buildersBoard.data.entity.Project;
import org.springframework.data.repository.CrudRepository;

public interface ProjectRepository extends CrudRepository<Project, Long> {
    Project findByOrderNumber(Long orderNumber);
}
