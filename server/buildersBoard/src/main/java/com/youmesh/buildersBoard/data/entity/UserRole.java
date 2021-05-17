package com.youmesh.buildersBoard.data.entity;

import com.sun.istack.NotNull;
import lombok.Data;

import javax.persistence.*;
import java.util.List;

@Data
@Entity
@Table(name = "user_role")
public class UserRole {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private long id;

    @NotNull
    @Column(name = "name")
    private String name;

    @OneToOne
    @JoinColumn(name = "underlying_role")
    private UserRole userRole;

    @OneToMany(mappedBy = "userRole")
    private List<User> users;

}
