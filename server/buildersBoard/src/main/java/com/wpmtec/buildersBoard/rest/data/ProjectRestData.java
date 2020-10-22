package com.wpmtec.buildersBoard.rest.data;

import lombok.Data;

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
