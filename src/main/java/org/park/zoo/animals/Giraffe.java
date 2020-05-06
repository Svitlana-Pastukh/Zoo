package org.park.zoo.animals;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.park.Insertable;

public class Giraffe extends Animal implements Herbivorous, Insertable {

    private static final Logger logger = LogManager.getLogger(Giraffe.class);

    public Giraffe(String name, int age, String country, int minTemperature, int maxTemperature, int weight) {
        super(name, age, country, minTemperature, maxTemperature, weight);
    }

    @Override
    public void drinkWater() {
        logger.info("Giraffe " + name + " drinks water");
    }

    @Override
    public String createInsertQuery() {
        return String.format("INSERT INTO animals(" +
                        "animal_type," +
                        "name," +
                        "age," +
                        "country," +
                        "minTemperature," +
                        "maxTemperature," +
                        "weight," +
                        "color)" +
                        "VALUES ('%s', '%s', %s, '%s', %s, %s, %s, %s);",
                getClass().getSimpleName(), name, age, country, minTemperature, maxTemperature, weight, null);
    }


    @Override
    public String toString() {
        return "Giraffe{" +
                "name='" + name + '\'' +
                ", age=" + age +
                ", country='" + country + '\'' +
                ", minTemperature=" + minTemperature +
                ", maxTemperature=" + maxTemperature +
                ", weight=" + weight +
                '}';
    }
}
