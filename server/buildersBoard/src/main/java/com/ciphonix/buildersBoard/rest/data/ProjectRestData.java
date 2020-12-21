package com.ciphonix.buildersBoard.rest.data;

import lombok.Data;

/**
 * TransferObject of the Entity {@link com.ciphonix.buildersBoard.data.entity.Project}
 *
 * @author wermar
 * @see Data
 * @see com.ciphonix.buildersBoard.data.entity.Project
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
