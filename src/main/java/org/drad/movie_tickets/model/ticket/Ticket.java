package org.drad.movie_tickets.model.ticket;

import java.math.BigDecimal;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode
@Getter
@Setter
public abstract class Ticket {

    protected Integer id;
    protected String type;
    protected BigDecimal price;

    public abstract void applyDiscount(BigDecimal discount);

    public TicketResponse toTicketResponse() {
        return TicketResponse.builder().ticketType(TicketType.of(id).getTypeName()).quantity(1).totalCostAmount(price)
              .build();
    }
}
