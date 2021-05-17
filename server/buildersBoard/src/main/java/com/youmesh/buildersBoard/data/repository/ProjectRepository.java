package com.youmesh.buildersBoard.data.repository;

import com.youmesh.buildersBoard.data.entity.Project;
import org.springframework.data.repository.CrudRepository;

public interface ProjectRepository extends CrudRepository<Project, Long> {

    /*boolean existsByOrderNumber(@NotNull Long orderNumber);
    Project findByOrderNumber(Long orderNumber);*/

}
