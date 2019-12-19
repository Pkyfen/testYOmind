package ru.podkovyrov.testyomind.tyom.service.impl;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import ru.podkovyrov.testyomind.tyom.model.Author;
import ru.podkovyrov.testyomind.tyom.model.Status;
import ru.podkovyrov.testyomind.tyom.service.AuthorService;
import ru.podkovyrov.testyomind.tyom.repository.AuthorRepository;

import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
@Slf4j
public class AuthorServiceImpl implements AuthorService {

    private final AuthorRepository authorRepository;

    @Override
    public Author register(Author author) {
        author.setStatus(Status.ACTIVE);
        Author registeredAuthor = authorRepository.save(author);

        log.info("IN register - Admin: {} successfully registered", registeredAuthor.getLogin());

        return registeredAuthor;
    }

    @Override
    public List<Author> getAll() {
        return authorRepository.findAllByStatusIs(Status.ACTIVE);
    }

    @Override
    public Author findByLogin(String login) {
        return authorRepository.findByLogin(login);
    }

    @Override
    public Author findById(Long id) {
        Author author = authorRepository.findById(id).orElse(null);

        if (author == null) {
            log.warn("IN findById - no user found by id: {}", id);
            return null;
        }

        log.info("IN findById - user: {} found by id: {}", author.getLogin(), id);
        return author;
    }

    @Override
    public Optional<Author> findByEmail(String email) {
        return authorRepository.findByEmail(email);
    }

    @Override
    public void delete(Long id) {
        Author deletedAuthor = authorRepository.findById(id).orElse(null);
        if(deletedAuthor != null){
            deletedAuthor.setStatus(Status.DELETED);
            authorRepository.save(deletedAuthor);
        }
    }

    @Override
    public void save(Author author) {
        authorRepository.save(author);
    }

    @Override
    public void update(Long id, Author author) {
        Author oldInfo = authorRepository.findById(id).orElse(null);
        if(oldInfo!=null){
            author.setYotests(oldInfo.getYotests());
            author.setStatus(oldInfo.getStatus());
            author.setId(oldInfo.getId());

            authorRepository.save(author);
        }
    }
}
