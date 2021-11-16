package kata.supermarket;

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

class BuyOneGetOneFreePromotionTest {

    @DisplayName("BuyOneGetOneFreePromotion calculates discount when containing...")
    @MethodSource
    @ParameterizedTest(name = "{0}")
    void promotionDiscount(String description, BigDecimal expectedDiscount, Map<String, BigDecimal> cumulativeItems){
        final BuyOneGetOneFreePromotion promotion =
                new BuyOneGetOneFreePromotion(new Product(BigDecimal.valueOf(10), "SKUWithPromotion001"));
        BigDecimal actualDiscount = promotion.discount(cumulativeItems);
        assertEquals(expectedDiscount, actualDiscount);
    }

    static Stream<Arguments> promotionDiscount() {
        return Stream.of(
                noItems(),
                itemsWithNoPromotion(),
                onlyOneItemInPromotion(),
                twoItemsInPromotion()
//                threeItemsInPromotion()
        );
    }

    private static Arguments noItems() {
        return Arguments.of("empty basket", BigDecimal.ZERO, Collections.emptyMap());
    }

    private static Arguments itemsWithNoPromotion(){
        return Arguments.of("items with no promotion",
                BigDecimal.ZERO,
                new HashMap<String, BigDecimal>(){{
                    put("SKU001", BigDecimal.valueOf(2));}});
    }

    private static Arguments onlyOneItemInPromotion() {
        return Arguments.of("only One item with promotion",
                BigDecimal.ZERO,
                new HashMap<String, BigDecimal>(){{
                    put("SKUWithPromotion001", BigDecimal.valueOf(1));}});
    }

    private static Arguments twoItemsInPromotion() {
        return Arguments.of("two items with promotion",
                BigDecimal.valueOf(10),
                new HashMap<String, BigDecimal>(){{
                    put("SKUWithPromotion001", BigDecimal.valueOf(2));}});
    }

}