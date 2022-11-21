package org.drad.movie_tickets.service.processor;

import org.drad.movie_tickets.model.ticket.AdultTicket;
import org.drad.movie_tickets.model.ticket.TicketType;
import org.drad.movie_tickets.service.TicketProcessor;
import org.springframework.stereotype.Component;

@Component
public class AdultTicketProcessor extends TicketProcessor<AdultTicket> {
    @Override
    public void applyDiscount(AdultTicket ticket) {
        // not implemented - do nothing
    }

    @Override
    public TicketType getType() {
        return TicketType.ADULT;
    }
}
