DROP TABLE IF EXISTS group_discounts;
CREATE TABLE IF NOT EXISTS group_discounts
(
    id    INT PRIMARY KEY,
    ticket_id  INT NOT NULL,
    discount DOUBLE PRECISION NOT NULL,
    min_quantity INT NOT NULL
);

DROP TABLE IF EXISTS tickets;
CREATE TABLE IF NOT EXISTS tickets
(
    id    INT PRIMARY KEY,
    `type`  VARCHAR(50) NOT NULL,
    min_age INT NOT NULL,
    max_age INT NOT NULL,
    price_id INT NOT NULL
    );

DROP TABLE IF EXISTS prices;
CREATE TABLE IF NOT EXISTS prices
(
    id    INT PRIMARY KEY,
    description  VARCHAR(50) NOT NULL,
    amount DOUBLE PRECISION NOT NULL
);

ALTER TABLE tickets ADD CONSTRAINT `FK_TICKETS_PRICES` FOREIGN KEY (price_id) REFERENCES prices (id);
ALTER TABLE group_discounts ADD CONSTRAINT `FK_GROUP_DISCOUNTS_TICKETS` FOREIGN KEY (ticket_id) REFERENCES tickets (id);