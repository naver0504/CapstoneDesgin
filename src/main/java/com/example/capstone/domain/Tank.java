package com.example.capstone.domain;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Table(name = "dgu_water_tank")
@NoArgsConstructor(access =  AccessLevel.PROTECTED)
public class Tank {

    @Id
    @Column(name = "tank_id")
    private int tankId;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "farm_id")
    private Farm farm;

}
