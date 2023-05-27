package com.example.capstone.domain;


import javax.persistence.*;

import static javax.persistence.FetchType.*;

@Entity
@Table(name = "question_output")
public class QuestionOutput {

    @Id @GeneratedValue
    @Column(name = "question_output_id")
    private int questionOutputId;

    @JoinColumn(name = "question_id")
    @OneToOne(fetch = LAZY)
    private QuestionInput questionInput;

    @Column(name = "api_do_aerator")
    private Boolean apiDoAerator;
}
