package org.drad.movie_tickets.domain.mapper;

import static org.mapstruct.factory.Mappers.getMapper;

public class Mappers {

    public static final TicketMapper TICKET_MAPPER = getMapper(TicketMapper.class);

    private Mappers() {
    }
}