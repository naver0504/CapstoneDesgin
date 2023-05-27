package com.example.capstone.domain;

import javax.persistence.*;

import static javax.persistence.FetchType.*;

@Entity
@Table(name = "question_input")
public class QuestionInput {

    @Id
    @Column(name = "question_id")
    private int questionId;

    @ManyToOne(fetch = LAZY)
    private IotPlatform iotPlatform;

    @Column(name = "not_aerator")
    private Boolean nowAerator;
}
