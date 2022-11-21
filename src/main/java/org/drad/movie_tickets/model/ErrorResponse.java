package org.drad.movie_tickets.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * Error
 */
@AllArgsConstructor
@NoArgsConstructor
@Builder
@EqualsAndHashCode
@Getter
@Setter
public class ErrorResponse {

  @JsonProperty("code")
  private Integer code;

  @JsonProperty("status")
  private Integer status;

  @JsonProperty("message")
  private String message;
}

