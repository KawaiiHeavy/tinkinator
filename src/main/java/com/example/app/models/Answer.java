package com.example.app.models;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import org.hibernate.annotations.*;

import javax.persistence.*;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.Table;
import java.util.UUID;

@Entity
@Table
@Setter
@Getter
@NoArgsConstructor
@ToString
public class Answer {

    @Id
    @GeneratedValue(generator = "UUID")
    @GenericGenerator(
            name = "UUID",
            strategy = "org.hibernate.id.UUIDGenerator"
    )
    @Column(name = "id", updatable = false, nullable = false)
    @ColumnDefault("random_uuid()")
    @Type(type = "uuid-char")
    private UUID id;

    @Column(name = "answer_text", nullable = false)
    private String answerText;

    @OneToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private Question question;

    @OneToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private Solution solution;

}
