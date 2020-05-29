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

    public AnimalExpert(String employeeId, String name, String surname, int age, int salary) {
        super(employeeId, name, surname, age, salary);
    }

    public void feedAnimal(Animal animal) throws AnimalDoesNotExist {
        if (animal instanceof Carnivore && animal instanceof Herbivorous) {
            logger.info("Gave {} kilos of meat and plants to: {}", calculateFoodAmount(animal.getWeight()), animal.getClass().getSimpleName());
        } else if (animal instanceof Carnivore) {
            logger.info("Gave {} kilos meat to: {}", calculateFoodAmount(animal.getWeight()), animal.getClass().getSimpleName());
        } else if (animal instanceof Herbivorous) {
            logger.info("Gave {} kilos of plants to: {}", calculateFoodAmount(animal.getWeight()), animal.getClass().getSimpleName());
        } else throw new AnimalDoesNotExist("There is no such animal in the zoo!!!");
    }

    private static int calculateFoodAmount(int animalWeight) {
        return animalWeight / 20;
    }

}


