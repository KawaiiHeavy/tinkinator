package com.example.app.models;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import org.hibernate.annotations.ColumnDefault;
import org.hibernate.annotations.GenericGenerator;
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
public class Question {

    @Id
    @Column(name = "id", updatable = false, nullable = false)
    @ColumnDefault("random_uuid()")
    @Type(type = "uuid-char")
    private UUID id;

    @Column(name = "question_text", nullable = false)
    private String questionText;

    @OneToMany(cascade = CascadeType.ALL)
    private Set<Answer> answers;

}
