package org.park.zoo.animals;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class Zebra extends Animal implements Herbivorous {

    private static final Logger logger = LogManager.getLogger(Zebra.class);

    public Zebra(String name, int age, String country, int minTemperature, int maxTemperature, int weight) {
        super(name, age, country, minTemperature, maxTemperature, weight);
    }

    public Zebra(String id, String name, int age, String country, int minTemperature, int maxTemperature, int weight) {
        super(id, name, age, country, minTemperature, maxTemperature, weight);
    }

    public Zebra() {
    }

    @Override
    public void drinkWater() {
        logger.info("Zebra " + getName() + "drinks water");
    }

}
