package org.drad.movie_tickets.service.processor;

import org.drad.movie_tickets.model.ticket.TeenTicket;
import org.drad.movie_tickets.model.ticket.TicketType;
import org.drad.movie_tickets.service.TicketProcessor;
import org.springframework.stereotype.Component;

@Component
public class TeenTicketProcessor extends TicketProcessor<TeenTicket> {
    @Override
    public void applyDiscount(TeenTicket ticket) {
        // not implemented - do nothing
    }

    @Override
    public TicketType getType() {
        return TicketType.TEEN;
    }
}
