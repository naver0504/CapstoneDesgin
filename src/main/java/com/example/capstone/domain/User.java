package com.example.capstone.domain;

import javax.persistence.*;

@Entity
@Table
public class User {

    @Id @GeneratedValue
    @Column(name = "user_id")
    private int userId;

    @OneToOne
    @JoinColumn(name = "question_id")
    private QuestionInput questionInput;

    @Column(name = "user_do_aerator")
    private Boolean userDoAerator;
}
