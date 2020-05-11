package org.park.zoo.animals;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonTypeName;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.park.Insertable;

@JsonTypeName("Lion")
public class Lion extends Animal implements Carnivore, Insertable {
    private static final Logger logger = LogManager.getLogger(Lion.class);
    @JsonProperty("@type")
    private final String type = "Lion";

    public Lion(String name, int age, String country, int minTemperature, int maxTemperature, int weight) {
        super(name, age, country, minTemperature, maxTemperature, weight);


    }

    public Lion(String id, String name, int age, String country, int minTemperature, int maxTemperature, int weight) {
        super(id, name, age, country, minTemperature, maxTemperature, weight);
    }
    private Lion(){

    }

    @Override
    public void drinkWater() {

        logger.info("Lion " + name + " drink water");
    }

    @Override
    public String toString() {
        return "Lion{" +
                " name='" + name + '\'' +
                ", age=" + age +
                ", country='" + country + '\'' +
                ", minTemperature=" + minTemperature +
                ", maxTemperature=" + maxTemperature +
                ", weight=" + weight +
                '}';
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
                        "VALUES ('%s', '%s', '%s', %s, '%s', %s, %s, %s, %s);",
                getClass().getSimpleName(), getId(), name, age, country, minTemperature, maxTemperature, weight, null);
    }
}
