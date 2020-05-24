package org.park.zoo.workers;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.apache.logging.log4j.core.util.JsonUtils;

public class Accountant extends Employee {
    private static final Logger logger = LogManager.getLogger(Accountant.class);

    private static final int WORK_HOURS_PER_MONTH = 200;
    private static final int BONUS_RATE = 2;

    public Accountant(String name, String surname, int age, int salary) {
        super(name, surname, age, salary);
    }

    public Accountant(String employeeId, String name, String surname, int age, int salary) {
        super(employeeId, name, surname, age, salary);
    }

    public int calculateBonus(Employee employee) {

        int hourRate = employee.getSalary() / WORK_HOURS_PER_MONTH;
        int overTime = employee.getWorkedHours() - WORK_HOURS_PER_MONTH;
        if (overTime > 0) {
            return overTime * hourRate * BONUS_RATE;
        } else {
            return 0;
        }
    }

    public int paySalary(Employee employee) {
        int a = employee.getWorkedHours();
        int s = employee.getSalary() + calculateBonus(employee);
        employee.setWorkedHours(0);
        System.out.println(s);
        return s;
    }


    public static void main(String[] args) {

        Director director = new Director("JJ", "Nest", 45, 5000);
        director.submitWorkHours(210);
        Vet vet = new Vet("Samm", "White", 27, 4500);
        vet.submitWorkHours(215);
        Accountant accountant = new Accountant("Anna", "Gray", 22, 2500);
        accountant.submitWorkHours(250);
        accountant.paySalary(vet);
        AnimalExpert animalExpert = new AnimalExpert("Bob", "Ice", 32, 3750);
        animalExpert.submitWorkHours(231);

        logger.info(accountant.calculateBonus(vet));

    }
}

