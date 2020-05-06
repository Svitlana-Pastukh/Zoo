package org.park.zoo.animals;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.park.Insertable;

public class Wolf extends Animal implements Carnivore, Insertable {
    private final Logger logger = LogManager.getLogger(Wolf.class);
    private final String color;

    public Wolf(String name, int age, String country, int minTemperature, int maxTemperature, int weight, String color) {
        super(name, age, country, minTemperature, maxTemperature, weight);
        this.color = color;
    }

    @Override
    public void drinkWater() {
        logger.info("Wolf " + name + " drink water");
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
                        "VALUES ('%s', '%s', %s, '%s', %s, %s, %s, '%s');",
                getClass().getSimpleName(), name, age, country, minTemperature, maxTemperature, weight, color);
    }


    @Override
    public String toString() {
        return "Wolf{" +
                ", name='" + name + '\'' +
                ", age=" + age +
                ", country='" + country + '\'' +
                ", minTemperature=" + minTemperature +
                ", maxTemperature=" + maxTemperature +
                ", weight=" + weight +
                ", color='" + color + '\'' +
                '}';
    }
}


