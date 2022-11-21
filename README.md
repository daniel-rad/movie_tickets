# Movie-Tickets API

### Description
This is a very simple demo application which exposes one REST API for calculating the movie ticket costs 
of a transaction, based on the number and age of the customers.

### Prerequisites

- Java8
- Gradle 7.3.1

### Build

Execute the following gradle command in the terminal, from the project root folder: 

```./gradlew clean build```

### Deploy

Execute the following Java command in the terminal, from the project root folder: 

```java -jar build/libs/movie_tickets.jar org.drad.movie_tickets.Application --spring.profiles.active=local```

### Testing

Test the API by executing the following cURL:

```
curl --location --request PUT 'http://localhost:8081/movie-tickets/api/v1/pricing/transaction' \
--header 'Content-Type: application/json' \
--data-raw '{
    "transactionId": 3,
    "customers": [
        {
            "name": "Jesse James",
            "age": 36
        },
        {
            "name": "Daniel Anderson",
            "age": 95
        },
        {
            "name": "Mary Jones",
            "age": 15
        },
        {
            "name": "Michelle Parker",
            "age": 10
        }
    ]
}'
```

### Future enhancements

- Generate specification with OpenAPI