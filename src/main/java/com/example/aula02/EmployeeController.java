package com.example.aula02;

import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.EntityModel;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

/*
    @GetMapping("/employees")
    public List<Employee> GetAllEmployeeList() {
        return this.repository.findAll();
    }
*/

@RestController()
public class EmployeeController {

    private final EmployeeRepository repository;

    public EmployeeController(EmployeeRepository repository) {
        this.repository = repository;
    }

    @GetMapping("/employee")
    public CollectionModel<EntityModel<Employee>> GetAll() {
        var employees = repository.findAll().stream().map(employee -> EntityModel.of(employee, linkTo(methodOn(EmployeeController.class).getEmployeeById(employee.getId())).withSelfRel(), linkTo(methodOn(EmployeeController.class).GetAll()).withRel("employees"))).toList();

        return CollectionModel.of(employees, linkTo(methodOn(EmployeeController.class).GetAll()).withSelfRel());
    }

    @PostMapping("/employee")
    public Employee createEmployee(@RequestBody Employee newEmployee) {
        return this.repository.save(newEmployee);
    }

    // Optional<Employee>
//    @GetMapping("/employees/{id}")
//    public Employee getEmployeeById(@PathVariable Long id) {
//        return repository.findById(id).orElseThrow(() -> new EmployeeNotFoundException(id));
//    }
    @GetMapping("/employee/{id}")
    public EntityModel<Employee> getEmployeeById(@PathVariable long id) {
        var employee = repository.findById(id).orElseThrow(() -> new EmployeeNotFoundException(id));

        return EntityModel.of(employee, linkTo(methodOn(EmployeeController.class).getEmployeeById(id)).withSelfRel(), linkTo(methodOn(EmployeeController.class).GetAll()).withRel("employees"));
    }

    @PutMapping("/employee/{id}")
    public Employee updateOrCreateEmployee(@RequestBody Employee newEmployee, @PathVariable Long id) {
        return repository.findById(id).map(employee -> {
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

    @GetMapping("/employee/{id}")
    EntityModel<Employee> one(@PathVariable Long id) {
        Employee employee = repository.findById(id).orElseThrow(() -> new EmployeeNotFoundException(id));

        return EntityModel.of(employee, linkTo(methodOn(EmployeeController.class).one(id)).withSelfRel()
                // linkTo(methodOn(EmployeeController.class).all()).withRel("employees")
        );
    }

    @GetMapping("/employee")
    CollectionModel<EntityModel<Employee>> all() {

        List<EntityModel<Employee>> employees = repository.findAll().stream().map(employee -> EntityModel.of(employee, linkTo(methodOn(EmployeeController.class).one(employee.getId())).withSelfRel(), linkTo(methodOn(EmployeeController.class).all()).withRel("employees"))).collect(Collectors.toList());

        return CollectionModel.of(employees, linkTo(methodOn(EmployeeController.class).all()).withSelfRel());
    }

}

// https://spring.io/guides/tutorials/rest
