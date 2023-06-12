package com.example.mockito.service;

import com.example.mockito.model.Employee;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Collectors;

import static java.util.stream.Collectors.groupingBy;

@Service
public class DepartmentServiceImpl implements DepartmentService {
    private final EmployeeService employeeService;

    public DepartmentServiceImpl(EmployeeService employeeService) {
        this.employeeService = employeeService;
    }

    @Override
    public Double sumSalary(Integer departmentId) {
        return employeeService.findAll().stream()
                .filter(value -> Objects.equals(value.getDepartmentId(), departmentId))
                .mapToDouble(Employee::getSalary)
                .sum();
    }

    @Override
    public Double maxSalary(Integer departmentId) {
        return employeeService.findAll().stream()
                .filter(value -> Objects.equals(value.getDepartmentId(), departmentId))
                .max(Comparator.comparingDouble(employee -> employee.getSalary()))
                .get().getSalary();
    }

    @Override
    public Double minSalary(Integer departmentId) {
        return employeeService.findAll().stream()
                .filter(value -> Objects.equals(value.getDepartmentId(), departmentId))
                .min(Comparator.comparingDouble(employee -> employee.getSalary()))
                .get().getSalary();
    }

    @Override
    public Collection<Employee> printEmployeesByDepartment(Integer departmentId) {
        List<Employee> employeeList = employeeService.findAll().stream()
                .filter(value -> Objects.equals(value.getDepartmentId(), departmentId))
                .collect(Collectors.toList());
        return Collections.unmodifiableList(employeeList);
    }

    @Override
    public Map<Integer, List<Employee>> printEmployees() {
        return employeeService.findAll().stream()
                .collect(groupingBy(employee -> employee.getDepartmentId()));
    }
}
