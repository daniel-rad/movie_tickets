package org.drad.movie_tickets.service;

import java.math.BigDecimal;
import java.util.Comparator;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import org.drad.movie_tickets.dao.GroupDiscountRepository;
import org.drad.movie_tickets.model.ticket.Ticket;
import org.drad.movie_tickets.model.ticket.TicketResponse;
import org.drad.movie_tickets.model.ticket.TicketType;
import org.drad.movie_tickets.model.TransactionRequest;
import org.drad.movie_tickets.model.TransactionResponse;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class PricingServiceImpl implements PricingService {

    private final TicketService ticketService;
    private final GroupDiscountRepository groupDiscountRepository;

    public PricingServiceImpl(TicketService ticketService, GroupDiscountRepository groupDiscountRepository) {
        this.ticketService = ticketService;
        this.groupDiscountRepository = groupDiscountRepository;
    }

    @Override
    @Transactional(readOnly = true)
    public TransactionResponse computeTransactionCost(TransactionRequest request) {
        List<TicketResponse> tickets = ticketService.getTicketsForCustomers(request.getCustomers()).stream()
              .collect(
                    Collectors.groupingBy(Ticket::getId,
                          Collectors.mapping(Ticket::toTicketResponse, Collectors.reducing(TicketResponse::merge))))
              .values().stream()
              .filter(Optional::isPresent)
              .map(Optional::get)
              .sorted(Comparator.comparing(TicketResponse::getTicketType))
              .collect(Collectors.toList());

        // apply group discounts
        for (TicketResponse ticketResponse : tickets) {
            groupDiscountRepository.findByTicketId(TicketType.of(ticketResponse.getTicketType()).getTypeId())
                  .filter(discount -> ticketResponse.getQuantity() >= discount.getMinQuantity())
                  .ifPresent(discount -> ticketResponse.applyDiscount(discount.getDiscount()));
        }

        // return response object
        return TransactionResponse.builder()
              .transactionId(request.getTransactionId())
              .tickets(tickets)
              .totalCostAmount(tickets.stream().map(TicketResponse::getTotalCostAmount).reduce(BigDecimal.ZERO, BigDecimal::add, BigDecimal::add))
              .build();
    }
}
