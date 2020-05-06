package org.park;

import org.park.zoo.animals.*;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class DataBaseInitialization {

    private static final String url = "jdbc:h2:mem:";

    public static void main(String[] args) {


        createAnimalsTable();
    }

    public static void createAnimalsTable() {

        try (Connection connection = DriverManager.getConnection(url);
             Statement statement = connection.createStatement()) {

            statement.execute("CREATE TABLE animals( " +
                    "animal_type TEXT," +
                    "name TEXT," +
                    "age int," +
                    "country TEXT," +
                    "minTemperature int," +
                    "maxTemperature int," +
                    "weight int," +
                    "color TEXT)");

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

            insertAnimals(animals, statement);

            ResultSet rs = statement.executeQuery("SELECT * FROM animals");

            while (rs.next()) {
                System.out.println();
                System.out.print(" " + rs.getString(1));
                System.out.print(" " + rs.getString(2));
                System.out.print(" " + rs.getString(8));

            }


        } catch (SQLException e) {
            e.printStackTrace();
        }

    }

    public static void insertAnimals(List<Animal> list, Statement statement) throws SQLException {
        for (Animal animal : list) {
            if (animal instanceof Insertable) {
                statement.execute(((Insertable) animal).createInsertQuery());
            }
        }
    }
}
