package ru.podkovyrov.testyomind.tyom.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.podkovyrov.testyomind.tyom.model.Author;
import ru.podkovyrov.testyomind.tyom.model.Status;

import java.util.List;
import java.util.Optional;

@Repository
public interface AuthorRepository extends JpaRepository<Author, Long> {
    Author findByLogin(String login);
    List<Author> findAllByStatusIs(Status status);
    Optional<Author> findByEmail(String email);
}
