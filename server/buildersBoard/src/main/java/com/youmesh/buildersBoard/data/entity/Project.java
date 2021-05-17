package com.youmesh.buildersBoard.data.entity;

import lombok.Data;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;

@Data
@Entity
@Table(name = "project")
public class Project implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @ManyToOne(cascade = {CascadeType.PERSIST, CascadeType.MERGE}, fetch = FetchType.EAGER)
    @JoinColumn(name = "responsible_person")
    private User responsiblePerson;

    @Column(name = "is_service")
    private boolean isService;

    @OneToMany(mappedBy = "project")
    List<ProjectDetails> projectsDetails;

    @OneToMany(mappedBy = "project")
    List<ServiceDetails> serviceDetails;
}
