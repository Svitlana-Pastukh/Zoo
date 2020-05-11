package org.park.zoo.animals;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonTypeName;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.park.Insertable;

@JsonTypeName("Wolf")
public class Wolf extends Animal implements Carnivore, Insertable {
    private static final Logger logger = LogManager.getLogger(Wolf.class);
    @JsonProperty("@type")
    private final String type = "Wolf";
    private final String color;

    public Wolf(String name, int age, String country, int minTemperature, int maxTemperature, int weight, String color) {
        super(name, age, country, minTemperature, maxTemperature, weight);
        this.color = color;
    }

    public Wolf(String id, String name, int age, String country, int minTemperature, int maxTemperature, int weight, String color) {
        super(id, name, age, country, minTemperature, maxTemperature, weight);
        this.color = color;
    }

    private Wolf() {
        color = "Black";
    }

    @Override
    public void drinkWater() {
        logger.info("Wolf " + name + " drink water");
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
        return "Wolf{" +
                "color='" + color + '\'' +
                ", name='" + name + '\'' +
                ", age=" + age +
                ", country='" + country + '\'' +
                ", minTemperature=" + minTemperature +
                ", maxTemperature=" + maxTemperature +
                ", weight=" + weight +
                '}';
    }
}


