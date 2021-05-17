package com.youmesh.buildersBoard.data.entity;

import lombok.Data;

import javax.persistence.*;

@Entity
@Table(name = "material")
@Data
public class Material {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "material_number")
    private String materialNumber;

    @Column(name = "name")
    private String name;

    @Column(name = "current_available")
    private int currentAvailable;
}