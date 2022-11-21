package org.drad.movie_tickets.model.ticket;

import java.math.BigDecimal;
import org.drad.movie_tickets.model.ticket.AdultTicket.AdultTicketBuilder;

public class ChildrenTicket extends Ticket {

    @Override
    public void applyDiscount(BigDecimal discount) {
        // do nothing
    }

    public static ChildrenTicketBuilder builder() {
        return new ChildrenTicketBuilder();
    }

    public static final class ChildrenTicketBuilder {

        private Integer id;
        private String type;
        private BigDecimal price;

        private ChildrenTicketBuilder() {
        }

        public static ChildrenTicketBuilder aChildrenTicket() {
            return new ChildrenTicketBuilder();
        }

        public ChildrenTicketBuilder id(Integer id) {
            this.id = id;
            return this;
        }

        public ChildrenTicketBuilder type(String type) {
            this.type = type;
            return this;
        }

        public ChildrenTicketBuilder price(BigDecimal price) {
            this.price = price;
            return this;
        }

        public ChildrenTicket build() {
            ChildrenTicket childrenTicket = new ChildrenTicket();
            childrenTicket.setId(id);
            childrenTicket.setType(type);
            childrenTicket.setPrice(price);
            return childrenTicket;
        }
    }
}
