package org.park.zoo.repositories;

import com.fasterxml.jackson.core.JsonProcessingException;
import org.park.zoo.animals.*;
import org.park.zoo.workers.*;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

import static org.park.App.*;

public class ZooRepository implements AnimalCrud, EmployeeCrud {

    private static final String url = "jdbc:h2:mem:";
    private final Connection connection;

    public ZooRepository() throws SQLException, JsonProcessingException {
        connection = DriverManager.getConnection(url);
        initialize();
    }

    @Override
    public void createAnimalsTable() throws SQLException {
        Statement statement = connection.createStatement();
        statement.execute("CREATE TABLE animals(id VARCHAR(40) NOT NULL, animal TEXT, PRIMARY KEY (id));");
    }

    @Override
    public void insertAnimals(List<Animal> list) throws SQLException, JsonProcessingException {
        Statement statement = connection.createStatement();
        for (Animal animal : list) {

            String json = createJson(animal);
            String b = String.format("MERGE INTO animals KEY (id) VALUES ('%s', '%s')", animal.getId(), json);
            statement.execute(b);
        }
    }

    @Override
    public List<Animal> selectAllAnimals() throws SQLException, JsonProcessingException {
        Statement statement = connection.createStatement();
        ResultSet resultSet = statement.executeQuery("SELECT animal FROM animals");
        List<Animal> animals = new ArrayList<>();
        while (resultSet.next()) {
            animals.add(createAnimalFromJson(resultSet.getString(1)));
        }
        return animals;
    }

    @Override
    public Animal selectAnimalById(String id) throws SQLException, JsonProcessingException {
        Statement statement = connection.createStatement();
        ResultSet resultSet = statement.executeQuery("SELECT animal FROM animals WHERE id='" + id + "';");
        if (resultSet.next()) {
            return createAnimalFromJson(resultSet.getString(1));
        } else {
            return null;
        }
    }

    @Override
    public void insertAnimal(Animal animal) throws SQLException, JsonProcessingException {
        Statement statement = connection.createStatement();
        statement.execute(String.format("MERGE INTO animals KEY (id) VALUES ('%s', '%s')", animal.getId(), createJson(animal)));
    }

    @Override
    public void deleteAnimal(String id) throws SQLException {
        Statement statement = connection.createStatement();
        statement.execute("DELETE FROM animals WHERE id='" + id + "';");
    }

    private void initialize() throws SQLException, JsonProcessingException {

        List<Animal> animals = new ArrayList<>();
        Giraffe giraffe = new Giraffe("Tim", 1, "Africa", 15, 50, 800);
        Bear bear = new Bear("Fred", 5, "USA", -10, 25, 400, "Black");
        Wolf wolf = new Wolf("Moon", 2, "North America", -10, 25, 50, "Gray");
        Lion lion = new Lion("Sara", 7, "Africa", 15, 50, 400);
        Zebra zebra = new Zebra("Po ", 4, "Africa", 15, 45, 200);

        animals.add(bear);
        animals.add(wolf);
        animals.add(lion);
        animals.add(zebra);
        animals.add(giraffe);

        createAnimalsTable();
        insertAnimals(animals);

        List<Employee> employees = new ArrayList<>();
        Director director = new Director("JJ", "Nest", 45, 5000);
        Vet vet = new Vet("Samm", "White", 27, 4500);
        Accountant accountant = new Accountant("Anna", "Gray", 22, 2500);
        AnimalExpert animalExpert = new AnimalExpert("Bob", "Ice", 32, 3750);
        employees.add(director);
        employees.add(vet);
        employees.add(accountant);
        employees.add(animalExpert);
    }

    @Override
    public void createEmployeesTable() throws SQLException {

        Statement statement = connection.createStatement();
        statement.execute("CREATE TABLE employees(id VARCHAR(40) NOT NULL, employee TEXT, PRIMARY KEY (id));");
    }

    @Override
    public void insertEmployees(List<Employee> list) throws SQLException, JsonProcessingException {
        Statement statement = connection.createStatement();
        for (Employee employee : list) {

            String json = createJson(employee);
            String b = String.format("MERGE INTO employees KEY (id) VALUES ('%s', '%s')", employee.getEmployeeId(), json);
            statement.execute(b);
        }
    }

    @Override
    public List<Employee> selectAllEmployees() throws SQLException, JsonProcessingException {
        Statement statement = connection.createStatement();
        ResultSet resultSet = statement.executeQuery("SELECT employee FROM employees");
        List<Employee> employees = new ArrayList<>();
        while (resultSet.next()) {
            employees.add(createEmployeeFromJson(resultSet.getString(1)));
        }
        return employees;
    }

    @Override
    public Employee selectEmployeeById(String id) throws SQLException, JsonProcessingException {
        Statement statement = connection.createStatement();
        ResultSet resultSet = statement.executeQuery("SELECT employee FROM employees WHERE id='" + id + "';");
        if (resultSet.next()) {
            return createEmployeeFromJson(resultSet.getString(1));
        } else {
            return null;
        }
    }

    @Override
    public void insertEmployee(Employee employee) throws SQLException, JsonProcessingException {
        Statement statement = connection.createStatement();
        statement.execute(String.format("MERGE INTO employees KEY (id) VALUES ('%s', '%s')", employee.getEmployeeId(), createJson(employee)));
    }

    @Override
    public void deleteEmployee(String id) throws SQLException, JsonProcessingException {
        Statement statement = connection.createStatement();
        statement.execute("DELETE FROM employees WHERE id='" + id + "';");
    }
}

