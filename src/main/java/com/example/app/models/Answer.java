package com.example.app.models;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.UUID;

@Entity
@Table
@Setter
@Getter
@NoArgsConstructor
public class Answer {

    @Id
    @GeneratedValue()
    @Column(name = "id", nullable = false)
    private UUID id;

    @Column(name = "answer_text", nullable = false)
    private String answerText;

    @ManyToOne
    @JoinColumn(name = "question_id")
    private Question question;

}
