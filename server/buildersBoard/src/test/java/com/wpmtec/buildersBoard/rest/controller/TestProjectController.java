package com.wpmtec.buildersBoard.rest.controller;

import com.wpmtec.buildersBoard.entity.controller.ProjectJpaController;
import com.wpmtec.buildersBoard.entity.controller.UserJpaController;
import com.wpmtec.buildersBoard.entity.data.Project;
import com.wpmtec.buildersBoard.rest.controller.project.ProjectController;
import com.wpmtec.buildersBoard.services.ProjectService;
import com.wpmtec.buildersBoard.services.UserService;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class TestProjectController {

    private static Stream<Arguments> createTestData() {
        return Stream.of(Arguments.of(setupProjectEntity(true, 1, 1), true),
                Arguments.of(setupProjectEntity(false, 0, 0), true),
                Arguments.of(setupProjectEntity(false, 1, 0), false),
                Arguments.of(setupProjectEntity(false, 0, 1), false));
    }

    private static Project setupProjectEntity(boolean reminder, int startReminder, int endReminder) {
        Project project = new Project();
        project.setReminder(reminder);
        project.setStartReminder(startReminder);
        project.setEndReminder(endReminder);
        return project;
    }

    @ParameterizedTest(name = "Test validation of Project Data -> expected value: {1}")
    @MethodSource("createTestData")
    public void testValidationMethod(Project project, boolean expectedResult) {
        ProjectController projectController = new ProjectController(createProjectService(), createUserService());
        assertEquals(expectedResult, projectController.validateInput(project));
    }

    private UserService createUserService() {
        return new UserService(new UserJpaController());
    }

    private ProjectService createProjectService() {
        return new ProjectService(new ProjectJpaController());
    }
}
