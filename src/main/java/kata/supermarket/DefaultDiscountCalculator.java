package kata.supermarket;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

public class DefaultDiscountCalculator implements DiscountCalculator{
    @Override
    public BigDecimal calculate(List<Item> items) {
        Map<String, BigDecimal> cumulativeItems = items.stream().collect(Collectors.
                groupingBy(Item::sku, Collectors.reducing(BigDecimal.ZERO, Item::amount, BigDecimal::add)));

        Set<Promotion> promotions = items.stream()
                .flatMap(item -> item.promotions().stream())
                .collect(Collectors.toSet());

        return promotions.stream().map(p -> p.discount(cumulativeItems)).reduce(BigDecimal::add).orElse(BigDecimal.ZERO)
                .setScale(2, RoundingMode.HALF_DOWN);
    }
}
