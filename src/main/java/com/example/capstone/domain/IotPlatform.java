package com.example.capstone.domain;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.Calendar;
import java.util.Date;

import static javax.persistence.FetchType.*;

@Entity
@Table(name = "iot_platform")
@Data
public class IotPlatform {

    @Id @GeneratedValue
    @Column(name = "iot_id")
    private int id;

    private double temp;

    @Column(name = "do")
    private double DO;


    private int tankId;

    @Temporal(TemporalType.TIMESTAMP)
    private Date dateTime;

}
