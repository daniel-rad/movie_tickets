package org.drad.movie_tickets.service;

import org.drad.movie_tickets.model.TransactionRequest;
import org.drad.movie_tickets.model.TransactionResponse;

public interface PricingService {

    /**
     * Takes a transaction containing the list of customers and returns the total cost associated with that transaction
     * together with aach individual type of movie ticket present in that transaction, ordered alphabetically, and it's
     * quantity and total cost.
     * @param request - transaction with list of customers.
     * @return {@link TransactionResponse} object containing transaction cost.
     */
    TransactionResponse computeTransactionCost(TransactionRequest request);
}
