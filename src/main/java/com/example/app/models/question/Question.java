package com.example.app.models.question;

import com.example.app.models.other.Answer;
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
    @GeneratedValue(generator = "UUID")
    @Column(name = "id", updatable = false, nullable = false)
    private UUID id;

    @Column(name = "question_text", nullable = false)
    private String questionText;

    private boolean isRoot;

//    @OneToMany(cascade =  { CascadeType.PERSIST, CascadeType.MERGE }, fetch = FetchType.EAGER)
//    private Set<Answer> answers;

}
