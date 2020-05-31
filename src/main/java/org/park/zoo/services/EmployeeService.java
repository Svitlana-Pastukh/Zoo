package org.park.zoo.services;

import com.fasterxml.jackson.core.JsonProcessingException;
import org.park.zoo.animals.Animal;
import org.park.zoo.animals.exceptions.AnimalDoesNotExist;
import org.park.zoo.animals.exceptions.EmployeeNotFound;
import org.park.zoo.workers.Employee;

import java.sql.SQLException;
import java.util.List;

public interface EmployeeService {

    List<Employee> selectAllEmployees() throws SQLException, JsonProcessingException;

    Employee selectEmployeeById(String id) throws SQLException, JsonProcessingException, EmployeeNotFound;

    void updateEmployee(Employee employee) throws SQLException, JsonProcessingException;

    void deleteEmployee(String id) throws SQLException, JsonProcessingException, EmployeeNotFound;

    Employee selectEmployeeByPosition(String position) throws SQLException, JsonProcessingException, EmployeeNotFound;

    void initialize() throws SQLException, JsonProcessingException;

    void checkAnimal(Animal animal) throws SQLException, JsonProcessingException, EmployeeNotFound;

    void feedAnimal(Animal animal) throws SQLException, JsonProcessingException, AnimalDoesNotExist, EmployeeNotFound;

    int calculateBonus(Employee employee) throws SQLException, JsonProcessingException, EmployeeNotFound;

    int paySalary(Employee employee) throws EmployeeNotFound, SQLException, JsonProcessingException;
}
