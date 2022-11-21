package org.drad.movie_tickets.model.ticket;

import static org.drad.movie_tickets.util.PriceUtils.getDiscountedPrice;

import java.math.BigDecimal;

public class SeniorTicket extends Ticket {

    @Override
    public void applyDiscount(BigDecimal discount) {
        setPrice(getDiscountedPrice(getPrice(), discount));
    }
}
