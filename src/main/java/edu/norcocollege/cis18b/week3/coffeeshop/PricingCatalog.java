package edu.norco.cis18b.week3.coffeeshop;

import java.math.BigDecimal;

public class PricingCatalog {
    private static final PricingCatalog INSTANCE = new PricingCatalog();

    private PricingCatalog() {
    }

    public static PricingCatalog getInstance() {
        return INSTANCE;
    }

    public BigDecimal getBasePrice(String productKey) {
        if (productKey == null) {
            throw new IllegalArgumentException("productKey cannot be null");
        }

        return switch (productKey) {
            case "LATTE" -> new BigDecimal("4.50");
            case "COLD_BREW" -> new BigDecimal("4.00");
            case "CUSTOM" -> new BigDecimal("4.25");
            default -> throw new IllegalArgumentException("Unknown product key: " + productKey);
        };
    }
}