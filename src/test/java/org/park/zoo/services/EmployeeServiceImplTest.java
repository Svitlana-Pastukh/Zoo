package org.park.zoo.services;

import com.fasterxml.jackson.core.JsonProcessingException;
import org.junit.jupiter.api.Test;
import org.park.App;
import org.park.zoo.animals.Animal;
import org.park.zoo.animals.Bear;
import org.park.zoo.animals.exceptions.AnimalDoesNotExist;
import org.park.zoo.animals.exceptions.EmployeeNotFound;
import org.park.zoo.repositories.EmployeeRepository;
import org.park.zoo.repositories.EmployeeRepositoryImpl;
import org.park.zoo.workers.Accountant;
import org.park.zoo.workers.AnimalExpert;
import org.park.zoo.workers.Employee;
import org.park.zoo.workers.Vet;

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
    void deleteEmployee() throws SQLException, JsonProcessingException, EmployeeNotFound {
        Employee employee = new Employee();
        service.deleteEmployee(employee.getEmployeeId());
        verify(repository, times(1)).deleteEmployee(employee.getEmployeeId());
    }

    @Test
    void selectEmployeeByPosition() throws EmployeeNotFound, SQLException, JsonProcessingException {
        Accountant accountant = new Accountant("Anna", "Gray", 22, 2500);
        when(repository.selectEmployeeByPosition("Accountant")).thenReturn(accountant);
        assertEquals(accountant, service.selectEmployeeByPosition(accountant.getClass().getSimpleName()));
    }

    @Test
    void initialize() {
    }

    @Test
    void getInstance() {
    }

    @Test
    void checkAnimal() throws EmployeeNotFound, SQLException, JsonProcessingException {
        Animal animal = new Animal();
        Vet vet = new Vet();
        when(repository.selectEmployeeByPosition(Vet.class.getSimpleName())).thenReturn(vet);
        service.checkAnimal(animal);
        assertTrue(animal.getLastVetVisit() > 0);
        verify(repository, times(1)).selectEmployeeByPosition(Vet.class.getSimpleName());
    }

    @Test
    void checkAnimalNegative() throws SQLException, JsonProcessingException {
        Animal animal = new Animal();
        when(repository.selectEmployeeByPosition(Vet.class.getSimpleName())).thenReturn(null);
        assertThrows(EmployeeNotFound.class, () -> service.checkAnimal(animal), "Cannot find Vet employee");
        assertEquals(animal.getLastVetVisit(), 0);
        verify(repository, times(1)).selectEmployeeByPosition(Vet.class.getSimpleName());
    }

    @Test
    void feedAnimal() throws SQLException, JsonProcessingException, AnimalDoesNotExist, EmployeeNotFound {
        Animal animal = new Bear();
        AnimalExpert animalExpert = new AnimalExpert();
        when(repository.selectEmployeeByPosition(AnimalExpert.class.getSimpleName())).thenReturn(animalExpert);
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
    void paySalary() throws SQLException, JsonProcessingException, EmployeeNotFound {
        AnimalExpert animalExpert = new AnimalExpert("Bob", "Ice", 32, 3750);
        animalExpert.setWorkedHours(290);
        Accountant accountant = new Accountant();
        when(repository.selectEmployeeByPosition(Accountant.class.getSimpleName())).thenReturn(accountant);
        assertEquals(6990, service.paySalary(animalExpert));
        verify(repository, times(1)).selectEmployeeByPosition(Accountant.class.getSimpleName());
    }
}