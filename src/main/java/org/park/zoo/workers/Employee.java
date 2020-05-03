package org.park.zoo.workers;

public abstract class Employee {
    public String name;
    public String surname;
    public int age;
    public int salary;
    Position position;


    public Employee(String name, String surname, int age,Position position, int salary) {
        this.name = name;
        this.surname = surname;
        this.age = age;
        this.salary = salary;
        this.position=position;
    }

    @Override
    public String toString() {
        return "Employee{" +
                "name='" + name + '\'' +
                ", surname='" + surname + '\'' +
                ", age=" + age +
                ", position='" + position + '\'' +
                ", salary=" + salary +
                '}';
    }
}

