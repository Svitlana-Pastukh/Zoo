package org.park.zoo.animals;


import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.park.Insertable;

public class Bear extends Animal implements Herbivorous, Carnivore, Insertable {

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

    public void startHibernate() {
        logger.info("Bear " + name + " started hibernating");
        hibernating = true;
    }

    public void stopHibernate() {
        logger.info("Bear " + name + " stopped hibernating ");
        hibernating = false;
    }

    public boolean getHibernate() {
        return hibernating;

    }

    @Override
    public void drinkWater() {
        logger.info("Bear " + name + " drinks water ");
    }


    @Override
    public String createInsertQuery() {
        return String.format("INSERT INTO animals(" +
                        "animal_type," +
                        "id," +
                        "name," +
                        "age," +
                        "country," +
                        "minTemperature," +
                        "maxTemperature," +
                        "weight," +
                        "color)" +
                        "VALUES ('%s', '%s', '%s', %s, '%s', %s, %s, %s, '%s');",
                getClass().getSimpleName(), getId(), name, age, country, minTemperature, maxTemperature, weight, color);
    }


    @Override
    public String toString() {
        return "Bear{" +
                "name='" + name + '\'' +
                ", age=" + age +
                ", country='" + country + '\'' +
                ", minTemperature=" + minTemperature +
                ", maxTemperature=" + maxTemperature +
                ", weight=" + weight +
                ", color='" + color + '\'' +
                '}';
    }
}
