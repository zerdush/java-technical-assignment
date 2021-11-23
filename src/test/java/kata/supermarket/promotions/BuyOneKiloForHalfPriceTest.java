package kata.supermarket.promotions;

import kata.supermarket.WeighedProduct;
import kata.supermarket.promotions.BuyOneKiloForHalfPrice;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.math.BigDecimal;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.*;

class BuyOneKiloForHalfPriceTest {
    @DisplayName("BuyOneKiloForHalfPrice calculates discount when containing...")
    @MethodSource
    @ParameterizedTest(name = "{0}")
    void promotionDiscount(String description, BigDecimal expectedDiscount, Map<String, BigDecimal> cumulativeItems){
        final BuyOneKiloForHalfPrice promotion =
                new BuyOneKiloForHalfPrice(new WeighedProduct(BigDecimal.valueOf(10), "SKUWithPromotion001"));
        BigDecimal actualDiscount = promotion.discount(cumulativeItems);
        assertEquals(expectedDiscount, actualDiscount);
    }

    static Stream<Arguments> promotionDiscount() {
        return Stream.of(
                noItems(),
                itemsWithNoPromotion(),
                onlyHalfKiloItemInPromotion(),
                oneKiloItemsInPromotion(),
                oneKiloAndHalfItemsInPromotion(),
                threeKilosItemsInPromotion()
        );
    }

    private static Arguments threeKilosItemsInPromotion() {
        return Arguments.of("three kilos with promotion", BigDecimal.valueOf(15),
                new HashMap<String, BigDecimal>(){{
                    put("SKUWithPromotion001", BigDecimal.valueOf(3));}});

    }

    private static Arguments oneKiloAndHalfItemsInPromotion() {
        return Arguments.of("one kilo and half with promotion", BigDecimal.valueOf(5),
                new HashMap<String, BigDecimal>(){{
                    put("SKUWithPromotion001", BigDecimal.valueOf(1.5));}});

    }

    private static Arguments oneKiloItemsInPromotion() {
        return Arguments.of("one kilo with promotion",
                BigDecimal.valueOf(5),
                new HashMap<String, BigDecimal>(){{
                    put("SKUWithPromotion001", BigDecimal.valueOf(1));}});
    }

    private static Arguments onlyHalfKiloItemInPromotion() {
        return Arguments.of("only half kilo with promotion",
                BigDecimal.ZERO,
                new HashMap<String, BigDecimal>(){{
                    put("SKUWithPromotion001", BigDecimal.valueOf(0.5));}});
    }

    private static Arguments itemsWithNoPromotion() {
        return Arguments.of("items with no promotion",
                BigDecimal.ZERO,
                new HashMap<String, BigDecimal>(){{
                    put("SKU001", BigDecimal.valueOf(2));}});
    }

    private static Arguments noItems() {
        return Arguments.of("empty basket", BigDecimal.ZERO, Collections.emptyMap());
    }
}