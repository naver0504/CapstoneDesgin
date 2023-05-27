package com.example.capstone.domain;

import javax.persistence.*;

@Entity
@Table(name = "question_best_condition")
public class QuestionBestCondition {

    @Id @GeneratedValue
    @Column(name = "question_best_condition_id")
    private int id;

    private double temp;

    private double DO;

}
