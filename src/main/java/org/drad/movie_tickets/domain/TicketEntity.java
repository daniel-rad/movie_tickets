package org.drad.movie_tickets.domain;

import java.math.BigDecimal;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * Represents the mapping to the <code>tickets</code> table.
 * This entity is used to keep the record of ticket entries.
 */
@Entity
@Table(name = "tickets")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Builder
@EqualsAndHashCode
public class TicketEntity {

    @Id
    @Column(name = "id")
    private Integer id;

    @Column(name = "type")
    private String type;

    @Column(name = "min_age")
    @NotNull
    @Min(0)
    @Max(120)
    private Integer minAge;

    @Column(name = "max_age")
    @NotNull
    @Min(0)
    @Max(120)
    private Integer maxAge;

    @ManyToOne
    @JoinColumn(name = "price_id")
    @NotNull
    private PriceEntity price;
}
