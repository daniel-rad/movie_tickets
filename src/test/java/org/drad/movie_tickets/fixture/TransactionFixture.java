package org.drad.movie_tickets.fixture;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.ArrayList;
import java.util.List;
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

    public static List<TicketResponse> buildTicketResponseList() {
        List<TicketResponse> tickets = new ArrayList<>();

        tickets.add(TicketResponse.builder().ticketType("Adult").quantity(1).totalCostAmount(new BigDecimal("25.00")).build());
        tickets.add(TicketResponse.builder().ticketType("Children").quantity(1).totalCostAmount(new BigDecimal("5.00")).build());
        tickets.add(TicketResponse.builder().ticketType("Senior").quantity(1).totalCostAmount(new BigDecimal("17.50")).build());
        tickets.add(TicketResponse.builder().ticketType("Teen").quantity(1).totalCostAmount(new BigDecimal("12.00")).build());
        return tickets;
    }
}
