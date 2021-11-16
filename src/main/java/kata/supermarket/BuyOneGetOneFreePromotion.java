package kata.supermarket;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.Map;
import java.util.Optional;

public class BuyOneGetOneFreePromotion implements Promotion{
    private final Product product;

    public BuyOneGetOneFreePromotion(final Product product) {
        this.product = product;
    }

    @Override
    public BigDecimal discount(Map<String, BigDecimal> items) {
        return Optional.ofNullable(items.get(product.sku()))
                .map(amount -> amount
                        .divide(BigDecimal.valueOf(2), RoundingMode.DOWN)
                        .setScale(0, RoundingMode.DOWN)
                        .multiply(product.pricePerUnit()))
                .orElse(BigDecimal.ZERO);
    }
}
