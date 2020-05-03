package org.park.zoo.animals;

public class Zebra extends Animal implements Herbivorous {

    public Zebra(String name, int age, String country, int minTemperature, int maxTemperature, int weight) {
        super(name, age, country, minTemperature, maxTemperature, weight);
    }

    @Override
    public void drinkWater() {
        System.out.println("Zebra "+ name + "drinks water");
    }
}
