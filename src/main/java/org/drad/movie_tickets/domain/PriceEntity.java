package org.drad.movie_tickets.domain;

import java.math.BigDecimal;
import java.util.List;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * Represents the mapping to the <code>prices</code> table.
 * This entity is used to keep the record of price entries.
 */
@Entity
@Table(name = "prices")
@NoArgsConstructor
@Getter
@Setter
@EqualsAndHashCode
public class PriceEntity {
    @Id
    @Column(name = "id")
    private Integer id;

    @Column(name = "description")
    private String description;

    @Column(name = "amount")
    @NotNull
    @Min(0)
    private BigDecimal amount;

    @OneToMany(mappedBy="price")
    private List<TicketEntity> tickets;
}
