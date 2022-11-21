package org.drad.movie_tickets.model.ticket;

import static org.drad.movie_tickets.util.PriceUtils.getDiscountedPrice;

import java.math.BigDecimal;
import org.drad.movie_tickets.model.ticket.ChildrenTicket.ChildrenTicketBuilder;

public class SeniorTicket extends Ticket {

    @Override
    public void applyDiscount(BigDecimal discount) {
        setPrice(getDiscountedPrice(getPrice(), discount));
    }

    public static SeniorTicketBuilder builder() {
        return new SeniorTicketBuilder();
    }

    public static final class SeniorTicketBuilder {

        private Integer id;
        private String type;
        private BigDecimal price;

        private SeniorTicketBuilder() {
        }

        public static SeniorTicketBuilder aSeniorTicket() {
            return new SeniorTicketBuilder();
        }

        public SeniorTicketBuilder id(Integer id) {
            this.id = id;
            return this;
        }

        public SeniorTicketBuilder type(String type) {
            this.type = type;
            return this;
        }

        public SeniorTicketBuilder price(BigDecimal price) {
            this.price = price;
            return this;
        }

        public SeniorTicket build() {
            SeniorTicket seniorTicket = new SeniorTicket();
            seniorTicket.setId(id);
            seniorTicket.setType(type);
            seniorTicket.setPrice(price);
            return seniorTicket;
        }
    }
}
