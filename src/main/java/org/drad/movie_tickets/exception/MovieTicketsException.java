package org.drad.movie_tickets.exception;

import org.drad.movie_tickets.model.messages.MessageDefinition;
import org.drad.movie_tickets.service.MessageService;

/**
 * The base exception thrown when any problem occurs in Movie Tickets application.
 */
public class MovieTicketsException extends RuntimeException {

    private final ExceptionDetails exceptionDetails;

    private MovieTicketsException(final ExceptionDetails exceptionDetails, final Throwable cause) {
        super(exceptionDetails.getMessage(), cause);
        this.exceptionDetails = exceptionDetails;
    }

    /**
     * Constructs a new movie tickets exception based on the received exception definition.
     *
     * @param exceptionDefinition the definition of the exception based on which the message, code, status and
     *                            tracking info will be set.
     * @param messageArgs         the arguments to use when resolving the message of the exception. See
     *                            {@link MessageService#resolveMessage(MessageDefinition, Object...)} for more
     *                            details on how the message is resolved
     */
    public MovieTicketsException(final ExceptionDefinitions exceptionDefinition, final Object... messageArgs) {
        this(ExceptionDetails.builder()
                    .message(MessageService.resolveMessage(exceptionDefinition, messageArgs))
                    .errorCode(exceptionDefinition.getCode())
                    .statusCode(exceptionDefinition.getStatus().value())
                    .build(),
              null);
    }

    /**
     * Retrieves the exception details. This object can be serialized as a response body.
     *
     * @return the exception details
     */
    public ExceptionDetails getExceptionDetails() {
        return exceptionDetails;
    }
}
