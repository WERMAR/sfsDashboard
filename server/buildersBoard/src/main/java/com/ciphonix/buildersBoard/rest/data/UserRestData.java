package com.ciphonix.buildersBoard.rest.data;

import lombok.Data;

/**
 * TransferObject of the Entity {@link com.ciphonix.buildersBoard.data.entity.User}
 *
 * @author wermar
 * @see Data
 * @see com.ciphonix.buildersBoard.data.entity.User
 */
@Data
public class UserRestData {
    private String id;
    private String firstName;
    private String lastName;
}
