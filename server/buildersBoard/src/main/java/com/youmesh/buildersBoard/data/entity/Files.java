package com.youmesh.buildersBoard.data.entity;

import lombok.Data;

import javax.persistence.*;

@Entity
@Table(name = "files")
@Data
public class Files {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @ManyToOne(cascade = {CascadeType.PERSIST, CascadeType.MERGE}, fetch = FetchType.EAGER)
    @JoinColumn(name = "file_category")
    private FileCategory fileCategory;

    @Column(name = "file_name")
    private String fileName;

}
