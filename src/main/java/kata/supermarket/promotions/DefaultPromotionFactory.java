package kata.supermarket.promotions;

import kata.supermarket.UnitProduct;
import kata.supermarket.WeighedProduct;

import java.math.BigDecimal;

public final class DefaultPromotionFactory {
    public Promotion createBuyOneKiloForHalfPrice(WeighedProduct product) {
        BuyOneKiloForHalfPrice buyOneKiloForHalfPrice = new BuyOneKiloForHalfPrice(product);
        product.addPromotion(buyOneKiloForHalfPrice);
        return buyOneKiloForHalfPrice;
    }

    public Promotion createLunchDealPromotion(UnitProduct sandwich, UnitProduct canOfDrink, UnitProduct packOfCrisps, BigDecimal valueOfDeal) {

        LunchDealPromotion lunchDealPromotion = new LunchDealPromotion(sandwich, canOfDrink, packOfCrisps, valueOfDeal);
        sandwich.addPromotion(lunchDealPromotion);
        canOfDrink.addPromotion(lunchDealPromotion);
        packOfCrisps.addPromotion(lunchDealPromotion);
        return lunchDealPromotion;
    }
}
