package org.drad.movie_tickets.model.ticket;

import java.math.BigDecimal;

public class ChildrenTicket extends Ticket {

    @Override
    public void applyDiscount(BigDecimal discount) {
        // do nothing
    }
}
