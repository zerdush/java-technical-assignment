package kata.supermarket;

import kata.supermarket.promotions.BuyOneGetOneFreePromotion;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;

import static org.junit.jupiter.api.Assertions.assertEquals;

class UnitProductTest {

    @Test
    void singleItemHasExpectedUnitPriceFromProduct() {
        final BigDecimal price = new BigDecimal("2.49");
        assertEquals(price, new UnitProduct(price, "SKU001").oneOf().price());
    }

    @Test
    void singleItemHasExpectedPromotions(){
        UnitProduct unitProduct = new UnitProduct(BigDecimal.ZERO, "SKU");
        BuyOneGetOneFreePromotion promotion = new BuyOneGetOneFreePromotion(unitProduct);
        unitProduct.addPromotion(promotion);
        Item item = unitProduct.oneOf();

        assertEquals(promotion, item.promotions().get(0));
    }

    @Test
    void singleItemHasExpectedSku(){
        UnitProduct unitProduct = new UnitProduct(BigDecimal.ZERO, "SKU");
        Item item = unitProduct.oneOf();

        assertEquals("SKU", item.sku());
    }
}