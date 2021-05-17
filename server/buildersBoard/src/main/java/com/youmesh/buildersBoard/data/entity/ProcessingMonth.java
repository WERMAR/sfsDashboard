package com.youmesh.buildersBoard.data.entity;

import lombok.Data;

import javax.persistence.*;

@Entity
@Table(name = "processing_month")
@Data
public class ProcessingMonth {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "month_name")
    private String monthName;

    @Column(name = "month_num")
    private int monthNumber;
}
