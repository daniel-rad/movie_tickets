package org.drad.movie_tickets.service;

import java.util.List;
import org.drad.movie_tickets.model.Customer;
import org.drad.movie_tickets.model.ticket.Ticket;

public interface TicketService {

    /**
     * Takes in a list of {@link Customer} objects and based on the age of the customer, it will return a list of
     * {@link Ticket} objects with individual discounts already applied.
     */
    List<Ticket> getTicketsForCustomers(List<Customer> customers);
}
