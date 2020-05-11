package org.park;

import com.fasterxml.jackson.core.JsonProcessingException;
import org.park.zoo.animals.*;
import org.park.zoo.animals.exceptions.AnimalDoesNotExist;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

import static org.park.App.createJson;
import static org.park.App.createObjectFromJson;

public class DataBaseInitialization {

    private static final String url = "jdbc:h2:mem:";

    public static void main(String[] args) {
        createAnimalsTable();
    }

    public static void createAnimalsTable() {

        try (Connection connection = DriverManager.getConnection(url);
             Statement statement = connection.createStatement()) {

            statement.execute("CREATE TABLE animals( id TEXT, animal TEXT)");

            List<Animal> animals = new ArrayList<>();
            Giraffe giraffe = new Giraffe("Tim", 1, "Africa", 15, 50, 800);
            Bear bear = new Bear("Fred", 5, "USA", -10, 25, 400, "Black");
            Wolf wolf = new Wolf("Moon", 2, "North America", -10, 25, 50, "Gray");
            Lion lion = new Lion("Sara", 7, "Africa", 15, 50, 400);
            Zebra zebra = new Zebra("Po ", 4, "Africa", 15, 45, 200);

            animals.add(giraffe);
            animals.add(bear);
            animals.add(wolf);
            animals.add(lion);
            animals.add(zebra);

            insertAnimals(animals, statement); //TODO remove
            selectAllAnimals(statement).forEach(System.out::println); //TODO remove

        } catch (SQLException | JsonProcessingException e) {
            e.printStackTrace();
        }
    }

    public static void insertAnimals(List<Animal> list, Statement statement) throws SQLException, JsonProcessingException {
        for (Animal animal : list) {
                statement.execute(
                        String.format("INSERT INTO animals(id, animal) VALUES ('%s', '%s');",
                        animal.getId(), createJson(animal)));
        }
    }

//    public static Animal selectOneAnimal(String id, Statement statement) throws SQLException, AnimalDoesNotExist {
//
//        ResultSet rs = statement.executeQuery(String.format("SELECT * FROM animals WHERE id='%s'", id));
//
//        if (rs.next()) {
//            if (rs.getString(1).equalsIgnoreCase("Lion")) {
//                return new Lion(rs.getString(2), rs.getString(3), rs.getInt(4), rs.getString(5),
//                        rs.getInt(6), rs.getInt(7), rs.getInt(8));
//            } else if (rs.getString(1).equalsIgnoreCase("Giraffe")) {
//                return new Giraffe(rs.getString(2), rs.getString(3), rs.getInt(4), rs.getString(5),
//                        rs.getInt(6), rs.getInt(7), rs.getInt(8));
//            } else if (rs.getString(1).equalsIgnoreCase("Zebra")) {
//                return new Zebra(rs.getString(2), rs.getString(3), rs.getInt(4), rs.getString(5),
//                        rs.getInt(6), rs.getInt(7), rs.getInt(8));
//            } else if (rs.getString(1).equalsIgnoreCase("Wolf")) {
//                return new Wolf(rs.getString(2), rs.getString(3), rs.getInt(4), rs.getString(5),
//                        rs.getInt(6), rs.getInt(7), rs.getInt(8), rs.getString(9));
//            } else if (rs.getString(1).equalsIgnoreCase("Bear")) {
//                return new Bear(rs.getString(2), rs.getString(3), rs.getInt(4), rs.getString(5),
//                        rs.getInt(6), rs.getInt(7), rs.getInt(8), rs.getString(9));
//            } else {
//                throw new AnimalDoesNotExist("No animal available of class:" + rs.getString(1));
//            }
//        } else {
//            throw new AnimalDoesNotExist("No animal available with id:" + id);
//        }
//    } TODO REWORK

    public static List<Animal> selectAllAnimals(Statement statement) throws SQLException, JsonProcessingException {
        ResultSet resultSet = statement.executeQuery("SELECT animal FROM animals");
        List<Animal> animals = new ArrayList<>();
        while (resultSet.next()) {
            animals.add(createObjectFromJson(resultSet.getString(1)));
        }
        return animals;
    }
}


