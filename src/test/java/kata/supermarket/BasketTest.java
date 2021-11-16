package kata.supermarket;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.math.BigDecimal;
import java.util.Arrays;
import java.util.Collections;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.assertEquals;

class BasketTest {

    @DisplayName("basket provides its total value when containing...")
    @MethodSource
    @ParameterizedTest(name = "{0}")
    void basketProvidesTotalValue(String description, String expectedTotal, Iterable<Item> items) {
        final Basket basket = new Basket();
        items.forEach(basket::add);
        assertEquals(new BigDecimal(expectedTotal), basket.total());
    }

    static Stream<Arguments> basketProvidesTotalValue() {
        return Stream.of(
                noItems(),
                aSingleItemPricedPerUnit(),
                multipleItemsPricedPerUnit(),
                aSingleItemPricedByWeight(),
                multipleItemsPricedByWeight(),
                aItemWithPromotion(),
                itemsWithMixPromotions(),
                itemsWithAndWithoutPromotions()
        );
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
        return Arguments.of("items with and without promotions", "15.25",
                Arrays.asList(product.oneOf(), product.oneOf(),
                        weighedProduct.weighing(BigDecimal.valueOf(1)),
                        twoFiftyGramsOfAmericanSweets()));
    }

    private static Arguments aSingleItemPricedByWeight() {
        return Arguments.of("a single weighed item", "1.25", Collections.singleton(twoFiftyGramsOfAmericanSweets()));
    }

    private static Arguments multipleItemsPricedByWeight() {
        return Arguments.of("multiple weighed items", "1.85",
                Arrays.asList(twoFiftyGramsOfAmericanSweets(), twoHundredGramsOfPickAndMix())
        );
    }

    private static Arguments multipleItemsPricedPerUnit() {
        return Arguments.of("multiple items priced per unit", "2.04",
                Arrays.asList(aPackOfDigestives(), aPintOfMilk()));
    }

    private static Arguments aSingleItemPricedPerUnit() {
        return Arguments.of("a single item priced per unit", "0.49", Collections.singleton(aPintOfMilk()));
    }

    private static Arguments noItems() {
        return Arguments.of("no items", "0.00", Collections.emptyList());
    }

    private static Item aPintOfMilk() {
        return new Product(new BigDecimal("0.49"), "SKU001").oneOf();
    }

    private static Item aPackOfDigestives() {
        return new Product(new BigDecimal("1.55"), "SKU001").oneOf();
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

    private static WeighedProduct aKiloOfAmericanSweets() {
        return new WeighedProduct(new BigDecimal("4.99"), "SKU001");
    }

    private static Item twoFiftyGramsOfAmericanSweets() {
        return aKiloOfAmericanSweets().weighing(new BigDecimal(".25"));
    }

    private static WeighedProduct aKiloOfPickAndMix() {
        return new WeighedProduct(new BigDecimal("2.99"), "SKU001");
    }

    private static Item twoHundredGramsOfPickAndMix() {
        return aKiloOfPickAndMix().weighing(new BigDecimal(".2"));
    }
}