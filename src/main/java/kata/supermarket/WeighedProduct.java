package kata.supermarket;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

public class WeighedProduct {

    private final List<Promotion> promotions;
    private final BigDecimal pricePerKilo;
    private final String sku;

    public WeighedProduct(final BigDecimal pricePerKilo, final String sku) {
        this.pricePerKilo = pricePerKilo;
        this.sku = sku;
        this.promotions = new ArrayList<>();
    }

    public String sku() {
        return sku;
    }

    BigDecimal pricePerKilo() {
        return pricePerKilo;
    }

    public Item weighing(final BigDecimal kilos) {
        return new ItemByWeight(this, kilos);
    }

    public List<Promotion> promotions(){
        return promotions;
    }

    public void addPromotion(Promotion promotion){
        promotions.add(promotion);
    }
}
