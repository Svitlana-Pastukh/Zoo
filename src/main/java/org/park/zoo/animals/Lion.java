package org.park.zoo.animals;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class Lion extends Animal implements Carnivore {

    private static final Logger logger = LogManager.getLogger(Lion.class);

    public Lion(String name, int age, String country, int minTemperature, int maxTemperature, int weight) {
        super(name, age, country, minTemperature, maxTemperature, weight);
    }

    public Lion(String id, String name, int age, String country, int minTemperature, int maxTemperature, int weight) {
        super(id, name, age, country, minTemperature, maxTemperature, weight);
    }

    private Lion() {

    }

    @Override
    public void drinkWater() {

        logger.info("Lion " + getName() + " drinks water");
    }
}
