package org.drad.movie_tickets.exception;

import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;

/**
 * All the possible exception definitions of Movie Tickets application.
 *
 */
@AllArgsConstructor
public enum ExceptionDefinitions implements ExceptionDefinition {

    /**
     * Exception thrown when unsupported step encountered.
     */
    UNSUPPORTED_TICKET_TYPE(0001, HttpStatus.NOT_ACCEPTABLE);

    private final int code;
    private final HttpStatus statusCode;

    @Override
    public int getCode() {
        return code;
    }

    @Override
    public HttpStatus getStatus() {
        return statusCode;
    }
}
