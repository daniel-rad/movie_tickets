package org.drad.movie_tickets.model.ticket;

import java.math.BigDecimal;

public class AdultTicket extends Ticket {

    @Override
    public void applyDiscount(BigDecimal discount) {
        // do nothing
    }
}
