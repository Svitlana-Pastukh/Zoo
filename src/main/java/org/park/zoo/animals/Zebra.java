package org.park.zoo.animals;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class Zebra extends Animal implements Herbivorous {
    private final Logger logger = LogManager.getLogger(Zebra.class);

    public Zebra(String name, int age, String country, int minTemperature, int maxTemperature, int weight) {
        super(name, age, country, minTemperature, maxTemperature, weight);
    }

    @Override
    public void drinkWater() {
        logger.info("Zebra "+ name + "drinks water");
    }
}
