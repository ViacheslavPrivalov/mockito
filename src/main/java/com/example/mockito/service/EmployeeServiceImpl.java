package com.example.mockito.service;

import com.example.mockito.exceptions.EmployeeAlreadyAddedException;
import com.example.mockito.exceptions.EmployeeNotFoundException;
import com.example.mockito.model.Employee;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Collectors;

import static java.util.stream.Collectors.groupingBy;

@Service
public class EmployeeServiceImpl implements EmployeeService {

    private Map<String, Employee> mapEmployeeBook = new HashMap<>();

    @Override
    public Employee addEmployee(String firstName, String lastName, Integer departmentId, Double salary) {
        String key = firstName + " " + lastName;
        if (mapEmployeeBook.containsKey(key)) {
            throw new EmployeeAlreadyAddedException();
        }
        Employee employee = new Employee(firstName, lastName, departmentId, salary);
        mapEmployeeBook.put(key, new Employee(firstName, lastName, departmentId, salary));
        return employee;
    }


    @Override
    public Employee removeEmployee(String firstName, String lastName) {
        String key = firstName + " " + lastName;
        if (mapEmployeeBook.containsKey(key)) {
            return mapEmployeeBook.remove(key);
        }
        throw new EmployeeNotFoundException();
    }

    @Override
    public Employee findEmployee(String firstName, String lastName) {
        String key = firstName + " " + lastName;
        if (mapEmployeeBook.containsKey(key)) {
            return mapEmployeeBook.get(key);
        }
        throw new EmployeeNotFoundException();
    }

    @Override
    public Collection<Employee> findAll() {
        return Collections.unmodifiableCollection(mapEmployeeBook.values());
    }
}
