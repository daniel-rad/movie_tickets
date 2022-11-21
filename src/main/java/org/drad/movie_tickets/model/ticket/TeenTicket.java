package org.drad.movie_tickets.model.ticket;

import java.math.BigDecimal;
import org.drad.movie_tickets.model.ticket.SeniorTicket.SeniorTicketBuilder;

public class TeenTicket extends Ticket {

    @Override
    public void applyDiscount(BigDecimal discount) {
        //do nothing
    }

    public static TeenTicketBuilder builder() {
        return new TeenTicketBuilder();
    }

    public static final class TeenTicketBuilder {

        private Integer id;
        private String type;
        private BigDecimal price;

        private TeenTicketBuilder() {
        }

        public static TeenTicketBuilder aTeenTicket() {
            return new TeenTicketBuilder();
        }

        public TeenTicketBuilder id(Integer id) {
            this.id = id;
            return this;
        }

        public TeenTicketBuilder type(String type) {
            this.type = type;
            return this;
        }

        public TeenTicketBuilder price(BigDecimal price) {
            this.price = price;
            return this;
        }

        public TeenTicket build() {
            TeenTicket teenTicket = new TeenTicket();
            teenTicket.setId(id);
            teenTicket.setType(type);
            teenTicket.setPrice(price);
            return teenTicket;
        }
    }
}
