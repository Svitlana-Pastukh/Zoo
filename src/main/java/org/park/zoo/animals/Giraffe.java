package org.park.zoo.animals;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class Giraffe extends Animal implements Herbivorous {

    private static final Logger logger = LogManager.getLogger(Giraffe.class);

    public Giraffe(String name, int age, String country, int minTemperature, int maxTemperature, int weight) {
        super(name, age, country, minTemperature, maxTemperature, weight);
    }

    @Override
    public void drinkWater() {
        logger.info("Giraffe " + name + " drinks water" );
    }


    @Override
    public String toString() {
        return "Giraffe{" +
                "name='" + name + '\'' +
                ", age=" + age +
                ", country='" + country + '\'' +
                ", minTemperature=" + minTemperature +
                ", maxTemperature=" + maxTemperature +
                ", weight=" + weight +
                '}';
    }
}
