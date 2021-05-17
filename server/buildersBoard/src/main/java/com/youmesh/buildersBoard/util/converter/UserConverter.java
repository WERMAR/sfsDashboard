package com.youmesh.buildersBoard.util.converter;

import com.youmesh.buildersBoard.data.entity.User;
import com.youmesh.buildersBoard.rest.data.UserRestData;

public class UserConverter {

    public static User toDB(UserRestData userRestData) {
        User user = new User();
        if (userRestData.getId() != null)
            user.setId(Long.parseLong(userRestData.getId()));
        user.setFirstname(userRestData.getFirstName());
        user.setLastname(userRestData.getLastName());
        return user;
    }

    public static UserRestData toRest(User user) {
        UserRestData userRestData = new UserRestData();
        userRestData.setFirstName(user.getFirstname());
        userRestData.setLastName(user.getLastname());
        userRestData.setId(user.getId().toString());
        return userRestData;
    }
}
