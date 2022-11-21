package org.drad.movie_tickets.model.ticket;

import java.text.MessageFormat;
import java.util.Arrays;
import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public enum TicketType {
    ADULT(1,"Adult"),
    SENIOR(2,"Senior"),
    TEEN(3,"Teen"),
    CHILDREN(4,"Children");

    private Integer typeId;
    private String typeName;

    public static TicketType of(Integer id) {
        return Arrays.stream(values())
              .filter(s -> s.getTypeId().equals(id))
              .findFirst()
              .orElseThrow(() -> new RuntimeException(MessageFormat.format("Invalid ticket type id {0}", id)));
    }

    public static TicketType of(String name) {
        return Arrays.stream(values())
              .filter(s -> s.getTypeName().equals(name))
              .findFirst()
              .orElseThrow(() -> new RuntimeException(MessageFormat.format("Invalid ticket type name {0}", name)));
    }
}
