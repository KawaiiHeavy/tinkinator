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
public class Solution {

    @Id
    @GeneratedValue()
    @Column(name = "id", nullable = false)
    private UUID id;

    @Column(name = "solution_text", nullable = false)
    private String solutionText;

    @ManyToOne
    @JoinColumn(name = "answer_id")
    private Answer answer;

}
