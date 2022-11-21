package org.drad.movie_tickets.exception;

import org.drad.movie_tickets.model.messages.MessageDefinition;
import org.drad.movie_tickets.model.messages.MessageType;
import org.springframework.http.HttpStatus;

/**
 * Defines the message and the fields each exception should contain.
 */
public interface ExceptionDefinition extends MessageDefinition {

    /**
     * The http status code that should be used when the exception is returned as a http response.
     *
     * @return the status code to be used in the exception.
     */
    HttpStatus getStatus();

    @Override
    default MessageType getType() {
        return MessageType.ERROR;
    }
}
