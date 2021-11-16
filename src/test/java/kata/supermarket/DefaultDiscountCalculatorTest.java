package kata.supermarket;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.math.BigDecimal;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.*;

class DefaultDiscountCalculatorTest {

    @DisplayName("calculator provides total discount when containing...")
    @MethodSource
    @ParameterizedTest(name = "{0}")
    void calculatorProvidesTotalDiscount(String description, String expectedDiscount, List<Item> items) {
        final DefaultDiscountCalculator defaultDiscountCalculator = new DefaultDiscountCalculator();
        BigDecimal actualDiscount = defaultDiscountCalculator.calculate(items);
        assertEquals(new BigDecimal(expectedDiscount), actualDiscount);
    }

    static Stream<Arguments> calculatorProvidesTotalDiscount() {
        return Stream.of(
                noItems(),
                aItemWithPromotion(),
                itemsWithMixPromotions(),
                itemsWithAndWithoutPromotions()
        );
    }

    private static Arguments noItems() {
        return Arguments.of("no items", "0.00", Collections.emptyList());
    }

    private static Arguments aItemWithPromotion() {
        Product product = aCanOfBeansWithBuyOneGetOneFreePromotion();
        return Arguments.of("a item with promotion", "10.00",
                Arrays.asList(product.oneOf(), product.oneOf()));
    }

    private static Arguments itemsWithMixPromotions() {
        Product product = aCanOfBeansWithBuyOneGetOneFreePromotion();
        WeighedProduct weighedProduct = aKiloOfTomato();
        return Arguments.of("items with mixed promotion", "14.00",
                Arrays.asList(product.oneOf(), product.oneOf(), weighedProduct.weighing(BigDecimal.valueOf(1))));
    }

    private static Arguments itemsWithAndWithoutPromotions() {
        Product product = aCanOfBeansWithBuyOneGetOneFreePromotion();
        WeighedProduct weighedProduct = aKiloOfTomato();
        return Arguments.of("items with and without promotions", "14.00",
                Arrays.asList(product.oneOf(), product.oneOf(),
                        weighedProduct.weighing(BigDecimal.valueOf(1)),
                        twoFiftyGramsOfAmericanSweets()));
    }

    private static Product aCanOfBeansWithBuyOneGetOneFreePromotion(){
        Product product = new Product(new BigDecimal("10.00"), "SKUCanOfBean");
        Promotion promotion = new BuyOneGetOneFreePromotion(product);
        product.addPromotion(promotion);
        return product;
    }

    private static WeighedProduct aKiloOfTomato(){
        WeighedProduct product = new WeighedProduct(new BigDecimal("8"), "SKUTomato");
        Promotion promotion = new BuyOneKiloForHalfPrice(product);
        product.addPromotion(promotion);
        return product;
    }

    private static Item twoFiftyGramsOfAmericanSweets() {
        return aKiloOfAmericanSweets().weighing(new BigDecimal(".25"));
    }

    private static WeighedProduct aKiloOfAmericanSweets() {
        return new WeighedProduct(new BigDecimal("4.99"), "SKU001");
    }
}