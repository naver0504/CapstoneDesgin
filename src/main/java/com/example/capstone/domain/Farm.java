package com.example.capstone.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "dgu_water_farm")
public class Farm {

    @Id
    @Column(name = "farm_id")
    private int id;

    @Column(name = "farm_name")
    private int farmName;
}
