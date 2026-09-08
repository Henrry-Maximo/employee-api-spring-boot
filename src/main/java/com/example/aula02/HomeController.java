package com.example.aula02;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDateTime;

@RestController()
public class HomeController {

    @GetMapping()
    public String HelloWorld() {
        return "Hello World";
    }

    @GetMapping("/now")
    public LocalDateTime Now() {
        return LocalDateTime.now();
    }
}

/*
    @GetMapping("/employee")
    public Employee TestEmployee() {
        return new Employee("Henrique");
    }
*/