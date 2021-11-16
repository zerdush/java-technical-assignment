package kata.supermarket;

import java.math.BigDecimal;
import java.util.List;

public class ItemByUnit implements Item {

    private final Product product;

    ItemByUnit(final Product product) {
        this.product = product;
    }

    public BigDecimal price() {
        return product.pricePerUnit();
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
        return BigDecimal.ONE;
    }
}
