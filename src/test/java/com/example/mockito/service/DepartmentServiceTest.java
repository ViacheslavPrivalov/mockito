package com.example.mockito.service;

import com.example.mockito.model.Employee;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.*;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertIterableEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class DepartmentServiceTest {

    private final EmployeeService employeeServiceMock = mock(EmployeeService.class);

    private static Collection<Employee> employees = List.of(
            new Employee("Ivan", "Ivanov", 1, 50_000.0),
            new Employee("Ivan", "Petrov", 1, 60_000.0),
            new Employee("Petr", "Petrov", 2, 70_000.0),
            new Employee("Petr", "Sidorov", 2, 80_000.0)
    );

    private DepartmentServiceImpl out;

    @BeforeEach
    public void initOut() {
        out = new DepartmentServiceImpl(employeeServiceMock);
    }


    @Test
    public void shouldReturnSalary() {
        when(employeeServiceMock.findAll()).thenReturn(employees);

        assertEquals(out.maxSalary(1), 60_000);
        assertEquals(out.maxSalary(2), 80_000);

        assertEquals(out.minSalary(1), 50_000);
        assertEquals(out.minSalary(2), 70_000);

        assertEquals(out.sumSalary(1), 110_000);
        assertEquals(out.sumSalary(2), 150_000);
    }

    @Test
    public void shouldReturnCollection() {
        when(employeeServiceMock.findAll()).thenReturn(employees);

        Collection<Employee> expected = List.of(
                new Employee("Ivan", "Ivanov", 1, 50_000.0),
                new Employee("Ivan", "Petrov", 1, 60_000.0)
        );

        assertIterableEquals(expected, out.printEmployeesByDepartment(1));
    }

    @Test
    public void shouldReturnMap() {
        when(employeeServiceMock.findAll()).thenReturn(employees);

        Map<Integer, List<Employee>> expected = new HashMap<>();
        expected.put(1, List.of(new Employee("Ivan", "Ivanov", 1, 50_000.0),
                new Employee("Ivan", "Petrov", 1, 60_000.0)));
        expected.put(2, List.of(new Employee("Petr", "Petrov", 2, 70_000.0),
                new Employee("Petr", "Sidorov", 2, 80_000.0)));

        assertEquals(expected, out.printEmployees());
    }
}
