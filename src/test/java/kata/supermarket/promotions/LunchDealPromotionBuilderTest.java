package kata.supermarket.promotions;

import kata.supermarket.UnitProduct;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.util.HashMap;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

public class LunchDealPromotionBuilderTest {

    private LunchDealPromotion lunchDealPromotion;
    private UnitProduct sandwichProduct;
    private UnitProduct canOfDrinkProduct;
    private UnitProduct packOfCrispsProduct;

    @BeforeEach
    public void setup(){
        sandwichProduct = new UnitProduct(BigDecimal.ONE, "SandwichSku");
        canOfDrinkProduct = new UnitProduct(BigDecimal.ONE, "CanOfDrinkSku");
        packOfCrispsProduct = new UnitProduct(BigDecimal.ONE, "PackOfCrispsSku");

        LunchDealPromotion.Builder builder = new LunchDealPromotion.Builder();
        lunchDealPromotion = builder
                .setSandwich(sandwichProduct)
                .setDrink(canOfDrinkProduct)
                .setCrisps(packOfCrispsProduct)
                .setValueOfDeal(BigDecimal.ONE)
                .build();
    }

    @Test
    public void whenBuildThenReturnPromotion(){
        assertNotNull(lunchDealPromotion);
    }

    @Test
    public void whenBuildThenSetPromotionFields(){
        BigDecimal actualDiscount = lunchDealPromotion.discount(new HashMap<String, BigDecimal>(){{
            put(sandwichProduct.sku(), BigDecimal.ONE);
            put(canOfDrinkProduct.sku(), BigDecimal.ONE);
            put(packOfCrispsProduct.sku(), BigDecimal.ONE);

        }} );

        assertEquals(BigDecimal.valueOf(2), actualDiscount);
    }
}
