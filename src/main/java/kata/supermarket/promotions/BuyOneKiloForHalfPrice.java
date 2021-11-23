package kata.supermarket.promotions;

import kata.supermarket.WeighedProduct;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.Map;
import java.util.Optional;

public class BuyOneKiloForHalfPrice implements Promotion {
    private final WeighedProduct product;

    public BuyOneKiloForHalfPrice(final WeighedProduct product) {
        this.product = product;
    }

    @Override
    public BigDecimal discount(Map<String, BigDecimal> items) {
        return Optional.ofNullable(items.get(product.sku()))
                .map(amount ->
                        amount.setScale(0, RoundingMode.DOWN)
                                .multiply(product.pricePerKilo())
                                .divide(BigDecimal.valueOf(2), RoundingMode.DOWN)
                        )
                .orElse(BigDecimal.ZERO);
    }
}
