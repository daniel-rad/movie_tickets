package org.drad.movie_tickets.fixture;

import static org.drad.movie_tickets.fixture.TicketFixture.buildTicketResponseList;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.ArrayList;
import java.util.List;
import org.drad.movie_tickets.domain.GroupDiscountEntity;
import org.drad.movie_tickets.model.Customer;
import org.drad.movie_tickets.model.TransactionRequest;
import org.drad.movie_tickets.model.TransactionResponse;
import org.drad.movie_tickets.model.ticket.TicketResponse;

public class TransactionFixture {

    public static final String TRANSACTION_REQUEST_JSON = "{\n"
          + "    \"transactionId\": 3,\n"
          + "    \"customers\": [\n"
          + "        {\n"
          + "            \"name\": \"Jesse James\",\n"
          + "            \"age\": 36\n"
          + "        },\n"
          + "        {\n"
          + "            \"name\": \"Daniel Anderson\",\n"
          + "            \"age\": 95\n"
          + "        },\n"
          + "        {\n"
          + "            \"name\": \"Mary Jones\",\n"
          + "            \"age\": 15\n"
          + "        },\n"
          + "        {\n"
          + "            \"name\": \"Michelle Parker\",\n"
          + "            \"age\": 10\n"
          + "        }\n"
          + "    ]\n"
          + "}";

    public static TransactionRequest buildTransactionRequest() {
        return TransactionRequest.builder()
              .transactionId(3)
              .customers(buildCustomerList())
              .build();
    }

    public static List<Customer> buildCustomerList() {
        List<Customer> customers = new ArrayList<>();
        customers.add(Customer.builder().name("Jesse James").age(36).build());
        customers.add(Customer.builder().name("Daniel Anderson").age(95).build());
        customers.add(Customer.builder().name("Mary Jones").age(15).build());
        customers.add(Customer.builder().name("Michelle Parker").age(10).build());
        return customers;
    }

    public static TransactionResponse buildTransactionResponse() {
        return TransactionResponse.builder()
              .transactionId(3)
              .tickets(buildTicketResponseList())
              .totalCostAmount(new BigDecimal("59.50"))
              .build();
    }

    public static GroupDiscountEntity buildGroupDiscountEntity(Integer ticketId) {
        return GroupDiscountEntity.builder().ticketId(ticketId).minQuantity(3).discount(new BigDecimal("25")).build();
    }
}
