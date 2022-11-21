package org.drad.movie_tickets.domain.mapper;


import org.drad.movie_tickets.domain.TicketEntity;
import org.drad.movie_tickets.model.ticket.AdultTicket;
import org.drad.movie_tickets.model.ticket.ChildrenTicket;
import org.drad.movie_tickets.model.ticket.SeniorTicket;
import org.drad.movie_tickets.model.ticket.TeenTicket;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.NullValueCheckStrategy;
import org.mapstruct.NullValuePropertyMappingStrategy;

@Mapper(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE, nullValueCheckStrategy =
      NullValueCheckStrategy.ALWAYS)
public interface TicketMapper {

    @Mapping(target = "price", expression = "java(entity.getPrice().getAmount().setScale(2))")
    AdultTicket toAdultTicketDTO(TicketEntity entity);

    @Mapping(target = "price", expression = "java(entity.getPrice().getAmount().setScale(2))")
    SeniorTicket toSeniorTicketDTO(TicketEntity entity);

    @Mapping(target = "price", expression = "java(entity.getPrice().getAmount().setScale(2))")
    TeenTicket toTeenTicketDTO(TicketEntity entity);

    @Mapping(target = "price", expression = "java(entity.getPrice().getAmount().setScale(2))")
    ChildrenTicket toChildrenTicketDTO(TicketEntity entity);

}
