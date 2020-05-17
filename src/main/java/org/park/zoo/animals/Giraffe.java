package org.park.zoo.animals;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonTypeName;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;


@JsonTypeName("Giraffe")
public class Giraffe extends Animal implements Herbivorous {

    private static final Logger logger = LogManager.getLogger(Giraffe.class);
    @JsonProperty("@type")
    private final String type = "Giraffe";

    public Giraffe(String name, int age, String country, int minTemperature, int maxTemperature, int weight) {
        super(name, age, country, minTemperature, maxTemperature, weight);
    }

    public Giraffe(String id, String name, int age, String country, int minTemperature, int maxTemperature, int weight) {
        super(id, name, age, country, minTemperature, maxTemperature, weight);
    }

    private Giraffe(){

    }

    @Override
    public void drinkWater() {
        logger.info("Giraffe " + name + " drinks water");
    }

    @Override
    public String toString() {
        return "Giraffe{" +
                " name='" + name + '\'' +
                ", age=" + age +
                ", country='" + country + '\'' +
                ", minTemperature=" + minTemperature +
                ", maxTemperature=" + maxTemperature +
                ", weight=" + weight +
                '}';
    }
}
