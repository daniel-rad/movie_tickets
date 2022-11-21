package org.drad.movie_tickets.service.processor;

import org.drad.movie_tickets.model.ticket.ChildrenTicket;
import org.drad.movie_tickets.model.ticket.TicketType;
import org.drad.movie_tickets.service.TicketProcessor;
import org.springframework.stereotype.Component;

@Component
public class ChildrenTicketProcessor extends TicketProcessor<ChildrenTicket> {
    @Override
    protected void applyDiscount(ChildrenTicket ticket) {
        // not implemented - do nothing
    }

    @Override
    public TicketType getType() {
        return TicketType.CHILDREN;
    }
}
