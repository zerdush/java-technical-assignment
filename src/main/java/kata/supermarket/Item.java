package kata.supermarket;

import java.math.BigDecimal;
import java.util.List;

public interface Item {
    BigDecimal price();
    String sku();
    List<Promotion> promotions();
    BigDecimal amount();
}
