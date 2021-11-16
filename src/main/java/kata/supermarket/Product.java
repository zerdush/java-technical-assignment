package kata.supermarket;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

public class Product {

    private final List<Promotion> promotions;
    private final String sku;
    private final BigDecimal pricePerUnit;

    public Product(final BigDecimal pricePerUnit, final String sku) {
        this.pricePerUnit = pricePerUnit;
        this.sku = sku;
        this.promotions = new ArrayList<>();
    }

    BigDecimal pricePerUnit() {
        return pricePerUnit;
    }

    public String sku() {
        return sku;
    }

    public List<Promotion> promotions(){
        return promotions;
    }

    public void addPromotion(Promotion promotion){
        promotions.add(promotion);
    }

    public Item oneOf() {
        return new ItemByUnit(this);
    }
}
