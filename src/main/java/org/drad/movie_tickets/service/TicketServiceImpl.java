package org.drad.movie_tickets.service;

import static java.util.function.Function.identity;
import static java.util.stream.Collectors.toMap;
import static org.drad.movie_tickets.domain.mapper.Mappers.TICKET_MAPPER;

import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Optional;
import java.util.stream.Collectors;
import lombok.extern.log4j.Log4j2;
import org.drad.movie_tickets.dao.TicketRepository;
import org.drad.movie_tickets.domain.TicketEntity;
import org.drad.movie_tickets.exception.ExceptionDefinitions;
import org.drad.movie_tickets.exception.MovieTicketsException;
import org.drad.movie_tickets.model.Customer;
import org.drad.movie_tickets.model.ticket.Ticket;
import org.drad.movie_tickets.model.ticket.TicketType;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Log4j2
public class TicketServiceImpl implements TicketService {

    private final Map<TicketType, TicketProcessor<? extends Ticket>> ticketProcessors;

    private final TicketRepository ticketRepository;

    public TicketServiceImpl(List<TicketProcessor<? extends Ticket>> ticketProcessors, TicketRepository ticketRepository) {
        this.ticketProcessors = Optional
              .ofNullable(ticketProcessors)
              .map(Collection::stream)
              .map(strategyStep -> strategyStep.collect(toMap(TicketProcessor::getType, identity())))
              .orElseGet(Collections::emptyMap);
        this.ticketRepository = ticketRepository;
    }

    @Override
    @Transactional(readOnly = true)
    public List<Ticket> getTicketsForCustomers(List<Customer> customers) {
        List<Ticket> tickets = customers.stream()
              .map(Customer::getAge)
              .map(this::findTicketByCustomerAge)
              .filter(Objects::nonNull)
              .collect(Collectors.toList());

        // apply individual discounts
        for (Ticket ticket : tickets) {
            TicketProcessor<Ticket> processor = (TicketProcessor<Ticket>) ticketProcessors.get(
                  TicketType.of(ticket.getId()));
            processor.applyDiscount(ticket);
        }

        return tickets;
    }

    public Ticket findTicketByCustomerAge(Integer customerAge) {
        return ticketRepository.findAll().stream()
              .filter(ticket -> ticket.getMinAge() <= customerAge && ticket.getMaxAge() > customerAge).findAny()
              .map(this::buildTicket).orElse(null);
    }

    private Ticket buildTicket(TicketEntity ticketEntity) {
        if (ticketEntity != null) {
            switch (TicketType.of(ticketEntity.getId())) {
                case ADULT:
                    return TICKET_MAPPER.toAdultTicketDTO(ticketEntity);
                case SENIOR:
                    return TICKET_MAPPER.toSeniorTicketDTO(ticketEntity);
                case TEEN:
                    return TICKET_MAPPER.toTeenTicketDTO(ticketEntity);
                case CHILDREN:
                    return TICKET_MAPPER.toChildrenTicketDTO(ticketEntity);
                default:
                    throw new MovieTicketsException(ExceptionDefinitions.UNSUPPORTED_TICKET_TYPE, ticketEntity.getId());
            }
        }
        return null;
    }
}
