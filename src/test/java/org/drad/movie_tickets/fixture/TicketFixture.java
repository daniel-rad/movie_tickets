package org.drad.movie_tickets.fixture;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import org.drad.movie_tickets.domain.PriceEntity;
import org.drad.movie_tickets.domain.TicketEntity;
import org.drad.movie_tickets.model.ticket.AdultTicket;
import org.drad.movie_tickets.model.ticket.ChildrenTicket;
import org.drad.movie_tickets.model.ticket.SeniorTicket;
import org.drad.movie_tickets.model.ticket.TeenTicket;
import org.drad.movie_tickets.model.ticket.Ticket;
import org.drad.movie_tickets.model.ticket.TicketResponse;

public class TicketFixture {

    public static List<TicketResponse> buildTicketResponseList() {
        List<TicketResponse> tickets = new ArrayList<>();

        tickets.add(TicketResponse.builder().ticketType("Adult").quantity(1).totalCostAmount(new BigDecimal("25.00")).build());
        tickets.add(TicketResponse.builder().ticketType("Children").quantity(1).totalCostAmount(new BigDecimal("5.00")).build());
        tickets.add(TicketResponse.builder().ticketType("Senior").quantity(1).totalCostAmount(new BigDecimal("17.50")).build());
        tickets.add(TicketResponse.builder().ticketType("Teen").quantity(1).totalCostAmount(new BigDecimal("12.00")).build());
        return tickets;
    }

    public static List<Ticket> buildTicketList() {
        List<Ticket> tickets = new ArrayList<>();

        tickets.add(AdultTicket.builder().type("Adult").id(1).price(new BigDecimal("25.00")).build());
        tickets.add(ChildrenTicket.builder().type("Children").id(4).price(new BigDecimal("5.00")).build());
        tickets.add(SeniorTicket.builder().type("Senior").id(2).price(new BigDecimal("17.50")).build());
        tickets.add(TeenTicket.builder().type("Teen").id(3).price(new BigDecimal("12.00")).build());
        return tickets;
    }

    public static List<Ticket> buildTicketListOneAdultThreeChildren() {
        List<Ticket> tickets = new ArrayList<>();

        tickets.add(AdultTicket.builder().type("Adult").id(1).price(new BigDecimal("25.00")).build());
        tickets.add(ChildrenTicket.builder().type("Children").id(4).price(new BigDecimal("5.00")).build());
        tickets.add(ChildrenTicket.builder().type("Children").id(4).price(new BigDecimal("5.00")).build());
        tickets.add(ChildrenTicket.builder().type("Children").id(4).price(new BigDecimal("5.00")).build());
        return tickets;
    }

    public static List<TicketEntity> buildTicketEntityList() {
        List<TicketEntity> tickets = new ArrayList<>();

        tickets.add(getAdultTicket());
        tickets.add(getSeniorTicket());
        tickets.add(getTeenTicket());
        tickets.add(getChildrenTicket());
        return tickets;
    }

    public static TicketEntity getAdultTicket() {
        return TicketEntity.builder().id(1).type("Adult").minAge(18).maxAge(65).price(buildPriceEntity("25.00")).build();
    }

    public static TicketEntity getSeniorTicket() {
        return TicketEntity.builder().id(2).type("Senior").minAge(65).maxAge(120).price(buildPriceEntity("25.00")).build();
    }

    public static TicketEntity getTeenTicket() {
        return TicketEntity.builder().id(3).type("Teen").minAge(11).maxAge(18).price(buildPriceEntity("12.00")).build();
    }

    public static TicketEntity getChildrenTicket() {
        return TicketEntity.builder().id(4).type("Children").minAge(0).maxAge(11).price(buildPriceEntity("5.00")).build();
    }

    public static PriceEntity buildPriceEntity(String price) {
        return PriceEntity.builder().amount(new BigDecimal(price)).build();
    }
}
