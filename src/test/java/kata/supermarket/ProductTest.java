package kata.supermarket;

import kata.supermarket.promotions.BuyOneGetOneFreePromotion;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;

import static org.junit.jupiter.api.Assertions.*;

class ProductTest {
    @Test
    void givenAddPromotionThenPromotionAppearUnderPromotions(){
        UnitProduct unitProduct = new UnitProduct(BigDecimal.ZERO, "SKU");
        BuyOneGetOneFreePromotion promotion = new BuyOneGetOneFreePromotion(unitProduct);
        unitProduct.addPromotion(promotion);

        assertEquals(promotion, unitProduct.promotions().get(0));
    }
}