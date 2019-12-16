package ru.podkovyrov.testyomind.tyom.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;

import javax.persistence.*;

@Entity
@Data
@Table(name = "User")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "Login")
    private String login;

    @Column(name = "Name")
    private String name;

    @Column(name = "Score")
    private int score;

    @ManyToOne
    @JoinColumn(name = "Check_id")
    @JsonIgnore
    private Check check;

}