package edu.norco.cis18b.coffeeshop;

import org.junit.jupiter.api.Test;

import java.math.BigDecimal;

import static org.junit.jupiter.api.Assertions.*;

import edu.norco.cis18b.coffeeshop.grading.Points;
import edu.norco.cis18b.coffeeshop.grading.ScoreExtension;

@Points(2)
@ExtendWith(ScoreExtension.class)
public class Part2Test {

    @Test
    void lattePricingBySize() {
        Latte small = new Latte(Beverage.Size.SMALL);
        Latte med = new Latte(Beverage.Size.MEDIUM);
        Latte large = new Latte(Beverage.Size.LARGE);

        assertEquals(new BigDecimal("4.50"), small.getPrice());
        assertEquals(new BigDecimal("5.40"), med.getPrice());   // 4.50 * 1.20
        assertEquals(new BigDecimal("6.30"), large.getPrice()); // 4.50 * 1.40
    }

    @Test
    void coldBrewPricingBySize() {
        ColdBrew small = new ColdBrew(Beverage.Size.SMALL);
        ColdBrew med = new ColdBrew(Beverage.Size.MEDIUM);
        ColdBrew large = new ColdBrew(Beverage.Size.LARGE);

        assertEquals(new BigDecimal("4.00"), small.getPrice());
        assertEquals(new BigDecimal("4.80"), med.getPrice());   // 4.00 * 1.20
        assertEquals(new BigDecimal("5.60"), large.getPrice()); // 4.00 * 1.40
    }

    @Test
    void beveragesHaveNonBlankSkuAndName() {
        assertFalse(new Latte(Beverage.Size.MEDIUM).getSku().isBlank());
        assertFalse(new Latte(Beverage.Size.MEDIUM).getName().isBlank());
    }
}