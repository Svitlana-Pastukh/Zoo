package org.park.zoo;

import java.io.IOException;

public class BoxOffice {
    static int ticketId;
    static int fullPrice = 20;
    static int discountPrice = 10;

    public static Ticket getTicket(int money, TicketType type) throws IOException {

        int price = type == TicketType.FULL ? fullPrice : discountPrice;
        if (money >= price) {

            Ticket ticket = new Ticket(ticketId++, price, type);
            Zoo.addSoldTicket(ticket);
            return ticket;

        } else {
            throw new IOException("Not enough money");
        }
    }
}





