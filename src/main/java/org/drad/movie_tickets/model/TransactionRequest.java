package org.drad.movie_tickets.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import java.util.ArrayList;
import java.util.List;
import javax.validation.Valid;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * TransactionRequest
 */
@AllArgsConstructor
@NoArgsConstructor
@Builder
@EqualsAndHashCode
@Getter
@Setter
public class TransactionRequest {

  @JsonProperty("transactionId")
  private Integer transactionId;

  @JsonProperty("customers")
  @Valid
  private List<Customer> customers = new ArrayList<>();

}

