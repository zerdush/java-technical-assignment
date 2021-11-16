package kata.supermarket;

import org.junit.jupiter.api.Test;

import java.math.BigDecimal;

import static org.junit.jupiter.api.Assertions.assertEquals;

class ProductTest {

    @Test
    void singleItemHasExpectedUnitPriceFromProduct() {
        final BigDecimal price = new BigDecimal("2.49");
        assertEquals(price, new Product(price, "SKU001").oneOf().price());
    }

    @Test
    void givenAddPromotionThenPromotionAppearUnderPromotions(){
        Product product = new Product(BigDecimal.ZERO, "SKU");
        BuyOneGetOneFreePromotion promotion = new BuyOneGetOneFreePromotion(product);
        product.addPromotion(promotion);

        assertEquals(promotion, product.promotions().get(0));
    }

    @Test
    void singleItemHasExpectedPromotions(){
        Product product = new Product(BigDecimal.ZERO, "SKU");
        BuyOneGetOneFreePromotion promotion = new BuyOneGetOneFreePromotion(product);
        product.addPromotion(promotion);
        Item item = product.oneOf();

        assertEquals(promotion, item.promotions().get(0));
    }

    @Test
    void singleItemHasExpectedSku(){
        Product product = new Product(BigDecimal.ZERO, "SKU");
        Item item = product.oneOf();

        assertEquals("SKU", item.sku());
    }
}