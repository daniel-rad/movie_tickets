package org.drad.movie_tickets.api;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import javax.validation.Valid;
import org.drad.movie_tickets.model.TransactionRequest;
import org.drad.movie_tickets.model.TransactionResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Validated
@Tag(name = "Pricing API v1", description = "This API provides pricing functionality for the movie_tickets application")
@RequestMapping("/api/v1/pricing")
public interface PricingApi {

    /**
     * PUT /v1/pricing/transaction : Computes the cost of a transaction
     *
     * @param transactionRequest Pet object that needs to be added to the store (required)
     * @return successful operation (status code 200)
     *         or Invalid input (status code 422)
     */
    @Operation(
        operationId = "computeTransactionCost",
        summary = "Computes the cost of a transaction",
        tags = { "transactions" },
        responses = {
            @ApiResponse(responseCode = "200", description = "successful operation", content = {
                @Content(mediaType = "application/json", schema = @Schema(implementation = TransactionResponse.class))
            }),
            @ApiResponse(responseCode = "422", description = "Invalid input")
        }
    )
    @RequestMapping(
        method = RequestMethod.PUT,
        value = "/transaction",
        produces = { "application/json" },
        consumes = { "application/json" }
    )
    default ResponseEntity<TransactionResponse> computeTransactionCost(
        @Parameter(name = "TransactionRequest", description = "Pet object that needs to be added to the store", required = true) @Valid @RequestBody TransactionRequest transactionRequest
    ) {
        return new ResponseEntity<>(HttpStatus.NOT_IMPLEMENTED);
    }

}
