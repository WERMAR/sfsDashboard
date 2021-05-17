package com.youmesh.buildersBoard.data.entity;

import lombok.Data;

import javax.persistence.*;

@Data
@Entity
@Table(name = "layout_config")
public class LayoutConfig {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "service_display")
    private boolean serviceDisplay;

    @Column(name = "project_display")
    private boolean projectDisplay;

    @Column(name = "newsfeed_display")
    private boolean newsfeedDisplay;

    @Column(name = "personalLinks_display")
    private boolean personalLinksDisplay;

    @Column(name = "cal_clock_display")
    private boolean calClockDisplay;

}
