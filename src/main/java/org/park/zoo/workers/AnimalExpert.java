package org.park.zoo.workers;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.park.zoo.animals.Animal;
import org.park.zoo.animals.Carnivore;
import org.park.zoo.animals.Herbivorous;
import org.park.zoo.animals.Zebra;
import org.park.zoo.animals.exceptions.AnimalDoesNotExist;

public class AnimalExpert extends Employee {
    private static final Logger logger = LogManager.getLogger(AnimalExpert.class);

    public AnimalExpert(String name, String surname, int age, int salary) {
        super(name, surname, age, salary);
    }

    public static void feedAnimal(Animal animal) throws AnimalDoesNotExist {
        if (animal instanceof Carnivore && animal instanceof Herbivorous) {
            logger.info("Gave {} kilos of food to: {}", calculateFoodAmount(animal.getWeight()), animal.getClass().getSimpleName());
        } else if (animal instanceof Carnivore) {
            logger.info("Gave {} kilos of food to: {}", calculateFoodAmount(animal.getWeight()), animal.getClass().getSimpleName());
        } else if (animal instanceof Herbivorous) {
            logger.info("Gave {} kilos of food to: {}", calculateFoodAmount(animal.getWeight()), animal.getClass().getSimpleName());
        } else throw new AnimalDoesNotExist("There is no such animal in the zoo!!!");
    }

    public static int calculateFoodAmount(int animalWeight) {
        return animalWeight / 20;
    }

    public static void main(String[] args) throws AnimalDoesNotExist {
        Zebra zebra = new Zebra("Po ", 4, "Africa", 15, 45, 200);
        System.out.println(zebra);
        feedAnimal(zebra);

    }
}

