package com.example.capstone.domain;


import javax.persistence.*;

import java.io.Serializable;

import static javax.persistence.FetchType.*;

@Entity
@Table(name = "question_output")
public class QuestionOutput implements Serializable {

    @Id
    @JoinColumn(name = "question_id")
    @ManyToOne(fetch = LAZY)
    private QuestionInput questionInput;

    @Column(name = "api_do_aerator")
    private Boolean apiDoAerator;
}
