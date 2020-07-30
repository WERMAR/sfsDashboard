package com.wpmtec.buildersBoard.util.converter;

import com.wpmtec.buildersBoard.entity.data.Project;
import com.wpmtec.buildersBoard.entity.data.User;
import com.wpmtec.buildersBoard.rest.controller.data.ProjectRestData;

import java.text.ParseException;

public class ProjectConverter {

    /**
     * returns a converted project data. This is ready to save in the database
     *
     * @param restData - the main project data which is required for the creation of the project
     * @param user     - the user which is responsible for this project
     * @return the converted project
     * @throws ParseException - if the date format not equal to the standard format the exception will be thrown
     */
    public static Project toDB(ProjectRestData restData, User user) throws ParseException {
        Project project = new Project();
        project.setOrderNumber(Long.parseLong(restData.getOrderNumber()));
        project.setProjectDescription(restData.getProjectDescription());
        project.setStart(DateConverter.convertToDate(restData.getStart()));
        project.setEnd(DateConverter.convertToDate(restData.getEnd()));
        project.setReminder(restData.isReminder());
        if (restData.isReminder()) {
            project.setStartReminder(Integer.parseInt(restData.getStartReminder()));
            project.setEndReminder(Integer.parseInt(restData.getEndReminder()));
        }
        project.setResponsiblePerson(user);
        return project;
    }

    /**
     * returns a converted data object for the data exchange of the the project endpoint
     *
     * @param project - data object which will be converted
     * @return converted object from type {@link ProjectRestData}
     */
    public static ProjectRestData toRest(Project project) {
        ProjectRestData projectRestData = new ProjectRestData();
        projectRestData.setOrderNumber(String.valueOf(project.getOrderNumber()));
        projectRestData.setProjectDescription(project.getProjectDescription());
        projectRestData.setStart(DateConverter.convertToString(project.getStart()));
        projectRestData.setEnd(DateConverter.convertToString(project.getEnd()));
        if (project.isReminder()) {
            projectRestData.setStartReminder(String.valueOf(project.getStartReminder()));
            projectRestData.setEndReminder(String.valueOf(project.getEndReminder()));
        }
        projectRestData.setReminder(project.isReminder());
        projectRestData.setResponsiblePersonName(project.getResponsiblePerson().toPersonName());
        return projectRestData;
    }
}
