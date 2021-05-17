package com.youmesh.buildersBoard.data.entity;

import lombok.Data;

import javax.persistence.*;
import java.util.List;

@Data
@Entity
@Table(name = "user_details")
public class UserDetails {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "user")
    private User user;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "layout_config", referencedColumnName = "id")
    private LayoutConfig layoutConfig;

    @ManyToMany(cascade = CascadeType.ALL)
    @JoinTable(name = "ql_2_ud", joinColumns = {@JoinColumn(name = "ud_id")}, inverseJoinColumns = {@JoinColumn(name = "ql_id")})
    private List<QuickLinks> quickLinks;

}
