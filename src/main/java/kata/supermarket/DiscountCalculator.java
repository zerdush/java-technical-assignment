package kata.supermarket;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;

public interface DiscountCalculator {
    BigDecimal calculate(List<Item> items);
}
