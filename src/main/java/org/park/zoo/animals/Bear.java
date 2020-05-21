package org.park.zoo.animals;


import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class Bear extends Animal implements Herbivorous, Carnivore {

    private static final Logger logger = LogManager.getLogger(Bear.class);

    private final String color;
    private boolean hibernating;

    public Bear(String name, int age, String country, int minTemperature, int maxTemperature, int weight, String color) {
        super(name, age, country, minTemperature, maxTemperature, weight);
        this.color = color;
    }

    public Bear(String id, String name, int age, String country, int minTemperature, int maxTemperature, int weight, String color) {
        super(id, name, age, country, minTemperature, maxTemperature, weight);
        this.color = color;
    }

    private Bear() {

        color = "brown";
    }

    public void startHibernate() {
        logger.info("Bear " + getName() + " started hibernating");
        hibernating = true;
    }

    public void stopHibernate() {
        logger.info("Bear " + getName() + " stopped hibernating ");
        hibernating = false;
    }

    public boolean getHibernating() {
        return hibernating;
    }

    public String getColor() {
        return color;
    }

    @Override
    public void drinkWater() {
        logger.info("Bear " + getName() + " drinks water ");
    }

}
