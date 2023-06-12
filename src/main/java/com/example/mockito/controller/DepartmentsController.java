package com.example.mockito.controller;

import com.example.mockito.model.Employee;
import com.example.mockito.service.DepartmentService;
import com.example.mockito.service.EmployeeService;
import org.springframework.web.bind.annotation.*;

import java.util.Collection;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/departments")
public class DepartmentsController {
    private final DepartmentService departmentService;


    public DepartmentsController(DepartmentService departmentService) {
        this.departmentService = departmentService;
    }

    @GetMapping("/{departmentId}/salary/sum")
    public Double sumSalary(@PathVariable Integer departmentId) {
        return departmentService.sumSalary(departmentId);
    }

    @GetMapping("/{departmentId}/salary/max")
    public Double maxSalary(@PathVariable Integer departmentId) {
        return departmentService.maxSalary(departmentId);
    }

    @GetMapping("/{departmentId}/salary/min")
    public Double minSalary(@PathVariable Integer departmentId) {
        return departmentService.minSalary(departmentId);
    }

    @GetMapping("/{departmentId}/employees")
    public Collection<Employee> printEmployeesByDepartment(@PathVariable Integer departmentId) {
        return departmentService.printEmployeesByDepartment(departmentId);
    }

    @GetMapping("/employees")
    public Map<Integer, List<Employee>> printEmployees() {
        return departmentService.printEmployees();
    }

}
