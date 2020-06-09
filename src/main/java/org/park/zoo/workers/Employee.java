package org.park.zoo.workers;

import com.fasterxml.jackson.annotation.JsonSubTypes;
import com.fasterxml.jackson.annotation.JsonTypeInfo;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.Objects;
import java.util.UUID;

import static com.fasterxml.jackson.annotation.JsonTypeInfo.As.PROPERTY;
import static com.fasterxml.jackson.annotation.JsonTypeInfo.Id.NAME;

@JsonTypeInfo(use = NAME, include = PROPERTY, property = "@type")
@JsonSubTypes({
        @JsonSubTypes.Type(value = Accountant.class, name = "Accountant"),
        @JsonSubTypes.Type(value = Director.class, name = "Director"),
        @JsonSubTypes.Type(value = AnimalExpert.class, name = "AnimalExpert"),
        @JsonSubTypes.Type(value = Vet.class, name = "Vet")})


public class Employee {
    private static final Logger logger = LogManager.getLogger(Employee.class);
    private String employeeId;
    private String name;
    private String surname;
    private int age;
    private int salary;
    private int workedHours;


    public Employee(String employeeId, String name, String surname, int age, int salary) {
        this.employeeId = employeeId;
        this.name = name;
        this.surname = surname;
        this.age = age;
        this.salary = salary;
    }

    public Employee(String name, String surname, int age, int salary) {
        this(UUID.randomUUID().toString(), name, surname, age, salary);
    }

    public Employee() {
        this.employeeId = UUID.randomUUID().toString();
    }

    public int submitWorkHours(int hours) {
        return workedHours += hours;
    }

    public String getEmployeeId() {
        return employeeId;
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

    public void setWorkedHours(int workedHours) {
        this.workedHours = workedHours;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Employee employee = (Employee) o;
        return age == employee.age &&
                salary == employee.salary &&
                workedHours == employee.workedHours &&
                employeeId.equals(employee.employeeId) &&
                Objects.equals(name, employee.name) &&
                Objects.equals(surname, employee.surname);
    }

    @Override
    public int hashCode() {
        return Objects.hash(employeeId, name, surname, age, salary, workedHours);
    }
}



