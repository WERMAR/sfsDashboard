package com.youmesh.buildersBoard.data.entity;

import lombok.Data;

import javax.persistence.*;

@Entity
@Table(name = "contract_details")
@Data
public class ContractDetails {

    @Id
    @GeneratedValue
    @Column(name = "id")
    private Long id;

    @ManyToOne(cascade = {CascadeType.PERSIST, CascadeType.MERGE}, fetch = FetchType.EAGER)
    @JoinColumn(name = "service_details")
    private ServiceDetails serviceDetails;

    @Column(name = "contract_number")
    private String contractNumber;

    @OneToOne(cascade = {CascadeType.PERSIST, CascadeType.MERGE}, fetch = FetchType.EAGER)
    @JoinColumn(name = "technician")
    private User technician;
}
