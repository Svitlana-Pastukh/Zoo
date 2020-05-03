package org.park.zoo;

public class Ticket {
    int ticketId;
    int ticketPrice;
    TicketType ticketType;

    public Ticket(int ticketId, int ticketPrice, TicketType ticketType) {
        this.ticketId = ticketId;
        this.ticketPrice = ticketPrice;
        this.ticketType = ticketType;
    }


}

