package ru.podkovyrov.testyomind.tyom.controller;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import ru.podkovyrov.testyomind.tyom.model.Author;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static ru.podkovyrov.testyomind.tyom.util.Convert.asJsonString;

@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
@Sql(value = {"/create-author.sql"}, executionPhase = Sql.ExecutionPhase.BEFORE_TEST_METHOD)
@Sql(value = {"/create-author-after.sql"}, executionPhase = Sql.ExecutionPhase.AFTER_TEST_METHOD)
public class AuthorControllerTest {
    @Autowired
    private MockMvc mockMvc;

    @Test
    public void testGetAllTest() throws Exception{
        this.mockMvc.perform(get("/api/v1/author") .accept(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.[*]").exists())
                .andExpect(MockMvcResultMatchers.jsonPath("$.[*].id").isNotEmpty());
    }

    @Test
    public void testGetById() throws Exception{
        this.mockMvc.perform(get("/api/v1/author/{id}",1)
        .accept(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.id").value(1));
    }

    @Test
    public void testAddNewAuthor() throws Exception{
        this.mockMvc.perform(post("/api/v1/author")
        .contentType(MediaType.APPLICATION_JSON)
        .content(asJsonString(new Author("Name","pic","Username", "mail"))))
        .andExpect(status().isOk())
        .andExpect(MockMvcResultMatchers.jsonPath("$.id").exists())
        .andDo(print());
    }

    @Test
    public void testDeleteById() throws Exception{
        this.mockMvc.perform(delete("/api/v1/author/{id}", 1) )
                .andExpect(status().isOk());
        this.mockMvc.perform(get("/api/v1/author/{id}", 1))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.status").value("DELETED"));
    }

}