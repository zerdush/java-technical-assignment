package kata.supermarket.promotions;

import kata.supermarket.UnitProduct;

import java.math.BigDecimal;
import java.util.Map;

public class LunchDealPromotion implements Promotion{

    private final UnitProduct sandwich;
    private final UnitProduct canOfDrink;
    private final UnitProduct packOfCrisps;
    private final BigDecimal valueOfDeal;

    public LunchDealPromotion(final UnitProduct sandwich, final UnitProduct canOfDrink,
                              final UnitProduct packOfCrisps, final BigDecimal valueOfDeal) {
        this.sandwich = sandwich;
        this.canOfDrink = canOfDrink;
        this.packOfCrisps = packOfCrisps;
        this.valueOfDeal = valueOfDeal;
    }

    @Override
    public BigDecimal discount(Map<String, BigDecimal> items) {
        BigDecimal sandwichCount = items.getOrDefault(sandwich.sku(), BigDecimal.ZERO);
        BigDecimal drinkCount = items.getOrDefault(canOfDrink.sku(), BigDecimal.ZERO);
        BigDecimal crispsCount = items.getOrDefault(packOfCrisps.sku(), BigDecimal.ZERO);

        BigDecimal minDeal = sandwichCount.min(drinkCount).min(crispsCount);

        if(minDeal.compareTo(BigDecimal.ZERO) > 0){
            return sandwich.pricePerUnit().add(canOfDrink.pricePerUnit()).add(packOfCrisps.pricePerUnit())
                    .multiply(minDeal)
                    .subtract(valueOfDeal.multiply(minDeal));
        }
        return BigDecimal.ZERO;
    }
}
