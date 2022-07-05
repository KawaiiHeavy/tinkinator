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
import java.util.Objects;
import java.util.UUID;

@Entity
@Table
@Setter
@Getter
@NoArgsConstructor
@ToString
public class Solution {

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

    @Column(name = "solution_text", nullable = false)
    private String solutionText;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "answer_id")
    @OnDelete(action = OnDeleteAction.CASCADE)
    private Answer answer;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Solution solution = (Solution) o;
        return solutionText.equals(solution.solutionText);
    }

    @Override
    public int hashCode() {
        return Objects.hash(solutionText);
    }
}
