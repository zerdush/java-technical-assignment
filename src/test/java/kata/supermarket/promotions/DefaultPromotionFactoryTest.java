package kata.supermarket.promotions;

import kata.supermarket.Product;
import kata.supermarket.UnitProduct;
import kata.supermarket.WeighedProduct;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.math.BigDecimal;
import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.*;

class DefaultPromotionFactoryTest {
    @DisplayName("Create correct promotion when call ...")
    @MethodSource
    @ParameterizedTest(name = "{0}")
    void promotionFactory(String description, Class expectedPromotionType, Promotion actualPromotion){
        assertEquals(expectedPromotionType, actualPromotion.getClass());
    }

    static Stream<Arguments> promotionFactory(){
        return Stream.of(
                createLunchDealPromotion(),
                createBuyOneGetOneFreePromotion(),
                createBuyOneKiloForHalfPrice()
        );
    }

    @DisplayName("Assign promotion to related products when call ...")
    @MethodSource
    @ParameterizedTest(name = "{0}")
    void promotionFactoryRelateToProduct(String description, Promotion expectedPromotion, Collection<Product> products){
        products.stream().forEach(p -> assertEquals(1, p.promotions().size()));
        products.stream().flatMap(p -> p.promotions().stream()).forEach(p -> assertEquals(expectedPromotion, p));
    }

    static Stream<Arguments> promotionFactoryRelateToProduct(){
        return Stream.of(
                createLunchDealPromotionRelatedProduct(),
                createBuyOneGetOneFreePromotionRelatedProduct(),
                createBuyOneKiloForHalfPriceRelatedProduct()
        );
    }

    private static Arguments createBuyOneGetOneFreePromotionRelatedProduct() {
        return null;
    }

    private static Arguments createLunchDealPromotionRelatedProduct() {
        List<UnitProduct> products = createUnitProducts(3);
        return Arguments.of("createLunchDealPromotion",
                new DefaultPromotionFactory().createLunchDealPromotion(products.get(0), products.get(1), products.get(2), BigDecimal.ONE),
                new ArrayList<>(products));
    }

    private static Arguments createBuyOneKiloForHalfPriceRelatedProduct() {
        WeighedProduct weighedProduct = new WeighedProduct(BigDecimal.ONE, "SKU");
        return Arguments.of("createBuyOneKiloForHalfPrice",
                new DefaultPromotionFactory().createBuyOneKiloForHalfPrice(weighedProduct),
                Collections.singleton(weighedProduct));
    }

    private static Arguments createBuyOneKiloForHalfPrice() {
        WeighedProduct weighedProduct = new WeighedProduct(BigDecimal.ONE, "SKU");
        return Arguments.of("createBuyOneKiloForHalfPrice",
                BuyOneKiloForHalfPrice.class,
                new DefaultPromotionFactory().createBuyOneKiloForHalfPrice(weighedProduct));
    }

    private static Arguments createBuyOneGetOneFreePromotion() {
        return null;
    }

    private static Arguments createLunchDealPromotion() {
        List<UnitProduct> products = createUnitProducts(3);
        return Arguments.of("createLunchDealPromotion", LunchDealPromotion.class,
                new DefaultPromotionFactory()
                        .createLunchDealPromotion(products.get(0), products.get(1), products.get(2), BigDecimal.ONE));
    }

    private static List<UnitProduct> createUnitProducts(int count){
        return IntStream.range(0, count)
                .mapToObj(i -> new UnitProduct(BigDecimal.ONE, "SKU" + i))
                .collect(Collectors.toList());
    }
}