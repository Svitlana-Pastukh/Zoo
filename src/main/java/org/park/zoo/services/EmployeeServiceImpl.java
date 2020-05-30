package org.park.zoo.services;

import com.fasterxml.jackson.core.JsonProcessingException;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.park.zoo.animals.Animal;
import org.park.zoo.animals.exceptions.AnimalDoesNotExist;
import org.park.zoo.animals.exceptions.AnimalNotFound;
import org.park.zoo.animals.exceptions.EmployeeNotFound;
import org.park.zoo.repositories.AnimalRepository;
import org.park.zoo.repositories.AnimalRepositoryImpl;
import org.park.zoo.repositories.EmployeeRepository;
import org.park.zoo.repositories.EmployeeRepositoryImpl;
import org.park.zoo.workers.AnimalExpert;
import org.park.zoo.workers.Employee;
import org.park.zoo.workers.Vet;

import java.sql.SQLException;
import java.util.List;

public class EmployeeServiceImpl implements EmployeeService {
    private static final Logger logger = LogManager.getLogger(EmployeeServiceImpl.class);
    private final EmployeeRepository employeeRepository;
    private final AnimalRepository animalRepository;

    public EmployeeServiceImpl(AnimalRepository animalRepository, EmployeeRepository employeeRepository) {

        this.animalRepository = animalRepository;
        this.employeeRepository = employeeRepository;
    }

    public EmployeeServiceImpl() throws SQLException, JsonProcessingException {

        this.animalRepository = new AnimalRepositoryImpl();
        this.employeeRepository = new EmployeeRepositoryImpl();
    }

    @Override
    public List<Employee> selectAllEmployees() throws SQLException, JsonProcessingException {
        return employeeRepository.selectAllEmployees();
    }

    @Override
    public Employee selectEmployeeById(String id) throws SQLException, JsonProcessingException, EmployeeNotFound {
        Employee employee = employeeRepository.selectEmployeeById(id);
        if (employee != null && !id.isBlank()) {
            return employee;
        } else {
            throw new EmployeeNotFound("Cannot find an employee");
        }
    }

    @Override
    public void updateEmployee(Employee employee) throws SQLException, JsonProcessingException {
        employeeRepository.insertEmployee(employee);
    }

    @Override
    public void deleteEmployee(String id) throws SQLException, JsonProcessingException, EmployeeNotFound {
        if (id != null && !id.isBlank()) {
            employeeRepository.deleteEmployee(id);
        } else {
            throw new EmployeeNotFound("inappropriate id");
        }
    }

    @Override
    public Employee selectEmployeeByPosition(String position) throws SQLException, JsonProcessingException, EmployeeNotFound {

        if (position != null && !position.isBlank()) {
            return employeeRepository.selectEmployeeByPosition(position);
        } else {
            throw new EmployeeNotFound("inappropriate position");
        }
    }

    @Override
    public void initialize() throws SQLException, JsonProcessingException {
        employeeRepository.initialize();
    }

    @Override
    public void checkAnimal(Animal animal) throws SQLException, JsonProcessingException, EmployeeNotFound {
        List<Employee> employees = employeeRepository.selectAllEmployees();
        Vet vet = findVet(employees);
        if (vet != null) {
            vet.checkAnimal(animal);
        } else throw new EmployeeNotFound("Cannot find Vet employee");

    }

    @Override
    public void feedAnimal(Animal animal) throws SQLException, JsonProcessingException, AnimalDoesNotExist, EmployeeNotFound {
        Employee employee = employeeRepository.selectEmployeeByPosition("AnimalExpert");
        if (employee instanceof AnimalExpert) {
    ((AnimalExpert) employee).feedAnimal(animal);
        }else throw new EmployeeNotFound("It is not an AnimalExpert");

    }

    @Override
    public int calculateBonus(Employee employee) {
        return 0;
    }

    @Override
    public int paySalary(Employee employee) {
        return 0;
    }


    private Vet findVet(List<Employee> employees) {
        for (Employee a : employees) {
            if (a instanceof Vet) {
                return (Vet) a;
            }
        }
        return null;
    }
}

