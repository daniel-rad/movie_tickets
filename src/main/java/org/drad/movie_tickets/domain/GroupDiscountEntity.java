package org.drad.movie_tickets.domain;

import java.math.BigDecimal;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
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
 * Represents the mapping to the <code>group_discounts</code> table.
 * This entity is used to keep the record of group discount entries.
 */
@Entity
@Table(name = "group_discounts")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Builder
@EqualsAndHashCode
public class GroupDiscountEntity {
    @Id
    @Column(name = "id")
    private Integer id;

    @Column(name = "ticket_id")
    private Integer ticketId;

    @Column(name = "discount")
    @NotNull
    @Min(0)
    @Max(100)
    private BigDecimal discount;

    @Column(name = "min_quantity")
    @NotNull
    private Integer minQuantity;
}
