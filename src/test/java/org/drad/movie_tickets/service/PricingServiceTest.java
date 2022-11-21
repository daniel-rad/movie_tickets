package org.drad.movie_tickets.service;

import static org.drad.movie_tickets.fixture.TicketFixture.buildTicketEntityList;
import static org.drad.movie_tickets.fixture.TicketFixture.buildTicketList;
import static org.drad.movie_tickets.fixture.TicketFixture.buildTicketListOneAdultThreeChildren;
import static org.drad.movie_tickets.fixture.TicketFixture.buildTicketResponseList;
import static org.drad.movie_tickets.fixture.TicketFixture.getAdultTicket;
import static org.drad.movie_tickets.fixture.TicketFixture.getChildrenTicket;
import static org.drad.movie_tickets.fixture.TicketFixture.getSeniorTicket;
import static org.drad.movie_tickets.fixture.TicketFixture.getTeenTicket;
import static org.drad.movie_tickets.fixture.TransactionFixture.buildCustomerList;
import static org.drad.movie_tickets.fixture.TransactionFixture.buildGroupDiscountEntity;
import static org.drad.movie_tickets.fixture.TransactionFixture.buildTransactionRequest;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.containsInAnyOrder;
import static org.hamcrest.Matchers.hasSize;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.ArgumentMatchers.anyList;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.lenient;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.springframework.test.util.ReflectionTestUtils.setField;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.function.Function;
import java.util.stream.Collectors;
import org.drad.movie_tickets.dao.GroupDiscountRepository;
import org.drad.movie_tickets.dao.TicketRepository;
import org.drad.movie_tickets.domain.TicketEntity;
import org.drad.movie_tickets.model.TransactionResponse;
import org.drad.movie_tickets.model.ticket.AdultTicket;
import org.drad.movie_tickets.model.ticket.ChildrenTicket;
import org.drad.movie_tickets.model.ticket.SeniorTicket;
import org.drad.movie_tickets.model.ticket.TeenTicket;
import org.drad.movie_tickets.model.ticket.Ticket;
import org.drad.movie_tickets.model.ticket.TicketResponse;
import org.drad.movie_tickets.model.ticket.TicketType;
import org.drad.movie_tickets.service.processor.AdultTicketProcessor;
import org.drad.movie_tickets.service.processor.ChildrenTicketProcessor;
import org.drad.movie_tickets.service.processor.SeniorTicketProcessor;
import org.drad.movie_tickets.service.processor.TeenTicketProcessor;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.internal.matchers.apachecommons.ReflectionEquals;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
public class PricingServiceTest {

    @Mock
    private GroupDiscountRepository groupDiscountRepository;
    @Mock
    private TicketService ticketService;

    @InjectMocks
    private PricingServiceImpl pricingService;

    @BeforeEach
    public void setUp() {
        lenient().when(groupDiscountRepository.findByTicketId(eq(TicketType.ADULT.getTypeId()))).thenReturn(Optional.empty());
        lenient().when(groupDiscountRepository.findByTicketId(eq(TicketType.SENIOR.getTypeId()))).thenReturn(Optional.empty());
        lenient().when(groupDiscountRepository.findByTicketId(eq(TicketType.TEEN.getTypeId()))).thenReturn(Optional.empty());
        lenient().when(groupDiscountRepository.findByTicketId(eq(TicketType.CHILDREN.getTypeId()))).thenReturn(Optional.of(buildGroupDiscountEntity(TicketType.CHILDREN.getTypeId())));
    }

    /**
     * One ticket of each kind, no discounts.
     */
    @Test
    public void computeTransactionPriceWithNoDiscounts() {
        List<Ticket> tickets = buildTicketList();
        Map<String, List<TicketResponse>> expected = tickets.stream().map(Ticket::toTicketResponse)
              .collect(Collectors.groupingBy(TicketResponse::getTicketType));

        when(ticketService.getTicketsForCustomers(anyList())).thenReturn(tickets);

        TransactionResponse transactionResponse = pricingService.computeTransactionCost(buildTransactionRequest());

        assertEquals(transactionResponse.getTickets().size(), tickets.size());
        assertEquals("59.50", transactionResponse.getTotalCost());

        for (TicketResponse ticketResponse : transactionResponse.getTickets()) {
            assertEquals(expected.get(ticketResponse.getTicketType()).get(0), ticketResponse);
        }
    }

    @Test
    public void computeTransactionPriceWithDiscounts() {
        List<Ticket> tickets = buildTicketListOneAdultThreeChildren();

        when(ticketService.getTicketsForCustomers(anyList())).thenReturn(tickets);

        TransactionResponse transactionResponse = pricingService.computeTransactionCost(buildTransactionRequest());

        assertEquals(transactionResponse.getTickets().size(), 2);
        assertEquals("36.25", transactionResponse.getTotalCost());
    }
}
