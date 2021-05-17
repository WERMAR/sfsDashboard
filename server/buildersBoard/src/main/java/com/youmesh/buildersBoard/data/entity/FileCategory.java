package com.youmesh.buildersBoard.data.entity;

import lombok.Data;

import javax.persistence.*;

@Entity
@Table(name = "file_category")
@Data
public class FileCategory {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "display_name")
    private String displayName;

    @Column(name = "folder_name")
    private String folderName;
}
