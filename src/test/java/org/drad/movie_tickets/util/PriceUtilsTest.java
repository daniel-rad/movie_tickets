package org.drad.movie_tickets.util;


import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.is;
import static org.junit.jupiter.api.Assertions.assertEquals;

import java.math.BigDecimal;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
public class PriceUtilsTest {


    @ParameterizedTest
    @CsvSource({
          "25.00, 30.00, 17.50"
    })
    public void whenValidValues_testDiscountCalculation(BigDecimal originalAmount, BigDecimal discount, BigDecimal result) {
        assertEquals(PriceUtils.getDiscountedPrice(originalAmount, discount), result);
    }

    @Test
    public void whenNullAmount_throwIllegalArgumentException() {
        IllegalArgumentException exception = Assertions.assertThrows(IllegalArgumentException.class, () -> PriceUtils.getDiscountedPrice(null, new BigDecimal("10")));
        assertThat(exception.getMessage(), is(equalTo("Original amount cannot be null!")));
    }

    @Test
    public void whenNullDiscount_throwIllegalArgumentException() {
        IllegalArgumentException exception = Assertions.assertThrows(IllegalArgumentException.class, () -> PriceUtils.getDiscountedPrice(new BigDecimal("10"), null));
        assertThat(exception.getMessage(), is(equalTo("Invalid value null provided for discount. Expecting a value between 0 and 100.")));
    }

    @Test
    public void whenInvalidDiscount_throwIllegalArgumentException() {
        IllegalArgumentException exception = Assertions.assertThrows(IllegalArgumentException.class, () -> PriceUtils.getDiscountedPrice(new BigDecimal("10"), new BigDecimal("110")));
        assertThat(exception.getMessage(), is(equalTo("Invalid value 110.00 provided for discount. Expecting a value between 0 and 100.")));
    }
}
