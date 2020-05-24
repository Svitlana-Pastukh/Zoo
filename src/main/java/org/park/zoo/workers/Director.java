package org.park.zoo.workers;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class Director extends Employee {
    private static final Logger logger = LogManager.getLogger(Director.class);

    public Director(String name, String surname, int age, int salary) {
        super(name, surname, age, salary);
    }

    public Director(String employeeId, String name, String surname, int age, int salary) {
        super(employeeId, name, surname, age, salary);
    }

    public static void main(String[] args) {
       Director director = new Director("JJ","Nest",45,5000);
       director.submitWorkHours(210);
   }
}

