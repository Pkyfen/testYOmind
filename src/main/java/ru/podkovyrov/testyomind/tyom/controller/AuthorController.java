package ru.podkovyrov.testyomind.tyom.controller;

import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;
import ru.podkovyrov.testyomind.tyom.model.Author;
import ru.podkovyrov.testyomind.tyom.service.AuthorService;

import java.util.List;

import static ru.podkovyrov.testyomind.tyom.controller.Endpoint.API;

@AllArgsConstructor
@RestController
@RequestMapping(value = API+"/author")
public class AuthorController {

    private final AuthorService authorService;

    @GetMapping()
    public List<Author> getAll(){
        return authorService.getAll();
    }


    @GetMapping(path = "{id}")
    public Author getAllById(@PathVariable(name = "id") Long id ){
        return authorService.findById(id);
    }

    @PostMapping()
    public Author addAuthor(@RequestBody Author author){
        return this.authorService.register(author);
    }

    @DeleteMapping("{id}")
    public void delete(@PathVariable(name = "id") Long id){
        authorService.delete(id);
    }

}
