package org.park.zoo;


import org.park.zoo.animals.*;
import org.park.zoo.workers.Employee;

import java.util.LinkedList;
import java.util.List;

public class Zoo {
    static final String name = "DOO-DOO";
    static final String street = "Ternopil";
    private static int balance = 0;

    private static List<Ticket> soldTickets = new LinkedList<>();

    private static List<Animal> animals = new LinkedList<>();

    public static void addAnimal(Animal animal) {
        animals.add(animal);
    }

    private static List<Employee> workers = new LinkedList<>();

    public static void addWorkers(Employee employee) {
        workers.add(employee);
    }


    public static void addSoldTicket(Ticket ticket) {
        soldTickets.add(ticket);
    }

    public static int getBalance() {
        int balance = 0;
        for (Ticket ticket : soldTickets) {
            balance = ticket.ticketPrice + balance;
        }
        return balance;
    }

    public static int getLastTicketId() {
        int size = soldTickets.size();
        return soldTickets.get(size - 1).ticketId;
    }






}



