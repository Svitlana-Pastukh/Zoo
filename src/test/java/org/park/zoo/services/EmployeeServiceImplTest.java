package org.park.zoo.services;

import com.fasterxml.jackson.core.JsonProcessingException;
import org.junit.jupiter.api.Test;
import org.park.App;
import org.park.zoo.animals.exceptions.EmployeeNotFound;
import org.park.zoo.repositories.EmployeeRepository;
import org.park.zoo.repositories.EmployeeRepositoryImpl;
import org.park.zoo.workers.Accountant;
import org.park.zoo.workers.AnimalExpert;
import org.park.zoo.workers.Employee;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

class EmployeeServiceImplTest {

    EmployeeRepository repository = mock(EmployeeRepositoryImpl.class);
    EmployeeService service = new EmployeeServiceImpl(repository);


    @Test
    void createEmployee() throws JsonProcessingException, SQLException {
        Employee employee = new Employee();
        assertEquals(service.createEmployee(App.createJson(employee)).getEmployeeId(), employee.getEmployeeId());
    }

    @Test
    void selectAllEmployees() throws SQLException, JsonProcessingException {
        List<Employee> employees = new ArrayList<>();
        Accountant accountant = new Accountant("Anna", "Gray", 22, 2500);
        AnimalExpert animalExpert = new AnimalExpert("Bob", "Ice", 32, 3750);
        employees.add(accountant);
        employees.add(animalExpert);
        when(repository.selectAllEmployees()).thenReturn(employees);
        assertEquals(employees, service.selectAllEmployees());
    }

    @Test
    void selectEmployeeById() throws SQLException, JsonProcessingException, EmployeeNotFound {
        Employee employee = new Employee();
        String a = employee.getEmployeeId();
        when(repository.selectEmployeeById(a)).thenReturn(employee);
        assertEquals(employee, service.selectEmployeeById(a));
    }

    @Test
    void updateEmployee() throws SQLException, JsonProcessingException {
        Employee employee = new Employee();
        assertEquals(employee, service.updateEmployee(employee));
        verify(repository, times(1)).insertEmployee(employee);
    }

    @Test
    void deleteEmployee() {
    }

    @Test
    void selectEmployeeByPosition() {
    }

    @Test
    void initialize() {
    }

    @Test
    void getInstance() {
    }

    @Test
    void checkAnimal() {
    }

    @Test
    void feedAnimal() {
    }

    @Test
    void calculateBonus() {
    }

    @Test
    void paySalary() {
    }
}