package com.example.mockito.service;

import com.example.mockito.model.Employee;

import java.util.Collection;
import java.util.List;
import java.util.Map;

public interface DepartmentService {
    Double sumSalary(Integer departmentId);
    Double maxSalary(Integer departmentId);

    Double minSalary(Integer departmentId);

    Collection<Employee> printEmployeesByDepartment(Integer departmentId);

    Map<Integer, List<Employee>> printEmployees();
}
