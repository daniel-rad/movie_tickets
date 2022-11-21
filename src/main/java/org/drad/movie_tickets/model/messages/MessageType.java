package org.drad.movie_tickets.model.messages;

/**
 * The possible types of a {@link MessageDefinition}.
 *
 */
public enum MessageType {
    /**
     * Log messages.
     */
    LOG("log."),

    /**
     * Exception messages.
     */
    ERROR("error.");

    private final String propertyPrefix;

    MessageType(final String propertyPrefix) {
        this.propertyPrefix = propertyPrefix;
    }

    /**
     * Retrieves the prefix to be used when searching for a message in the message bundle.
     *
     * @return the property prefix
     */
    public String getPropertyPrefix() {
        return propertyPrefix;
    }
}
