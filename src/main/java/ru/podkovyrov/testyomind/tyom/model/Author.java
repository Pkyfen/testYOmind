package ru.podkovyrov.testyomind.tyom.model;
import lombok.AllArgsConstructor;
import lombok.Data;

import javax.persistence.*;
import javax.validation.constraints.Size;
import java.util.List;

import static ru.podkovyrov.testyomind.tyom.model.EntityConstrainConstants.MAX_ACCOUNT_NAME;

@Entity
@Data
@Table(name = "Author")
public class Author {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "name")
    private String name;

    @Column(name = "login")
    @Size(message = "Login not be more than " + MAX_ACCOUNT_NAME + " characters", max = MAX_ACCOUNT_NAME)
    private String login;

    @OneToMany(mappedBy = "author", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private List<Yotest> yotests;

    @Enumerated(EnumType.STRING)
    @Column(name = "status")
    private Status status;

    @Column(name = "email")
    private String email;

    @Column(name = "userpic")
    private String userpic;
}
