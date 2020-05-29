package org.park.zoo.repositories;

import com.fasterxml.jackson.core.JsonProcessingException;
import org.park.zoo.workers.Employee;

import java.sql.SQLException;
import java.util.List;

public interface EmployeeRepository {

    void createEmployeesTable() throws SQLException;

    void insertEmployees(List<Employee> list) throws SQLException, JsonProcessingException;

    List<Employee> selectAllEmployees() throws SQLException, JsonProcessingException;

    Employee selectEmployeeById(String id) throws SQLException, JsonProcessingException;

    void insertEmployee(Employee employee) throws SQLException, JsonProcessingException;

    void deleteEmployee(String id) throws SQLException, JsonProcessingException;

    Employee selectEmployeeByPosition(String position) throws SQLException, JsonProcessingException;
}
