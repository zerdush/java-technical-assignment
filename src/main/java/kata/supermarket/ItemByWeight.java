package kata.supermarket;

import kata.supermarket.promotions.Promotion;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.List;

public class ItemByWeight implements Item {

    private final WeighedProduct product;
    private final BigDecimal weightInKilos;

    ItemByWeight(final WeighedProduct product, final BigDecimal weightInKilos) {
        this.product = product;
        this.weightInKilos = weightInKilos;
    }

    public BigDecimal price() {
        return product.pricePerKilo().multiply(weightInKilos).setScale(2, RoundingMode.HALF_UP);
    }

    @Override
    public String sku() {
        return product.sku();
    }

    @Override
    public List<Promotion> promotions() {
        return product.promotions();
    }

    @Override
    public BigDecimal amount() {
        return weightInKilos;
    }
}
