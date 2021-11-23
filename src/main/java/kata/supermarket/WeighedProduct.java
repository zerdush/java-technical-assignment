package kata.supermarket;

import java.math.BigDecimal;

public class WeighedProduct extends Product{
    private final BigDecimal pricePerKilo;

    public WeighedProduct(final BigDecimal pricePerKilo, final String sku) {
        super(sku);
        this.pricePerKilo = pricePerKilo;
    }

    public BigDecimal pricePerKilo() {
        return pricePerKilo;
    }

    public Item weighing(final BigDecimal kilos) {
        return new ItemByWeight(this, kilos);
    }
}
