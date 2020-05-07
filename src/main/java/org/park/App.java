package org.park;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.park.zoo.Zoo;
import org.park.zoo.animals.*;

import java.util.ArrayList;
import java.util.List;

public class App {
    private static final Logger logger = LogManager.getLogger(App.class);

    public static void main(String[] args) {
        List<Animal> list = createAnimals();
        giveWaterToAnimals(list);
        feedAnimals(list);
    }

    public static List<Animal> createAnimals() {
        List<Animal> animals = new ArrayList<>();
        Giraffe giraffe = new Giraffe("Tim", 1, "Africa", 15, 50, 800);
        Bear bear = new Bear("Fred", 5, "USA", -10, 25, 400, "Black");
        Wolf wolf = new Wolf("Moon", 2, "North America", -10, 25, 50, "Black");
        Lion lion = new Lion("Sara", 7, "Africa", 15, 50, 400);
        Zebra zebra = new Zebra("Po ", 4, "Africa", 15, 45, 200);

        animals.add(bear);
        animals.add(wolf);
        animals.add(lion);
        animals.add(zebra);
        animals.add(giraffe);
        return animals;
    }

    public static void giveWaterToAnimals(List<Animal> list) {

        for (Animal a : list) {
            a.drinkWater();
        }
    }

    public static void feedAnimals(List<Animal> list) {
        for (Animal a : list) {
            if (a instanceof Carnivore) {
                logger.info("gave meat to animal "+ a.getClass().getSimpleName());
                ((Carnivore) a).eatMeat();
            }
            if (a instanceof Herbivorous) {
                logger.info("gave plants to animal "+ a.getClass().getSimpleName());
                ((Herbivorous) a).eatPlants();
            }

        }

    }

}