package kata.supermarket.promotions;

import kata.supermarket.UnitProduct;
import kata.supermarket.promotions.LunchDealPromotion;
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

class LunchDealPromotionTest {
    @DisplayName("LunchDealPromotion calculates discount when containing...")
    @MethodSource
    @ParameterizedTest(name = "{0}")
    void promotionDiscount(String description, BigDecimal expectedDiscount, Map<String, BigDecimal> cumulativeItems){
        UnitProduct sandwich = new UnitProduct(BigDecimal.valueOf(5), "SandwichSKU");
        UnitProduct canOfDrink = new UnitProduct(BigDecimal.valueOf(3), "CanOfDrinkSKU");
        UnitProduct packOfCrisps = new UnitProduct(BigDecimal.valueOf(1), "PackOfCrispSKU");
        final LunchDealPromotion lunchDealPromotion = new LunchDealPromotion(sandwich, canOfDrink, packOfCrisps, BigDecimal.valueOf(2));
        BigDecimal actualDiscount = lunchDealPromotion.discount(cumulativeItems);
        assertEquals(expectedDiscount, actualDiscount);
    }

    static Stream<Arguments> promotionDiscount() {
        return Stream.of(
                noItems(),
                itemsWithNoPromotion(),
                onlyOneItemFromLunchDeal(),
                allItemsFromLunchDeal(),
                allItemsFromLunchDealPlusExtraItem(),
                allItemsFromLunchDealTwice(),
                allItemsFromLunchDealTwicePlusExtraItem()
        );
    }

    private static Arguments allItemsFromLunchDealTwicePlusExtraItem() {
        return Arguments.of("all items from lunch deal twice", BigDecimal.valueOf(14), new HashMap<String ,BigDecimal>(){{
            put("SandwichSKU", BigDecimal.valueOf(2));
            put("CanOfDrinkSKU", BigDecimal.valueOf(2));
            put("PackOfCrispSKU", BigDecimal.valueOf(3));
        }});
    }

    private static Arguments allItemsFromLunchDealTwice() {
        return Arguments.of("all items from lunch deal twice", BigDecimal.valueOf(14), new HashMap<String ,BigDecimal>(){{
            put("SandwichSKU", BigDecimal.valueOf(2));
            put("CanOfDrinkSKU", BigDecimal.valueOf(2));
            put("PackOfCrispSKU", BigDecimal.valueOf(2));
        }});
    }

    private static Arguments allItemsFromLunchDealPlusExtraItem() {
        return Arguments.of("all items from lunch deal plus extra item", BigDecimal.valueOf(7), new HashMap<String ,BigDecimal>(){{
            put("SandwichSKU", BigDecimal.valueOf(2));
            put("CanOfDrinkSKU", BigDecimal.ONE);
            put("PackOfCrispSKU", BigDecimal.ONE);
        }});
    }

    private static Arguments allItemsFromLunchDeal() {
        return Arguments.of("all items from lunch deal", BigDecimal.valueOf(7), new HashMap<String, BigDecimal>(){{
            put("SandwichSKU", BigDecimal.ONE);
            put("CanOfDrinkSKU", BigDecimal.ONE);
            put("PackOfCrispSKU", BigDecimal.ONE);
        }});
    }

    private static Arguments onlyOneItemFromLunchDeal() {
        return Arguments.of("only one item from lunch deal", BigDecimal.ZERO, new HashMap<String, BigDecimal>(){{
            put("SandwichSKU", BigDecimal.ONE);
        }});
    }

    private static Arguments itemsWithNoPromotion() {
        return Arguments.of("items with no promotion", BigDecimal.ZERO, new HashMap<String, BigDecimal>(){{
            put("SKU001", BigDecimal.ONE);}});
    }

    private static Arguments noItems() {
        return Arguments.of("empty basket", BigDecimal.ZERO, Collections.emptyMap());
    }
}