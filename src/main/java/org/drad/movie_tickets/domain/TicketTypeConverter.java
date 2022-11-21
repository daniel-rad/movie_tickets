package org.drad.movie_tickets.domain;

import java.text.MessageFormat;
import java.util.Arrays;
import java.util.Optional;
import javax.persistence.AttributeConverter;
import javax.persistence.Converter;
import org.drad.movie_tickets.model.ticket.TicketType;

@Converter
public class TicketTypeConverter implements AttributeConverter<TicketType, Integer> {

    @Override
    public Integer convertToDatabaseColumn(TicketType attribute) {
        return  Optional.ofNullable(attribute).map(TicketType::getTypeId).orElse(null);
    }

    @Override
    public TicketType convertToEntityAttribute(Integer dbData) {
        if (dbData == null) {
            return null;
        }

        return Arrays.stream(TicketType.values())
              .filter(e -> e.getTypeId().equals(dbData))
              .findFirst()
              .orElseThrow(() -> new IllegalArgumentException(
                    MessageFormat.format("Invalid job type id {0} from database", dbData)));
    }
}