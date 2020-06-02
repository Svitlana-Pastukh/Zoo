package org.park.zoo.workers;

import org.junit.jupiter.api.Test;
import org.park.zoo.animals.Animal;

import static org.junit.jupiter.api.Assertions.*;

class VetTest {

    @Test
    void checkAnimalWithNoVetVisit() {
        Animal animal = new Animal();
        Vet vet = new Vet();
        vet.checkAnimal(animal);
        assertTrue(animal.getLastVetVisit() > 0);
    }

    @Test
    void checkAnimalWithRecentVetVisit() {
        Animal animal = new Animal();
        long current = System.currentTimeMillis();
        animal.setLastVetVisit(current);
        Vet vet = new Vet("Samm", "White", 27, 4500);
        vet.checkAnimal(animal);
        assertEquals(animal.getLastVetVisit(), current);
    }
}