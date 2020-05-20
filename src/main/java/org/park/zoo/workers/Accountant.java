package org.park.zoo.workers;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class Accountant extends Employee {
    private static final Logger logger = LogManager.getLogger(Accountant.class);

    private static final int WORK_HOURS_PER_MONTH = 200;
    private static final int BONUS_RATE = 2;

    public Accountant(String name, String surname, int age, int salary) {
        super(name, surname, age, salary);
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

    public static void main(String[] args) {
        Director director = new Director("JJ", "Nest", 45, 5000);
        director.submitWorkHours(210);
        Accountant accountant = new Accountant("Anna", "Gray", 22, 2500);
        accountant.submitWorkHours(250);
        logger.info(accountant.calculateBonus(director));

    }
}

