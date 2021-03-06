package org.park.zoo.animals;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;


public class Wolf extends Animal implements Carnivore {

    private static final Logger logger = LogManager.getLogger(Wolf.class);

    private String color;

    public Wolf(String name, int age, String country, int minTemperature, int maxTemperature, int weight, String color) {
        super(name, age, country, minTemperature, maxTemperature, weight);
        this.color = color;
    }

    public Wolf(String id, String name, int age, String country, int minTemperature, int maxTemperature, int weight, String color) {
        super(id, name, age, country, minTemperature, maxTemperature, weight);
        this.color = color;
    }

    public Wolf() {
    }

    @Override
    public void drinkWater() {
        logger.info("Wolf " + getName() + " drink water");
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }
}



