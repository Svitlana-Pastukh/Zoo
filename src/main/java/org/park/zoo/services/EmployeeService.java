package org.park.zoo.services;

import com.fasterxml.jackson.core.JsonProcessingException;
import org.park.zoo.animals.exceptions.EmployeeNotFound;
import org.park.zoo.workers.Employee;

import java.sql.SQLException;
import java.util.List;

public interface EmployeeService {
    Employee createEmployee(String employeeJson) throws SQLException, JsonProcessingException;

    List<Employee> selectAllEmployees() throws SQLException, JsonProcessingException;

    Employee selectEmployeeById(String id) throws SQLException, JsonProcessingException, EmployeeNotFound;

    Employee updateEmployee(Employee employee) throws SQLException, JsonProcessingException;

    void deleteEmployee(String id) throws SQLException, JsonProcessingException, EmployeeNotFound;

    Employee selectEmployeeByPosition(String position) throws SQLException, JsonProcessingException, EmployeeNotFound;

    void initialize() throws SQLException, JsonProcessingException;

    int calculateBonus(Employee employee) throws SQLException, JsonProcessingException, EmployeeNotFound;

    int paySalary(Employee employee) throws EmployeeNotFound, SQLException, JsonProcessingException;
}
