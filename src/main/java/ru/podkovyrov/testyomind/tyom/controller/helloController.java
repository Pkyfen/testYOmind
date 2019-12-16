package ru.podkovyrov.testyomind.tyom.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class helloController {

    @GetMapping("/hello")
    public String hello(){
        return "Hello, it's tYOm";
    }
}
