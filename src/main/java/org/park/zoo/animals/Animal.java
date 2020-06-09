package org.park.zoo.animals;

import com.fasterxml.jackson.annotation.JsonSubTypes;
import com.fasterxml.jackson.annotation.JsonTypeInfo;
import com.fasterxml.jackson.core.JsonProcessingException;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.park.MapperUtil;

import java.io.Serializable;
import java.util.Objects;
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

    private String id;
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

    public Animal() {
        this.id = UUID.randomUUID().toString();
    }

    public String getId() {
        return id;
    }

    public void drinkWater() {
        logger.info("Animal drinks");
    }

    @Override
    public String toString() {
        try {
            return MapperUtil.createJson(this);
        } catch (JsonProcessingException e) {
            logger.error(e);
        }
        return null;
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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Animal animal = (Animal) o;
        return age == animal.age &&
                minTemperature == animal.minTemperature &&
                maxTemperature == animal.maxTemperature &&
                weight == animal.weight &&
                id.equals(animal.id) &&
                Objects.equals(name, animal.name) &&
                Objects.equals(country, animal.country);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, age, country, minTemperature, maxTemperature, weight);
    }
}




