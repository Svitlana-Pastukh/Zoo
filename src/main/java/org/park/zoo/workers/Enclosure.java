package org.park.zoo.workers;

import org.park.zoo.animals.Animal;
import org.park.zoo.animals.exceptions.AnimalCouldNotBeAdded;

import java.util.LinkedList;
import java.util.List;


public class Enclosure {
    private static int id = 1;
    private final int squareMeters;
    private final int temperature;
    private final int number;
    List<Animal> animals = new LinkedList<>();


    public Enclosure(int squareMeters, int temperature) {
        this.squareMeters = squareMeters;
        this.temperature = temperature;
        this.number = id++;
    }

    public void addAnimal(Animal animal) throws AnimalCouldNotBeAdded {

        int min = animal.getMinTemperature();
        int max = animal.getMaxTemperature();

        if (animalCanBeAdded(animal)) {
            if (acceptableEnclosureTemperature(min, max)) {
                animals.add(animal);
            } else
                throw new AnimalCouldNotBeAdded("Animal cannot be added because the existing animal has inappropriate temperature");
        } else
            throw new AnimalCouldNotBeAdded("The animal cannot be added because the existing animal in the enclosure is of a different type");
    }

    public int getNumber() {
        return number;
    }

    public int getTemperature() {
        return temperature;
    }

    public int getSquareMeters() {
        return squareMeters;
    }

    private boolean acceptableEnclosureTemperature(int minTemperatureForAnimal, int maxTemperatureForAnimal) {
        return temperature >= minTemperatureForAnimal && temperature <= maxTemperatureForAnimal;

    }

    private boolean animalCanBeAdded(Animal animal) {
        if (animals.size() == 0) {
            return true;
        }
        Animal existingAnimal = animals.get(0);
        return existingAnimal.getClass().equals(animal.getClass());

    }


}

