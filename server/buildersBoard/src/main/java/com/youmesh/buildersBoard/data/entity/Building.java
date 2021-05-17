package com.youmesh.buildersBoard.data.entity;

import lombok.Data;

import javax.persistence.*;

@Data
@Entity
@Table(name = "building")
public class Building {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @ManyToOne(cascade = {CascadeType.PERSIST, CascadeType.MERGE}, fetch = FetchType.EAGER)
    @JoinColumn(name = "customer")
    private Customer customer;

    @Column(name = "name")
    private String name;
}