package org.park.zoo.workers;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.park.zoo.animals.Animal;

public class Vet extends Employee {
    private static final Logger logger = LogManager.getLogger(Vet.class);

    public Vet(String name, String surname, int age, int salary) {
        super(name, surname, age, salary);
    }

    public Vet() {
    }

    public Vet(String employeeId, String name, String surname, int age, int salary) {
        super(employeeId, name, surname, age, salary);
    }

    public void checkAnimal(Animal animal) {

        long currentTimeMinusThirty = minusThirtyDays(System.currentTimeMillis());
        long timeVisit = animal.getLastVetVisit();

        if (timeVisit == 0 || timeVisit < currentTimeMinusThirty) {
            animal.setLastVetVisit(System.currentTimeMillis());
            logger.info("Vet checked animal: {} ", animal.getName());
        } else {
            logger.info("Animal has been already checked in the last 30 days");
        }
    }

    private static long minusThirtyDays(long currentMillis) {
        long thirtyDaysInMillis = 30 * 24 * 60 * 60 * 1000L;
        return currentMillis - thirtyDaysInMillis;
    }
}
