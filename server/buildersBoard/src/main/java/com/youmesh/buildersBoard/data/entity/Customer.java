package com.youmesh.buildersBoard.data.entity;

import lombok.Data;

import javax.persistence.*;
import java.util.List;

@Data
@Entity
@Table(name = "customer")
public class Customer {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "name")
    private String name;

    @Lob
    @Column(name = "logo")
    private byte[] logo;

    @Column(name = "logo_url")
    private String logoUrl;

    @OneToMany(mappedBy = "customer")
    private List<Building> buildings;
}
