package com.youmesh.buildersBoard.data.entity;

import lombok.Data;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "hotel_reservation")
@Data
public class HotelReservation {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @ManyToMany(cascade = CascadeType.ALL)
    @JoinTable(name = "files_2_hotel_resv", joinColumns = {@JoinColumn(name = "file_id")}, inverseJoinColumns = {@JoinColumn(name = "hotel_resv_id")})
    private List<Files> filesList;

    @Column(name = "name_of_hotel")
    private String nameOfHotel;

    @Column(name = "nLat")
    private Double nLat;

    @Column(name = "eLong")
    private Double eLong;

}
