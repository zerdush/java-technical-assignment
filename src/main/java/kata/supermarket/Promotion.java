package kata.supermarket;

import java.math.BigDecimal;
import java.util.Map;

public interface Promotion {
    BigDecimal discount(Map<String, BigDecimal> items);
}
