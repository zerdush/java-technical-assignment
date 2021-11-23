package kata.supermarket;

import kata.supermarket.promotions.Promotion;

import java.math.BigDecimal;
import java.util.List;

public class ItemByUnit implements Item {

    private final UnitProduct unitProduct;

    ItemByUnit(final UnitProduct unitProduct) {
        this.unitProduct = unitProduct;
    }

    public BigDecimal price() {
        return unitProduct.pricePerUnit();
    }

    @Override
    public String sku() {
        return unitProduct.sku();
    }

    @Override
    public List<Promotion> promotions() {
        return unitProduct.promotions();
    }

    @Override
    public BigDecimal amount() {
        return BigDecimal.ONE;
    }
}
