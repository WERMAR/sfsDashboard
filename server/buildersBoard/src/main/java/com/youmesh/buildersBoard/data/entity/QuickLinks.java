package com.youmesh.buildersBoard.data.entity;

import lombok.Data;

import javax.persistence.*;
import java.util.List;

@Data
@Entity
@Table(name = "quick_links")
public class QuickLinks {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "name")
    private String name;

    @Column(name = "link")
    private String link;

    @Column(name = "standard_link")
    private boolean standardLink;

    @ManyToMany(mappedBy = "quickLinks")
    private List<UserDetails> userDetailsList;
}
