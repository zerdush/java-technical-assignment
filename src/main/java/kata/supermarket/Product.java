package kata.supermarket;

import kata.supermarket.promotions.Promotion;

import java.util.ArrayList;
import java.util.List;

public class Product {
    private final List<Promotion> promotions;
    private final String sku;

    protected Product(final String sku) {
        this.promotions = new ArrayList<>();
        this.sku = sku;
    }

    public String sku() {
        return sku;
    }

    public List<Promotion> promotions() {
        return promotions;
    }

    public void addPromotion(Promotion promotion) {
        promotions.add(promotion);
    }
}
