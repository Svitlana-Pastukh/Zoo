package org.park.zoo.animals;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class Giraffe extends Animal implements Herbivorous {

    private static final Logger logger = LogManager.getLogger(Giraffe.class);

    public Giraffe(String name, int age, String country, int minTemperature, int maxTemperature, int weight) {
        super(name, age, country, minTemperature, maxTemperature, weight);
    }

    public Giraffe(String id, String name, int age, String country, int minTemperature, int maxTemperature, int weight) {
        super(id, name, age, country, minTemperature, maxTemperature, weight);
    }

    public Giraffe() {
    }

    @Override
    public void drinkWater() {
        logger.info("Giraffe " + getName() + " drinks water");
    }

}
