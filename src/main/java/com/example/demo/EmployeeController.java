package com.example.demo;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/employees")
public class EmployeeController {

    @Autowired
    private EmployeeRepository repo;

    // CREATE
    @PostMapping
    public Employee addEmployee(@RequestBody Employee e) {
        return repo.save(e);
    }

    // READ ALL
    @GetMapping
    public List<Employee> getAllEmployees() {
        return repo.findAll();
    }

    // READ BY ID
    @GetMapping("/{id}")
    public Employee getEmployeeById(@PathVariable int id) {
        return repo.findById(id).orElse(null);
    }

    // UPDATE
    @PutMapping("/{id}")
    public Employee updateEmployee(@PathVariable int id, @RequestBody Employee e) {
        Employee emp = repo.findById(id).orElse(null);
        if (emp != null) {
            emp.setName(e.getName());
            emp.setSalary(e.getSalary());
            return repo.save(emp);
        }
        return null;
    }

    // DELETE
    @DeleteMapping("/{id}")
    public String deleteEmployee(@PathVariable int id) {
        repo.deleteById(id);
        return "Employee Deleted";
    }
}
