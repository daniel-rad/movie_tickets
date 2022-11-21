package org.drad.movie_tickets.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.ArrayList;
import java.util.List;
import javax.validation.Valid;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.drad.movie_tickets.model.ticket.TicketResponse;

/**
 * TransactionResponse
 */
@AllArgsConstructor
@NoArgsConstructor
@Builder
@EqualsAndHashCode
@Getter
@Setter
public class TransactionResponse {

    @JsonProperty("transactionId")
    private Integer transactionId;

    @JsonProperty("tickets")
    @Valid
    private List<TicketResponse> tickets = new ArrayList<>();

    @JsonIgnore
    private BigDecimal totalCostAmount;

    @JsonProperty("totalCost")
    public String getTotalCost() {
        if (totalCostAmount != null) {
            return totalCostAmount.setScale(2, RoundingMode.CEILING).toString();
        }
        return null;
    }
}

