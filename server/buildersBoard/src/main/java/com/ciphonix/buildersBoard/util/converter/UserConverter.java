package com.ciphonix.buildersBoard.util.converter;

import com.ciphonix.buildersBoard.data.entity.User;
import com.ciphonix.buildersBoard.rest.data.UserRestData;

public class UserConverter {

    public static User toDB(UserRestData userRestData) {
        User user = new User();
        if (userRestData.getId() != null)
            user.setId(Long.parseLong(userRestData.getId()));
        user.setFirstName(userRestData.getFirstName());
        user.setLastName(userRestData.getLastName());
        return user;
    }

    public static UserRestData toRest(User user) {
        UserRestData userRestData = new UserRestData();
        userRestData.setFirstName(user.getFirstName());
        userRestData.setLastName(user.getLastName());
        userRestData.setId(user.getId().toString());
        return userRestData;
    }
}
