package kata.supermarket;

import java.math.BigDecimal;

public class Product {

    private final String sku;
    private final BigDecimal pricePerUnit;

    public Product(final BigDecimal pricePerUnit, final String sku) {
        this.pricePerUnit = pricePerUnit;
        this.sku = sku;
    }

    BigDecimal pricePerUnit() {
        return pricePerUnit;
    }

    public String sku() {
        return sku;
    }

    public Item oneOf() {
        return new ItemByUnit(this);
    }
}
