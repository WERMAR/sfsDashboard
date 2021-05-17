package com.youmesh.buildersBoard.rest.data;

import com.youmesh.buildersBoard.data.entity.Project;
import lombok.Data;

/**
 * TransferObject of the Entity {@link Project}
 *
 * @author wermar
 * @see Data
 * @see Project
 */
@Data
public class ProjectRestData {
    private String orderNumber;
    private String projectDescription;
    private String start;
    private String end;
    private boolean reminder;
    private String startReminder;
    private String endReminder;
    private String responsiblePersonName;
}
