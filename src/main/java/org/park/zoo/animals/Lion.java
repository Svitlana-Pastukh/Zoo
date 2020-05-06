package org.park.zoo.animals;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.park.Insertable;

public class Lion extends Animal implements Carnivore, Insertable {
    private final Logger logger = LogManager.getLogger(Lion.class);

    public Lion(String name, int age, String country, int minTemperature, int maxTemperature, int weight) {
        super(name, age, country, minTemperature, maxTemperature, weight);
    }

    @Override
    public void drinkWater() {

        logger.info("Lion " + name + " drink water");
    }


    @Override
    public String toString() {
        return "Lion{" +
                "name='" + name + '\'' +
                ", age=" + age +
                ", country='" + country + '\'' +
                ", minTemperature=" + minTemperature +
                ", maxTemperature=" + maxTemperature +
                ", weight=" + weight +
                '}';
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
}
