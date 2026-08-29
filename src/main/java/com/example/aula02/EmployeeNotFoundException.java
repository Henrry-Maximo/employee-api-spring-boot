package com.example.aula02;

// RuntimeException -> erro de execução
public class EmployeeNotFoundException extends RuntimeException {

    // alterando o constructor da classe
    public EmployeeNotFoundException(Long id) {
        super("Could not find the employee: " + id);
    }
}
