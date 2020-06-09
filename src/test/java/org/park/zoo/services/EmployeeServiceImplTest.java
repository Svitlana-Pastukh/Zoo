package org.park.zoo.services;

import com.fasterxml.jackson.core.JsonProcessingException;
import org.junit.jupiter.api.Test;
import org.park.MapperUtil;
import org.park.zoo.animals.exceptions.EmployeeNotFound;
import org.park.zoo.repositories.EmployeeRepository;
import org.park.zoo.repositories.EmployeeRepositoryImpl;
import org.park.zoo.workers.Accountant;
import org.park.zoo.workers.AnimalExpert;
import org.park.zoo.workers.Employee;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class EmployeeServiceImplTest {

    EmployeeRepository repository = mock(EmployeeRepositoryImpl.class);
    EmployeeService service = new EmployeeServiceImpl(repository);

    @Test
    void createEmployee() throws JsonProcessingException, SQLException {
        Employee employee = new Employee();
        assertEquals(service.createEmployee(MapperUtil.createJson(employee)).getEmployeeId(), employee.getEmployeeId());
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
    void selectEmployeeWithIncorrectId() throws SQLException, JsonProcessingException {
        String a = "asd123";
        when(repository.selectEmployeeById(a)).thenReturn(null);
        assertThrows(EmployeeNotFound.class, () -> service.selectEmployeeById(a), "Correct id");
        verify(repository, times(1)).selectEmployeeById(a);
    }

    @Test
    void selectEmployeeWithNullId() throws SQLException, JsonProcessingException {
        String a = null;
        assertThrows(EmployeeNotFound.class, () -> service.selectEmployeeById(a), "Provided id is not null");
        verify(repository, times(0)).selectEmployeeById(a);
    }

    @Test
    void updateEmployee() throws SQLException, JsonProcessingException {
        Employee employee = new Employee();
        when(repository.insertEmployee(employee)).thenReturn(employee);
        assertEquals(employee, service.updateEmployee(employee));
        verify(repository, times(1)).insertEmployee(employee);
    }

    @Test
    void deleteEmployee() throws SQLException, JsonProcessingException, EmployeeNotFound {
        Employee employee = new Employee();
        String a = employee.getEmployeeId();
        when(repository.selectEmployeeById(a)).thenReturn(employee);
        service.deleteEmployee(a);
        verify(repository, times(1)).deleteEmployee(a);
    }

    @Test
    void deleteEmployeeWithIncorrectId() throws SQLException, JsonProcessingException {
        String a = "123asd";
        when(repository.selectEmployeeById(a)).thenReturn(null);
        assertThrows(EmployeeNotFound.class, () -> service.selectEmployeeById(a), "Correct id");
        verify(repository, times(1)).selectEmployeeById(a);
        verify(repository, times(0)).deleteEmployee(a);
    }

    @Test
    void deleteEmployeeWithNull() throws SQLException, JsonProcessingException {
        String a = null;
        when(repository.selectEmployeeById(a)).thenReturn(null);
        assertThrows(EmployeeNotFound.class, () -> service.deleteEmployee(a), "Id not null");
        verify(repository, times(0)).selectEmployeeById(a);
        verify(repository, times(0)).deleteEmployee(a);
    }

    @Test
    void selectEmployeeByPosition() throws EmployeeNotFound, SQLException, JsonProcessingException {
        Accountant accountant = new Accountant("Anna", "Gray", 22, 2500);
        when(repository.selectEmployeeByPosition("Accountant")).thenReturn(accountant);
        assertEquals(accountant, service.selectEmployeeByPosition(accountant.getClass().getSimpleName()));
    }

    @Test
    void selectEmployeeByPositionWithIncorrectPosition() throws SQLException, JsonProcessingException {
        String a = "Doctor123";
        when(repository.selectEmployeeByPosition(a)).thenReturn(null);
        assertThrows(EmployeeNotFound.class, () -> service.selectEmployeeByPosition("Doctor"), "Employee with doctor123 position exist");
        verify(repository, times(0)).selectEmployeeByPosition(a);
    }

    @Test
    void selectEmployeeWithNull() throws SQLException, JsonProcessingException {
        String a = null;
        when(repository.selectEmployeeByPosition(a)).thenReturn(null);
        assertThrows(EmployeeNotFound.class, () -> service.selectEmployeeByPosition(a), "Position is not null");
        verify(repository, times(0)).selectEmployeeByPosition(a);
    }

    @Test
    void initialize() throws SQLException, JsonProcessingException {
        service.initialize();
        verify(repository, times(1)).initialize();
    }

    @Test
    void getInstance() {
        assertNotNull(EmployeeServiceImpl.getInstance());
    }

    @Test
    void calculateBonus() throws SQLException, JsonProcessingException, EmployeeNotFound {
        AnimalExpert animalExpert = new AnimalExpert("Bob", "Ice", 32, 3750);
        animalExpert.setWorkedHours(290);
        Accountant accountant = new Accountant();
        when(repository.selectEmployeeByPosition(Accountant.class.getSimpleName())).thenReturn(accountant);
        service.calculateBonus(animalExpert);
        assertEquals(accountant.calculateBonus(animalExpert), 3240);
        verify(repository, times(1)).selectEmployeeByPosition(Accountant.class.getSimpleName());
    }

    @Test
    void calculateBonusIncorrectEmployee() throws SQLException, JsonProcessingException {
        AnimalExpert animalExpert = new AnimalExpert("Bob", "Ice", 32, 3750);
        animalExpert.setWorkedHours(290);
        String a = "Doctor";
        when(repository.selectEmployeeByPosition(a)).thenReturn(null);
        assertThrows(EmployeeNotFound.class, () -> service.calculateBonus(animalExpert), "Position is Accountant");
        verify(repository, times(0)).selectEmployeeByPosition(a);
    }

    @Test
    void calculateBonusWithNull() throws SQLException, JsonProcessingException {
        AnimalExpert animalExpert = new AnimalExpert("Bob", "Ice", 32, 3750);
        animalExpert.setWorkedHours(290);
        String a = null;
        when(repository.selectEmployeeByPosition(a)).thenReturn(null);
        assertThrows(EmployeeNotFound.class, () -> service.calculateBonus(animalExpert), "Position is not null");
        verify(repository, times(0)).selectEmployeeByPosition(a);
    }

    @Test
    void paySalary() throws SQLException, JsonProcessingException, EmployeeNotFound {
        AnimalExpert animalExpert = new AnimalExpert("Bob", "Ice", 32, 3750);
        animalExpert.setWorkedHours(290);
        Accountant accountant = new Accountant();
        when(repository.selectEmployeeByPosition(Accountant.class.getSimpleName())).thenReturn(accountant);
        assertEquals(6990, service.paySalary(animalExpert));
        verify(repository, times(1)).selectEmployeeByPosition(Accountant.class.getSimpleName());
    }

    @Test
    void paySalaryIncorrectEmployee() throws SQLException, JsonProcessingException {
        AnimalExpert animalExpert = new AnimalExpert("Bob", "Ice", 32, 3750);
        animalExpert.setWorkedHours(290);
        String a = "Doctor";
        when(repository.selectEmployeeByPosition(a)).thenReturn(null);
        assertThrows(EmployeeNotFound.class, () -> service.paySalary(animalExpert), "Position is Accountant");
        verify(repository, times(0)).selectEmployeeByPosition(a);
    }

    @Test
    void paySalaryWithNull() throws SQLException, JsonProcessingException {
        AnimalExpert animalExpert = new AnimalExpert("Bob", "Ice", 32, 3750);
        animalExpert.setWorkedHours(290);
        String a = null;
        when(repository.selectEmployeeByPosition(a)).thenReturn(null);
        assertThrows(EmployeeNotFound.class, () -> service.paySalary(animalExpert), "Position is not null");
        verify(repository, times(0)).selectEmployeeByPosition(a);
    }
}