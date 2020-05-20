package org.park.zoo.animals;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonSubTypes;
import com.fasterxml.jackson.annotation.JsonTypeInfo;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.Serializable;
import java.util.UUID;

import static com.fasterxml.jackson.annotation.JsonTypeInfo.As.PROPERTY;
import static com.fasterxml.jackson.annotation.JsonTypeInfo.Id.NAME;

@JsonTypeInfo(use = NAME, include = PROPERTY, property = "@type")
@JsonSubTypes({
        @JsonSubTypes.Type(value = Bear.class, name = "Bear"),
        @JsonSubTypes.Type(value = Giraffe.class, name = "Giraffe"),
        @JsonSubTypes.Type(value = Lion.class, name = "Lion"),
        @JsonSubTypes.Type(value = Wolf.class, name = "Wolf"),
        @JsonSubTypes.Type(value = Zebra.class, name = "Zebra"),
})
public class Animal implements Serializable {

    private static final Logger logger = LogManager.getLogger(Animal.class);

    @JsonProperty("@type")
    private final String type = "Animal";
    private final String id;
    private String name;
    private int age;
    private String country;
    private int minTemperature;
    private int maxTemperature;
    private int weight;
    private long lastVetVisit = 0;

    public Animal(String id, String name, int age, String country, int minTemperature, int maxTemperature, int weight) {
        this.id = id;
        this.name = name;
        this.age = age;
        this.country = country;
        this.minTemperature = minTemperature;
        this.maxTemperature = maxTemperature;
        this.weight = weight;
    }

    public Animal(String name, int age, String country, int minTemperature, int maxTemperature, int weight) {
        this(UUID.randomUUID().toString(), name, age, country, minTemperature, maxTemperature, weight);
    }

    protected Animal() {
        id = UUID.randomUUID().toString();
    }

    public String getId() {
        return id;
    }

    public void drinkWater() {
        logger.info("Animal drinks");
    }

    @Override
    public String toString() {
        return "Animal{" +
                "type='" + type + '\'' +
                ", id='" + id + '\'' +
                ", name='" + name + '\'' +
                ", age=" + age +
                ", country='" + country + '\'' +
                ", minTemperature=" + minTemperature +
                ", maxTemperature=" + maxTemperature +
                ", weight=" + weight +
                ", lastVetVisit=" + lastVetVisit +
                '}';
    }

    public String getType() {
        return type;
    }

    public String getName() {
        return name;
    }

    public int getAge() {
        return age;
    }

    public String getCountry() {
        return country;
    }

    public int getMinTemperature() {
        return minTemperature;
    }

    public int getMaxTemperature() {
        return maxTemperature;
    }

    public int getWeight() {
        return weight;
    }

    public long getLastVetVisit() {
        return lastVetVisit;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public void setWeight(int weight) {
        this.weight = weight;
    }

    public void setLastVetVisit(long lastVetVisit) {
        this.lastVetVisit = lastVetVisit;
    }
}




