package org.park.zoo.animals;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonTypeName;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.park.Insertable;

@JsonTypeName("Zebra")
public class Zebra extends Animal implements Herbivorous, Insertable {
    private static final Logger logger = LogManager.getLogger(Zebra.class);
    @JsonProperty("@type")
    private final String type = "Zebra";

    public Zebra(String name, int age, String country, int minTemperature, int maxTemperature, int weight) {
        super(name, age, country, minTemperature, maxTemperature, weight);
    }

    public Zebra(String id, String name, int age, String country, int minTemperature, int maxTemperature, int weight) {
        super(id, name, age, country, minTemperature, maxTemperature, weight);
    }
    private Zebra(){

    }

    @Override
    public void drinkWater() {
        logger.info("Zebra " + name + "drinks water");
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


    @Override
    public String toString() {
        return "Zebra{" +
                " name='" + name + '\'' +
                ", age=" + age +
                ", country='" + country + '\'' +
                ", minTemperature=" + minTemperature +
                ", maxTemperature=" + maxTemperature +
                ", weight=" + weight +
                '}';
    }
}
