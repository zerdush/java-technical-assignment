package kata.supermarket;

import java.math.BigDecimal;
import java.util.List;

public class StaticDiscountCalculator implements DiscountCalculator{
    private BigDecimal staticDiscount;

    public StaticDiscountCalculator(final BigDecimal staticDiscount) {
        this.staticDiscount = staticDiscount;
    }

    @Override
    public BigDecimal calculate(List<Item> items) {
        return staticDiscount;
    }
}
