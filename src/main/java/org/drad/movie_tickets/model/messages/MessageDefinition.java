package org.drad.movie_tickets.model.messages;

import org.drad.movie_tickets.service.MessageService;

/**
 * Defines the code and the type of the message that must be resolved by the {@link MessageService}.
 *
 */
public interface MessageDefinition {

    /**
     * Retrieves the code used to reference the message internally.
     *
     * @return the message code
     */
    int getCode();

    /**
     * Retrieves the type of the message.
     *
     * @return the message type
     */
    MessageType getType();

    /**
     * Composes and returns the key to be used when searching for a message in the messages bundle.
     *
     * @return the key to be used for searching the message
     */
    default String getMessageKey() {
        return getType().getPropertyPrefix() + getCode();
    }
}
