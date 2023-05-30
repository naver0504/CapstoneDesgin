package com.example.capstone.domain;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table
public class User implements Serializable {

    @Id
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "question_id")
    private QuestionInput questionId;

    @Column(name = "user_do_aerator")
    private Boolean userDoAerator;
}
