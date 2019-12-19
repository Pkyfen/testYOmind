package ru.podkovyrov.testyomind.tyom.service;

import ru.podkovyrov.testyomind.tyom.model.Author;

import java.util.List;
import java.util.Optional;

public interface AuthorService {
    Author register(Author author);
    Author findByLogin(String login);
    Author findById(Long id);
    Optional<Author> findByEmail(String email);

    List<Author> getAll();
    

    void delete(Long id);
    void save(Author author);
    void update(Long id, Author author);
}
