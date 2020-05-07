package org.park;

import org.park.zoo.animals.*;
import org.park.zoo.animals.exceptions.AnimalDoesNotExist;

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
                    "id TEXT," +
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

            Animal a = selectOneAnimal(bear.getId(), statement);
            selectAllAnimals(statement).forEach(e-> System.out.println(e));


//            while (rs.next()) {
//                System.out.println();
//                System.out.print(" " + rs.getString(1));
//                System.out.print(" " + rs.getString(2));
//            }
            System.out.println(a.toString());


        } catch (SQLException | AnimalDoesNotExist e) {
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

    public static Animal selectOneAnimal(String id, Statement statement) throws SQLException, AnimalDoesNotExist {

        ResultSet rs = statement.executeQuery(String.format("SELECT * FROM animals WHERE id='%s'", id));

        if (rs.next()) {
            if (rs.getString(1).equalsIgnoreCase("Lion")) {
                return new Lion(rs.getString(2), rs.getString(3), rs.getInt(4), rs.getString(5),
                        rs.getInt(6), rs.getInt(7), rs.getInt(8));
            } else if (rs.getString(1).equalsIgnoreCase("Giraffe")) {
                return new Giraffe(rs.getString(2), rs.getString(3), rs.getInt(4), rs.getString(5),
                        rs.getInt(6), rs.getInt(7), rs.getInt(8));
            } else if (rs.getString(1).equalsIgnoreCase("Zebra")) {
                return new Zebra(rs.getString(2), rs.getString(3), rs.getInt(4), rs.getString(5),
                        rs.getInt(6), rs.getInt(7), rs.getInt(8));
            } else if (rs.getString(1).equalsIgnoreCase("Wolf")) {
                return new Wolf(rs.getString(2), rs.getString(3), rs.getInt(4), rs.getString(5),
                        rs.getInt(6), rs.getInt(7), rs.getInt(8), rs.getString(9));
            } else if (rs.getString(1).equalsIgnoreCase("Bear")) {
                return new Bear(rs.getString(2), rs.getString(3), rs.getInt(4), rs.getString(5),
                        rs.getInt(6), rs.getInt(7), rs.getInt(8), rs.getString(9));
            } else {
                throw new AnimalDoesNotExist("No animal available of class:" + rs.getString(1));
            }
        } else {
            throw new AnimalDoesNotExist("No animal available with id:" + id);
        }
    }

    public static List<Animal> selectAllAnimals(Statement statement) throws SQLException {
        ResultSet rs = statement.executeQuery(String.format("SELECT * FROM animals"));
        List<Animal> animals = new ArrayList<>();
        while (rs.next()) {
            if (rs.getString(1).equalsIgnoreCase("Lion")) {
                animals.add(new Lion(rs.getString(2), rs.getString(3), rs.getInt(4), rs.getString(5),
                        rs.getInt(6), rs.getInt(7), rs.getInt(8)));
            } else if (rs.getString(1).equalsIgnoreCase("Giraffe")) {
                animals.add(new Giraffe(rs.getString(2), rs.getString(3), rs.getInt(4), rs.getString(5),
                        rs.getInt(6), rs.getInt(7), rs.getInt(8)));
            } else if (rs.getString(1).equalsIgnoreCase("Zebra")) {
                animals.add(new Zebra(rs.getString(2), rs.getString(3), rs.getInt(4), rs.getString(5),
                        rs.getInt(6), rs.getInt(7), rs.getInt(8)));
            } else if (rs.getString(1).equalsIgnoreCase("Wolf")) {
                animals.add(new Wolf(rs.getString(2), rs.getString(3), rs.getInt(4), rs.getString(5),
                        rs.getInt(6), rs.getInt(7), rs.getInt(8), rs.getString(9)));
            } else if (rs.getString(1).equalsIgnoreCase("Bear")) {
                animals.add(new Bear(rs.getString(2), rs.getString(3), rs.getInt(4), rs.getString(5),
                        rs.getInt(6), rs.getInt(7), rs.getInt(8), rs.getString(9)));
            }
        }
        return animals;
    }
}


