package org.drad.movie_tickets.model.ticket;

import static org.drad.movie_tickets.util.PriceUtils.getDiscountedPrice;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.Optional;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * Ticket
 */
@AllArgsConstructor
@NoArgsConstructor
@Builder
@EqualsAndHashCode
@Getter
@Setter
public class TicketResponse {

    @JsonProperty("ticketType")
    private String ticketType;

    @JsonProperty("quantity")
    private Integer quantity;

    @JsonIgnore
    private BigDecimal totalCostAmount;

    @JsonProperty("totalCost")
    public String getTotalCost() {
        if (totalCostAmount != null) {
            return totalCostAmount.setScale(2, RoundingMode.CEILING).toString();
        }
        return null;
    }

    public static TicketResponse merge(TicketResponse ticketResponse1, TicketResponse ticketResponse2) {
        if (ticketResponse1 != null) {
            if (ticketResponse2 != null) {
                return TicketResponse.builder()
                      .ticketType(ticketResponse1.getTicketType())
                      .quantity(Optional.ofNullable(ticketResponse1.getQuantity()).orElse(0) + Optional.ofNullable(ticketResponse2.getQuantity()).orElse(0))
                      .totalCostAmount(Optional.ofNullable(ticketResponse1.getTotalCostAmount()).orElse(BigDecimal.ZERO).add(Optional.ofNullable(ticketResponse2.getTotalCostAmount()).orElse(BigDecimal.ZERO)))
                      .build();
            } else {
                return ticketResponse1;
            }
        } else {
            return ticketResponse2;
        }
    }

    public void applyDiscount(BigDecimal discount) {
        setTotalCost(getDiscountedPrice(getTotalCostAmount(), discount));
    }

    public void setTotalCost(BigDecimal totalCost) {
        if (totalCost != null) {
            totalCost = totalCost.setScale(2, RoundingMode.CEILING);
        }
        this.totalCostAmount = totalCost;
    }
}

