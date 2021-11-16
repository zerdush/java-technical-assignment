package kata.supermarket;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.*;
import java.util.stream.Collectors;

public class Basket {
    private final List<Item> items;

    public Basket() {
        this.items = new ArrayList<>();
    }

    public void add(final Item item) {
        this.items.add(item);
    }

    List<Item> items() {
        return Collections.unmodifiableList(items);
    }

    public BigDecimal total() {
        return new TotalCalculator().calculate();
    }

    private class TotalCalculator {
        private final List<Item> items;

        TotalCalculator() {
            this.items = items();
        }

        private BigDecimal subtotal() {
            return items.stream().map(Item::price)
                    .reduce(BigDecimal::add)
                    .orElse(BigDecimal.ZERO)
                    .setScale(2, RoundingMode.HALF_UP);
        }

        /**
         * TODO: This could be a good place to apply the results of
         *  the discount calculations.
         *  It is not likely to be the best place to do those calculations.
         *  Think about how Basket could interact with something
         *  which provides that functionality.
         */
        private BigDecimal discounts() {
            Map<String, BigDecimal> cumulativeItems = items.stream().collect(Collectors.
                    groupingBy(Item::sku, Collectors.reducing(BigDecimal.ZERO, Item::amount, BigDecimal::add)));

            Set<Promotion> promotions = items.stream()
                    .flatMap(item -> item.promotions().stream())
                    .collect(Collectors.toSet());

            return promotions.stream().map(p -> p.discount(cumulativeItems)).reduce(BigDecimal::add).orElse(BigDecimal.ZERO)
                    .setScale(2, RoundingMode.HALF_DOWN);
        }

        private BigDecimal calculate() {
            return subtotal().subtract(discounts());
        }
    }
}
