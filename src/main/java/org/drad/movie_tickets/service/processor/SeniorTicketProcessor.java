package org.drad.movie_tickets.service.processor;

import java.math.BigDecimal;
import java.util.Objects;
import org.drad.movie_tickets.model.ticket.SeniorTicket;
import org.drad.movie_tickets.model.ticket.TicketType;
import org.drad.movie_tickets.service.TicketProcessor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class SeniorTicketProcessor extends TicketProcessor<SeniorTicket> {

    @Value("${discount.senior:0.0}")
    private BigDecimal discount;

    @Override
    public void applyDiscount(SeniorTicket ticket) {
        if (Objects.equals(discount, BigDecimal.ZERO)) {
            return;
        }
        ticket.applyDiscount(discount);
    }

    @Override
    public TicketType getType() {
        return TicketType.SENIOR;
    }
}
