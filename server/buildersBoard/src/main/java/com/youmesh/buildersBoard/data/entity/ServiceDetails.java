package com.youmesh.buildersBoard.data.entity;

import lombok.Data;

import javax.persistence.*;

@Entity
@Table(name = "service_details")
@Data
public class ServiceDetails {

    @Id
    @GeneratedValue
    @Column(name = "id")
    private Long id;

    @ManyToOne(cascade = {CascadeType.PERSIST, CascadeType.MERGE}, fetch = FetchType.EAGER)
    @JoinColumn(name = "project")
    private Project project;

    @Column(name = "name")
    private String name;
}
