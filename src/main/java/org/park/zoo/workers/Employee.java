package org.park.zoo.workers;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class Employee {
    private static final Logger logger = LogManager.getLogger(Employee.class);
    private String name;
    private String surname;
    private int age;
    private int salary;
    private int workedHours;


    public Employee(String name, String surname, int age, int salary, int workedHours) {
        this.name = name;
        this.surname = surname;
        this.age = age;
        this.salary = salary;
        this.workedHours = workedHours;
    }

    public int submitWorkHours(int hours) {
        return workedHours += hours;
    }

    public String getName() {
        return name;
    }

    public String getSurname() {
        return surname;
    }

    public int getAge() {
        return age;
    }

    public int getSalary() {
        return salary;
    }

    public int getWorkedHours() {
        return workedHours;
    }
}



