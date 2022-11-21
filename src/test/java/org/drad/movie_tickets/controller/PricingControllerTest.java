package org.drad.movie_tickets.controller;

import static org.drad.movie_tickets.exception.ExceptionDefinitions.UNSUPPORTED_TICKET_TYPE;
import static org.drad.movie_tickets.fixture.TransactionFixture.TRANSACTION_REQUEST_JSON;
import static org.mockito.ArgumentMatchers.any;
import static org.springframework.test.web.servlet.ResultMatcher.matchAll;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.Collections;
import java.util.List;
import org.drad.movie_tickets.exception.MovieTicketsException;
import org.drad.movie_tickets.fixture.TransactionFixture;
import org.drad.movie_tickets.model.TransactionRequest;
import org.drad.movie_tickets.service.PricingService;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.ResultActions;

public class PricingControllerTest extends AbstractControllerTest {

    @Mock
    private PricingService pricingService;
    @InjectMocks
    private PricingController objectUnderTest;

    @Test
    public void whenComputeTransactionCost_thenCorrectResponse() throws Exception {

        //given
        Mockito.when(pricingService.computeTransactionCost(any(TransactionRequest.class)))
              .thenReturn(TransactionFixture.buildTransactionResponse());
        //when
        ResultActions result = computeTransactionValue(TRANSACTION_REQUEST_JSON);

        // then
        Mockito.verify(pricingService, Mockito.times(1)).computeTransactionCost(any(TransactionRequest.class));
        result.andDo(print())
              .andExpect(status().isOk())
              .andExpect(content().contentType(MediaType.APPLICATION_JSON))
              .andExpect(matchAll(
                    jsonPath("$.transactionId").value("3"),
                    jsonPath("$.totalCost").value("59.50"),
                    jsonPath("$.tickets").isArray(),
                    jsonPath("$.tickets.length()").value(4),
                    jsonPath("$.tickets[0].ticketType").value("Adult"),
                    jsonPath("$.tickets[0].quantity").value("1"),
                    jsonPath("$.tickets[0].totalCost").value("25.00"),
                    jsonPath("$.tickets[1].ticketType").value("Children"),
                    jsonPath("$.tickets[1].quantity").value("1"),
                    jsonPath("$.tickets[1].totalCost").value("5.00"),
                    jsonPath("$.tickets[2].ticketType").value("Senior"),
                    jsonPath("$.tickets[2].quantity").value("1"),
                    jsonPath("$.tickets[2].totalCost").value("17.50"),
                    jsonPath("$.tickets[3].ticketType").value("Teen"),
                    jsonPath("$.tickets[3].quantity").value("1"),
                    jsonPath("$.tickets[3].totalCost").value("12.00")
              ));
    }

    @Test
    public void whenComputeTransactionCostInternalError_thenErrorResponseInternalError() throws Exception {

        //given
        Mockito.when(pricingService.computeTransactionCost(any(TransactionRequest.class)))
              .thenThrow(new RuntimeException("Something happened"));
        //when
        ResultActions result = computeTransactionValue(TRANSACTION_REQUEST_JSON);

        // then
        Mockito.verify(pricingService, Mockito.times(1)).computeTransactionCost(any(TransactionRequest.class));
        result.andDo(print())
              .andExpect(status().isInternalServerError())
              .andExpect(content().contentType(MediaType.APPLICATION_JSON))
              .andExpect(matchAll(
                    jsonPath("$.code").value("500"),
                    jsonPath("$.status").value("500"),
                    jsonPath("$.message").value("Something happened")
              ));
    }

    @Test
    public void whenComputeTransactionCostMovieTicketsException_thenErrorResponseBadRequest() throws Exception {

        //given
        Mockito.when(pricingService.computeTransactionCost(any(TransactionRequest.class)))
              .thenThrow(new MovieTicketsException(UNSUPPORTED_TICKET_TYPE, 10));
        //when
        ResultActions result = computeTransactionValue(TRANSACTION_REQUEST_JSON);

        // then
        Mockito.verify(pricingService, Mockito.times(1)).computeTransactionCost(any(TransactionRequest.class));
        result.andDo(print())
              .andExpect(status().isBadRequest())
              .andExpect(content().contentType(MediaType.APPLICATION_JSON))
              .andExpect(matchAll(
                    jsonPath("$.code").value("1"),
                    jsonPath("$.status").value("400"),
                    jsonPath("$.message").value("10 is an unsupported ticket type id.")
              ));
    }

    private ResultActions computeTransactionValue(String transaction) throws Exception {
        return performPut("/api/v1/pricing/transaction", transaction);
    }

    @Override
    public List<Object> getControllers() {
        return Collections.singletonList(objectUnderTest);
    }
}
