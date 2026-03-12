package edu.norco.cis18b.week3.coffeeshop;

import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;

import static org.junit.jupiter.api.Assertions.*;

import edu.norco.cis18b.coffeeshop.grading.Points;
import edu.norco.cis18b.coffeeshop.grading.ScoreExtension;

@Points(2)
@ExtendWith(ScoreExtension.class)
public class Part4Test {

    @Test
    void customDrink_defaultsWork() {
        CustomDrink d = new CustomDrink.Builder().build();
        // MEDIUM multiplier 1.20 -> base 4.25 * 1.20 = 5.10
        assertEquals(new BigDecimal("5.10"), d.getPrice());
    }

    @Test
    void customDrink_pricingRules() {
        CustomDrink d = new CustomDrink.Builder()
                .size(Beverage.Size.LARGE)               // 1.40
                .espressoShots(3)                        // + (2 * 0.75) = +1.50
                .syrup(CustomDrink.Syrup.VANILLA)        // +0.50
                .addExtra("Cinnamon")                    // +0.25
                .addExtra("Whipped cream")               // +0.25
                .build();

        // base 4.25 * 1.40 = 5.95
        // + 1.50 + 0.50 + 0.50 = 8.45
        assertEquals(new BigDecimal("8.45"), d.getPrice());
    }

    @Test
    void customDrink_builderValidations() {
        assertThrows(IllegalArgumentException.class, () -> new CustomDrink.Builder().espressoShots(0));
        assertThrows(IllegalArgumentException.class, () -> new CustomDrink.Builder().addExtra("   "));
    }

    @Test
    void pricingCatalog_singletonAndPrices() {
        PricingCatalog a = PricingCatalog.getInstance();
        PricingCatalog b = PricingCatalog.getInstance();
        assertSame(a, b);

        assertEquals(new BigDecimal("4.50"), a.getBasePrice("LATTE"));
        assertEquals(new BigDecimal("4.00"), a.getBasePrice("COLD_BREW"));
        assertEquals(new BigDecimal("4.25"), a.getBasePrice("CUSTOM"));
        assertThrows(IllegalArgumentException.class, () -> a.getBasePrice("ESPRESSO"));
    }
}