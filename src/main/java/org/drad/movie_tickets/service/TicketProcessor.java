package org.drad.movie_tickets.service;

import org.drad.movie_tickets.model.ticket.Ticket;
import org.drad.movie_tickets.model.ticket.TicketType;

/**
 * Processor to process the individual ticket discounts.
 */
public abstract class TicketProcessor<T extends Ticket> {

    /**
     * Applies the discount specific to the fiven ticket type.
     *
     */
    protected abstract void applyDiscount(T ticket);

    /**
     * Returns the ticket type. Processors will update the ticket type for which they are responsible to process.
     *
     * @return Ticket type
     */
    public abstract TicketType getType();
}
