package org.drad.movie_tickets.model.ticket;

import java.math.BigDecimal;

public class AdultTicket extends Ticket {

    @Override
    public void applyDiscount(BigDecimal discount) {
        // do nothing
    }

    public static AdultTicketBuilder builder() {
        return new AdultTicketBuilder();
    }

    public static final class AdultTicketBuilder {

        private Integer id;
        private String type;
        private BigDecimal price;

        private AdultTicketBuilder() {
        }

        public static AdultTicketBuilder anAdultTicket() {
            return new AdultTicketBuilder();
        }

        public AdultTicketBuilder id(Integer id) {
            this.id = id;
            return this;
        }

        public AdultTicketBuilder type(String type) {
            this.type = type;
            return this;
        }

        public AdultTicketBuilder price(BigDecimal price) {
            this.price = price;
            return this;
        }

        public AdultTicket build() {
            AdultTicket adultTicket = new AdultTicket();
            adultTicket.setId(id);
            adultTicket.setType(type);
            adultTicket.setPrice(price);
            return adultTicket;
        }
    }
}
