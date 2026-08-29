package com.example.aula02;

import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController()
public class EmployeeController {
    private final EmployeeRepository repository;

    public EmployeeController(EmployeeRepository repository) {
        this.repository = repository;
    }

    @GetMapping("/employees")
    public List<Employee> GetAllEmployeeList() {
        return this.repository.findAll();
    }

    @PostMapping("/employees")
    public Employee NewEmployees(@RequestBody Employee newEmployee) {
        return this.repository.save(newEmployee);
    }

    // Optional<Employee>
    @GetMapping("/employees/{id}")
    public Employee getEmployeeById(@PathVariable Long id) {
        return repository.findById(id).orElseThrow(() -> new EmployeeNotFoundException(id));
    }

    @PutMapping("/employees/{id}")
    public Employee updateOrCreateEmployee(@RequestBody Employee newEmployee, @PathVariable Long id) {
        return repository.findById(id)
                .map(employee -> {
            employee.setName(newEmployee.getName());
            employee.setRole(newEmployee.getRole());

            return repository.save(employee);
        }).orElseGet(() -> {
            return repository.save(newEmployee);
        });

    }

    @DeleteMapping("/employee/{id}")
    public void deleteEmployee(@PathVariable Long id) {
        repository.deleteById(id);
    }

}
