package org.drad.movie_tickets.controller;

import lombok.RequiredArgsConstructor;
import org.drad.movie_tickets.api.PricingApi;
import org.drad.movie_tickets.model.TransactionRequest;
import org.drad.movie_tickets.model.TransactionResponse;
import org.drad.movie_tickets.service.PricingService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

/**
 * Controller class exposes the all available operations related to pricing.
 */
@RestController
@RequiredArgsConstructor
public class PricingController implements PricingApi {

    private final PricingService pricingService;

    @Override
    public ResponseEntity<TransactionResponse> computeTransactionCost(TransactionRequest transactionRequest) {
        TransactionResponse transactionResponse = pricingService.computeTransactionCost(transactionRequest);
        return ResponseEntity.ok(transactionResponse);
    }
}
