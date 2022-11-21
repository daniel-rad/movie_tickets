package org.drad.movie_tickets.service;

import static org.drad.movie_tickets.fixture.TicketFixture.buildTicketEntityList;
import static org.drad.movie_tickets.fixture.TicketFixture.getAdultTicket;
import static org.drad.movie_tickets.fixture.TicketFixture.getChildrenTicket;
import static org.drad.movie_tickets.fixture.TicketFixture.getSeniorTicket;
import static org.drad.movie_tickets.fixture.TicketFixture.getTeenTicket;
import static org.drad.movie_tickets.fixture.TransactionFixture.buildCustomerList;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.hasSize;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.doNothing;
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
import org.drad.movie_tickets.dao.TicketRepository;
import org.drad.movie_tickets.domain.TicketEntity;
import org.drad.movie_tickets.model.ticket.AdultTicket;
import org.drad.movie_tickets.model.ticket.ChildrenTicket;
import org.drad.movie_tickets.model.ticket.SeniorTicket;
import org.drad.movie_tickets.model.ticket.TeenTicket;
import org.drad.movie_tickets.model.ticket.Ticket;
import org.drad.movie_tickets.model.ticket.TicketType;
import org.drad.movie_tickets.service.processor.AdultTicketProcessor;
import org.drad.movie_tickets.service.processor.ChildrenTicketProcessor;
import org.drad.movie_tickets.service.processor.SeniorTicketProcessor;
import org.drad.movie_tickets.service.processor.TeenTicketProcessor;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;
import org.mockito.CheckReturnValue;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
public class TicketServiceTest {

    @Mock
    private TicketRepository ticketRepository;

    @InjectMocks
    private TicketServiceImpl ticketService;

    /**
     * Invalid values (-3, Integer.MAX_VALUE) should be filtered out
     * @param age
     */
    @ParameterizedTest
    @ValueSource(ints = {1, 3, 5, -3, 15, 19, 36, 65, 80, Integer.MAX_VALUE})
    public void fetchTicketBasedOnCustomerAgeTest(int age) {
        when(ticketRepository.findAll()).thenReturn(buildTicketEntityList());

        Ticket response = ticketService.findTicketByCustomerAge(age);

        if (0 <= age && age < 11) {
            assertEquals("Children", response.getType());
            assertEquals(4, response.getId());
            assertEquals(new BigDecimal("5.00"), response.getPrice());
            assertTrue(response instanceof ChildrenTicket);
        }

        if (11 <= age && age < 18) {
            assertEquals("Teen", response.getType());
            assertEquals(3, response.getId());
            assertEquals(new BigDecimal("12.00"), response.getPrice());
            assertTrue(response instanceof TeenTicket);
        }

        if (18 <= age && age < 65) {
            assertEquals("Adult", response.getType());
            assertEquals(1, response.getId());
            assertEquals(new BigDecimal("25.00"), response.getPrice());
            assertTrue(response instanceof AdultTicket);
        }

        if (65 <= age && age < 120) {
            assertEquals("Senior", response.getType());
            assertEquals(2, response.getId());
            assertEquals(new BigDecimal("25.00"), response.getPrice());
            assertTrue(response instanceof SeniorTicket);
        }
    }

    @Test
    public void whenGetTicketsForCustomers_returnTickets() {

        AdultTicketProcessor adultTicketProcessorMock = mock(AdultTicketProcessor.class);
        ChildrenTicketProcessor childrenTicketProcessorMock = mock(ChildrenTicketProcessor.class);
        TeenTicketProcessor teenTicketProcessorMock = mock(TeenTicketProcessor.class);
        SeniorTicketProcessor seniorTicketProcessorMock = mock(SeniorTicketProcessor.class);

        Map<TicketType, TicketProcessor> processors = new HashMap<>();
        processors.put(TicketType.ADULT, adultTicketProcessorMock);
        processors.put(TicketType.CHILDREN, childrenTicketProcessorMock);
        processors.put(TicketType.TEEN, teenTicketProcessorMock);
        processors.put(TicketType.SENIOR, seniorTicketProcessorMock);

        setField(ticketService, "ticketProcessors", processors);
        when(ticketRepository.findAll()).thenReturn(buildTicketEntityList());

        doNothing().when(adultTicketProcessorMock).applyDiscount(any(AdultTicket.class));
        doNothing().when(seniorTicketProcessorMock).applyDiscount(any(SeniorTicket.class));
        doNothing().when(teenTicketProcessorMock).applyDiscount(any(TeenTicket.class));
        doNothing().when(childrenTicketProcessorMock).applyDiscount(any(ChildrenTicket.class));

        List<Ticket> ticketsForCustomers = ticketService.getTicketsForCustomers(buildCustomerList());

        verify(adultTicketProcessorMock, times(1)).applyDiscount(any(AdultTicket.class));
        verify(seniorTicketProcessorMock, times(1)).applyDiscount(any(SeniorTicket.class));
        verify(teenTicketProcessorMock, times(1)).applyDiscount(any(TeenTicket.class));
        verify(childrenTicketProcessorMock, times(1)).applyDiscount(any(ChildrenTicket.class));

        assertThat(ticketsForCustomers, hasSize(4));

        Optional<Ticket> adultTicketOptional = ticketsForCustomers.stream()
              .filter(ticket -> ticket.getId().equals(TicketType.ADULT.getTypeId())).findAny();
        assertTrue(adultTicketOptional.isPresent());
        assertTrue(testEqual(adultTicketOptional.get(), getAdultTicket()));

        Optional<Ticket> seniorTicketOptional = ticketsForCustomers.stream()
              .filter(ticket -> ticket.getId().equals(TicketType.SENIOR.getTypeId())).findAny();
        assertTrue(seniorTicketOptional.isPresent());
        assertTrue(testEqual(seniorTicketOptional.get(), getSeniorTicket()));

        Optional<Ticket> teenTicketOptional = ticketsForCustomers.stream()
              .filter(ticket -> ticket.getId().equals(TicketType.TEEN.getTypeId())).findAny();
        assertTrue(teenTicketOptional.isPresent());
        assertTrue(testEqual(teenTicketOptional.get(), getTeenTicket()));

        Optional<Ticket> childrenTicketOptional = ticketsForCustomers.stream()
              .filter(ticket -> ticket.getId().equals(TicketType.CHILDREN.getTypeId())).findAny();
        assertTrue(childrenTicketOptional.isPresent());
        assertTrue(testEqual(childrenTicketOptional.get(), getChildrenTicket()));

    }

    private boolean testEqual(Ticket ticket, TicketEntity ticketEntity) {
        return ticket.getId().equals(ticketEntity.getId()) && ticket.getType().equals(ticketEntity.getType());
    }
}
