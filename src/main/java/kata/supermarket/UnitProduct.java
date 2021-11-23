package kata.supermarket;

import java.math.BigDecimal;

public class UnitProduct extends Product {

    private final BigDecimal pricePerUnit;

    public UnitProduct(final BigDecimal pricePerUnit, final String sku) {
        super(sku);
        this.pricePerUnit = pricePerUnit;
    }

    public BigDecimal pricePerUnit() {
        return pricePerUnit;
    }

    public Item oneOf() {
        return new ItemByUnit(this);
    }
}
