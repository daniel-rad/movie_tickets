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
        if (originalPrice == null) {
            throw new IllegalArgumentException("Original amount cannot be null!");
        }
        if (discount == null || (discount.compareTo(new BigDecimal("0.0")) < 0 || discount.compareTo(new BigDecimal("100.0")) > 0)) {
            throw new IllegalArgumentException(String.format(
                  "Invalid value %s provided for discount. Expecting a value between 0 and 100.", discount != null ? discount.setScale(2).toString() : null));
        }
        BigDecimal percentage = discount.abs().multiply(BigDecimal.valueOf(0.01));
        BigDecimal discountAmount = originalPrice.abs().multiply(percentage);
        return originalPrice.abs().subtract(discountAmount).setScale(2, RoundingMode.CEILING);
    }
}
