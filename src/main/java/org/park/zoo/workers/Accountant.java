package org.park.zoo.workers;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class Accountant extends Employee {
    private static final Logger logger = LogManager.getLogger(Accountant.class);
    private static final int WORK_HOURS_PER_MONTH = 200;
    private static final int BONUS_RATE = 2;

    public Accountant(String name, String surname, int age, int salary, int workedHours) {
        super(name, surname, age, salary, workedHours);
    }

    public void calculateBonus(int salary, int workedHours) {

        int hourRate = salary * WORK_HOURS_PER_MONTH;
        int overTime = workedHours - WORK_HOURS_PER_MONTH;
        int bonus = overTime * hourRate * BONUS_RATE;
        logger.info(bonus);
    }
}
