package com.ciphonix.buildersBoard.data.entity;

import lombok.Data;

import javax.persistence.*;
import java.io.Serializable;

@Data
@Entity
@Table(name = "user")
@NamedQueries({

        @NamedQuery(name = "User.getAll", query = "Select u from User u"),
        @NamedQuery(name = "User.getForId", query = "Select u from User u where u.id = :id"),
        @NamedQuery(name = "User.findForName", query = "Select u from User u where u.firstName LIKE CONCAT('%',:firstName,'%') and u.lastName LIKE CONCAT('%',:lastName,'%')")
})
public class User implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "last_name")
    private String lastName;

    @Column(name = "first_name")
    private String firstName;

    public String toPersonName() {
        return this.firstName + "@" + this.lastName;
    }
}
