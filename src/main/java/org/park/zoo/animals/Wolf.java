package org.park.zoo.animals;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class Wolf extends Animal implements Carnivore {
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


