package kata.supermarket;

import java.math.BigDecimal;

public class WeighedProduct {

    private final BigDecimal pricePerKilo;
    private final String sku;

    public WeighedProduct(final BigDecimal pricePerKilo, final String sku) {
        this.pricePerKilo = pricePerKilo;
        this.sku = sku;
    }

    public String sku() {
        return sku;
    }

    BigDecimal pricePerKilo() {
        return pricePerKilo;
    }

    public Item weighing(final BigDecimal kilos) {
        return new ItemByWeight(this, kilos);
    }
}
