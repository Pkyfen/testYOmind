package ru.podkovyrov.testyomind.tyom.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;

import java.util.List;

import static ru.podkovyrov.testyomind.tyom.model.EntityConstrainConstants.NOT_EMPTY;

@Entity
@Data
@Table(name = "Check")
public class Check {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "Title")
    @NotBlank(message = "Title " + NOT_EMPTY)
    private String title;

    @Column(name = "Join_key")
    private String joinKey;

    @Column(name = "Current_question")
    private Long currentQuestion;

    @ManyToOne()
    @JoinColumn(name = "Author_id")
    @JsonIgnore
    private Author author;

    @Enumerated(EnumType.STRING)
    @Column(name = "status")
    private Status status;

    @OneToMany(mappedBy = "check", cascade = CascadeType.ALL)
    private List<User> User;
}

