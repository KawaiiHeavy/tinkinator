package com.example.app.models;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import org.hibernate.annotations.Type;

import javax.persistence.*;
import java.util.Set;
import java.util.UUID;

@Entity
@Table
@Setter
@Getter
@NoArgsConstructor
@ToString
public class Solution {

    @Id
    @Column(name = "id", updatable = false, nullable = false)
    @Type(type = "uuid-char")
    private UUID id;

    @Column(name = "solution_text", nullable = false)
    private String solutionText;

    @ToString.Exclude
    @ManyToMany
    @JoinTable(name = "solutions_answers",
            joinColumns = @JoinColumn(name = "solution_id", referencedColumnName = "id"),
            inverseJoinColumns = @JoinColumn(name = "answer_id", referencedColumnName = "id"))
    private Set<Answer> answers;


}
