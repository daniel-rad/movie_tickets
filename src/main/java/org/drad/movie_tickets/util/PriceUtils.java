package org.drad.movie_tickets.util;

import java.math.BigDecimal;
import java.math.RoundingMode;
import org.hibernate.annotations.GenericGenerator;

public class PriceUtils {

    private PriceUtils() {

    }

    /**
     * Applies the discount on the original price.
     * Discount can be any value between 0..100
     * @param originalPrice
     * @param discount
     * @return
     */
    public static BigDecimal getDiscountedPrice(BigDecimal originalPrice, BigDecimal discount) {
        BigDecimal percentage = discount.abs().multiply(BigDecimal.valueOf(0.01));
        BigDecimal discountAmount = originalPrice.abs().multiply(percentage);
        return originalPrice.abs().subtract(discountAmount).setScale(2, RoundingMode.CEILING);
    }
}
