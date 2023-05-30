package com.example.capstone.domain;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.Date;

import static javax.persistence.FetchType.*;

@Entity
@Table(name = "iot_platform")
@NoArgsConstructor(access =  AccessLevel.PROTECTED)
public class IotPlatform {

    @Id @GeneratedValue
    @Column(name = "iot_id")
    private int iotId;

    private double temp;

    @Column(name = "do")
    private double DO;

    @ManyToOne(fetch = LAZY)
    private Tank tank;

    @Temporal(TemporalType.TIMESTAMP)
    private Date dateTime;


}
