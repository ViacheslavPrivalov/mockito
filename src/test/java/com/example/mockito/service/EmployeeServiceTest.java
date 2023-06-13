package com.example.mockito.service;

import com.example.mockito.exceptions.EmployeeAlreadyAddedException;
import com.example.mockito.exceptions.EmployeeNotFoundException;
import com.example.mockito.model.Employee;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.*;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.*;

public class EmployeeServiceTest {
    private final EmployeeService out = new EmployeeServiceImpl();

    private static Stream<Arguments> paramsForTest() {
        return Stream.of(
                Arguments.of("Ivan", "Ivanov", 1, 50_000.0),
                Arguments.of("Petr", "Petrov", 1, 60_000.0),
                Arguments.of("Dima", "Ivanov", 2, 70_000.0),
                Arguments.of("Dima", "Petrov", 2, 80_000.0),
                Arguments.of("", "", 1, 0.0),
                Arguments.of("  ", "  ", 100, 1_000_000.0)
        );
    }

    @ParameterizedTest
    @MethodSource("paramsForTest")
    public void shouldReturnEmployee(String firstName, String lastName, Integer id, Double salary) {
        Employee expected = new Employee(firstName, lastName, id, salary);

        Employee actual1 = out.addEmployee(firstName, lastName, id, salary);
        Employee actual2 = out.findEmployee(firstName, lastName);
        Employee actual3 = out.removeEmployee(firstName, lastName);

        assertEquals(actual1, expected);
        assertEquals(actual2, expected);
        assertEquals(actual3, expected);
    }

    @Test
    public void shouldReturnCollection() {
        Employee employee1 = new Employee("Ivan", "Ivanov", 1, 50_000.0);
        Employee employee2 = new Employee("Ivan", "Petrov", 1, 60_000.0);
        Collection<Employee> expected = new ArrayList<>();
        expected.add(employee1);
        expected.add(employee2);

        out.addEmployee("Ivan", "Ivanov", 1, 50_000.0);
        out.addEmployee("Ivan", "Petrov", 1, 60_000.0);
        Collection<Employee> actual = out.findAll();

        assertIterableEquals(expected, actual);
    }

    @Test
    public void shouldThrowEmployeeAlreadyAddedException() {
        out.addEmployee("Ivan", "Ivanov", 1, 50_000.0);
        assertThrows(EmployeeAlreadyAddedException.class,
                () -> out.addEmployee("Ivan", "Ivanov", 1, 50_000.0));
    }

    @Test
    public void shouldThrowEmployeeNotFoundException() {
        assertThrows(EmployeeNotFoundException.class,
                () -> out.findEmployee("Ivan", "Ivanov"));
        assertThrows(EmployeeNotFoundException.class,
                () -> out.removeEmployee("Ivan", "Ivanov"));
    }
}
