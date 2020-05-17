package org.park.zoo.animals;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonTypeName;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

@JsonTypeName("Lion")
public class Lion extends Animal implements Carnivore {

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

        logger.info("Lion " + name + " drinks water");
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
}
