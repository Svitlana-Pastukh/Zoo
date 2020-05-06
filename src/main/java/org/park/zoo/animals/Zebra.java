package org.park.zoo.animals;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.park.Insertable;

public class Zebra extends Animal implements Herbivorous, Insertable {
    private final Logger logger = LogManager.getLogger(Zebra.class);

    public Zebra(String name, int age, String country, int minTemperature, int maxTemperature, int weight) {
        super(name, age, country, minTemperature, maxTemperature, weight);
    }

    @Override
    public void drinkWater() {
        logger.info("Zebra " + name + "drinks water");
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
