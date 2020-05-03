package org.park.zoo.animals;

public class Animal {

    String name;
    int age;
    String country;
    public int minTemperature;
    public int maxTemperature;
    int weight;

    public Animal(String name, int age, String country, int minTemperature, int maxTemperature, int weight) {
        this.name = name;
        this.age = age;
        this.country = country;
        this.minTemperature = minTemperature;
        this.maxTemperature = maxTemperature;
        this.weight = weight;
    }

    public void drinkWater() {
        System.out.println("Animal drinks");
    }

    @Override
    public String toString() {
        return "Animal{" +
                "name='" + name + '\'' +
                ", age=" + age +
                ", country='" + country + '\'' +
                ", minTemperature=" + minTemperature +
                ", maxTemperature=" + maxTemperature +
                ", weight=" + weight +
                '}';
    }
}




