package org.drad.movie_tickets.controller;

import lombok.extern.log4j.Log4j2;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.exception.ExceptionUtils;
import org.drad.movie_tickets.exception.ExceptionDetails;
import org.drad.movie_tickets.exception.MovieTicketsException;
import org.drad.movie_tickets.model.ErrorResponse;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@Log4j2
@RestControllerAdvice
public class ExceptionHandlingController {

    @ExceptionHandler(MovieTicketsException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public final ErrorResponse handleMovieTicketsExceptions(Exception exception) {
        ExceptionDetails exceptionDetails = ((MovieTicketsException) exception).getExceptionDetails();
        log.error(exception.getMessage(), exception);
        return ErrorResponse.builder()
              .code(exceptionDetails.getErrorCode())
              .status(HttpStatus.BAD_REQUEST.value())
              .message(StringUtils.isNotBlank(exceptionDetails.getMessage()) ? exceptionDetails.getMessage()
                    : ExceptionUtils.getRootCause(exception).getMessage())
              .build();
    }

    // generic exception handler
    @ExceptionHandler(RuntimeException.class)
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    public final ErrorResponse handleAllExceptions(RuntimeException exception) {
        log.error(exception.getMessage(), exception);
        return ErrorResponse.builder()
              .code(HttpStatus.INTERNAL_SERVER_ERROR.value())
              .status(HttpStatus.INTERNAL_SERVER_ERROR.value())
              .message(ExceptionUtils.getRootCause(exception).getMessage())
              .build();
    }
}
