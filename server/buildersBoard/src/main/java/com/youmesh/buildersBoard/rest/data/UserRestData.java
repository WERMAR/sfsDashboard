package com.youmesh.buildersBoard.rest.data;

import com.youmesh.buildersBoard.data.entity.User;
import lombok.Data;

/**
 * TransferObject of the Entity {@link User}
 *
 * @author wermar
 * @see Data
 * @see User
 */
@Data
public class UserRestData {
    private String id;
    private String firstName;
    private String lastName;
}
