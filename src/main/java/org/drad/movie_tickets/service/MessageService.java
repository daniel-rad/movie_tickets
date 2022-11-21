package org.drad.movie_tickets.service;

import java.text.MessageFormat;
import java.util.Locale;
import java.util.ResourceBundle;
import lombok.extern.log4j.Log4j2;
import org.apache.commons.lang3.ArrayUtils;
import org.drad.movie_tickets.model.messages.MessageDefinition;

/**
 * Utility class for transforming a {@link MessageDefinition} object into a {@link String}.
 *
 */
@Log4j2
public final class MessageService {

    private static final ResourceBundle MESSAGES = ResourceBundle.getBundle("messages", Locale.ENGLISH);
    private static final String DEFAULT_MESSAGE = "Unknown";

    private MessageService() {
    }

    /**
     * Retrieves the message associated with the given message definition and formats it using the received arguments.
     *
     * @param messageDefinition the message definition
     * @param arguments         the arguments to be used for formatting the message
     * @return the resolved message
     */
    public static String resolveMessage(final MessageDefinition messageDefinition, final Object... arguments) {
        String messageKey = messageDefinition.getMessageKey();
        if (!MESSAGES.containsKey(messageKey)) {
            log.warn("The resource bundle doesn't contain any message for '{}' key.", messageKey);
            return DEFAULT_MESSAGE;
        }
        String messagePattern = MESSAGES.getString(messageKey);
        if (ArrayUtils.isNotEmpty(arguments)) {
            return MessageFormat.format(messagePattern, arguments);
        } else {
            return messagePattern;
        }
    }
}
