package kata.supermarket.promotions;

import kata.supermarket.UnitProduct;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.Map;
import java.util.Optional;

public class BuyOneGetOneFreePromotion implements Promotion{
    private final UnitProduct unitProduct;

    public BuyOneGetOneFreePromotion(final UnitProduct unitProduct) {
        this.unitProduct = unitProduct;
    }

    @Override
    public BigDecimal discount(Map<String, BigDecimal> items) {
        return Optional.ofNullable(items.get(unitProduct.sku()))
                .map(amount -> amount
                        .divide(BigDecimal.valueOf(2), RoundingMode.DOWN)
                        .setScale(0, RoundingMode.DOWN)
                        .multiply(unitProduct.pricePerUnit()))
                .orElse(BigDecimal.ZERO);
    }
}
