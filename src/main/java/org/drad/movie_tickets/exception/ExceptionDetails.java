package org.drad.movie_tickets.exception;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.ToString;

/**
 * Simple DTO representing the response body to be returned when an exception occurs.
 *
 */
@Builder
@Getter
@ToString
@AllArgsConstructor
public class ExceptionDetails {

    private final int errorCode;
    private final int statusCode;
    private final String message;
}
