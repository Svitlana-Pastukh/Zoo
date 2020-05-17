package org.park.zoo.repositories;

import com.fasterxml.jackson.core.JsonProcessingException;
import org.park.zoo.animals.*;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

import static org.park.App.createJson;
import static org.park.App.createObjectFromJson;

public class ZooRepository implements AnimalCrud {

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
            animals.add(createObjectFromJson(resultSet.getString(1)));
        }
        return animals;
    }

    @Override
    public Animal selectAnimalById(String id) throws SQLException, JsonProcessingException {
        Statement statement = connection.createStatement();
        ResultSet resultSet = statement.executeQuery("SELECT animal FROM animals WHERE id='" + id + "';");
        if (resultSet.next()) {
            return createObjectFromJson(resultSet.getString(1));
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
    }
}

