package org.park.zoo.animals;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class Animal {

    private static final Logger logger = LogManager.getLogger(Animal.class);

    String name;
    int age;
    String country;
    public int minTemperature;
    public int maxTemperature;
    int weight;

    public Animal(String name, int age, String country, int minTemperature, int maxTemperature, int weight) {
        this.name = name;
        this.age = age;
        this.country = country;
        this.minTemperature = minTemperature;
        this.maxTemperature = maxTemperature;
        this.weight = weight;
    }

    public void drinkWater() {
        logger.info("Animal drinks");
    }

    @Override
    public String toString() {
        return "Animal{" +
                "name='" + name + '\'' +
                ", age=" + age +
                ", country='" + country + '\'' +
                ", minTemperature=" + minTemperature +
                ", maxTemperature=" + maxTemperature +
                ", weight=" + weight +
                '}';
    }
}




