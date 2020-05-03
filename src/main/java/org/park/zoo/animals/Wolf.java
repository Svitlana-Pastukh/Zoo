package org.park.zoo.animals;

public class Wolf extends Animal implements Carnivore {
    String color;

    public Wolf(String name, int age, String country, int minTemperature, int maxTemperature, int weight, String color) {
        super(name, age, country, minTemperature, maxTemperature, weight);
        this.color = color;
    }

    @Override
    public void drinkWater() {
        System.out.println("Wolf " + name + " drink water");
    }


    @Override
    public String toString() {
        return "Wolf{" +
                ", name='" + name + '\'' +
                ", age=" + age +
                ", country='" + country + '\'' +
                ", minTemperature=" + minTemperature +
                ", maxTemperature=" + maxTemperature +
                ", weight=" + weight +
                ", color='" + color + '\'' +
                '}';
    }
}


