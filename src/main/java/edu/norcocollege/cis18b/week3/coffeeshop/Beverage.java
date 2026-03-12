package edu.norco.cis18b.week3.coffeeshop;

import java.math.BigDecimal;
import java.math.RoundingMode;

public abstract class Beverage extends MenuItem {

    public enum Size {
        SMALL,
        MEDIUM,
        LARGE
    }

    private final Size size;

    public Beverage(String sku, String name, BigDecimal basePrice, Size size) {
        this(sku, name, basePrice, size, false);
    }

    protected Beverage(String sku, String name, BigDecimal price, Size size, boolean finalPrice) {
        super(
            sku,
            name,
            finalPrice
                ? price.setScale(2, RoundingMode.HALF_UP)
                : calculatePrice(basePriceOrThrow(price), size)
        );

        if (size == null) {
            throw new IllegalArgumentException("size cannot be null");
        }
        this.size = size;
    }

    private static BigDecimal basePriceOrThrow(BigDecimal price) {
        if (price == null) {
            throw new IllegalArgumentException("basePrice cannot be null");
        }
        return price;
    }

    private static BigDecimal calculatePrice(BigDecimal basePrice, Size size) {
        if (size == null) {
            throw new IllegalArgumentException("size cannot be null");
        }

        return basePrice.multiply(multiplierFor(size)).setScale(2, RoundingMode.HALF_UP);
    }

    private static BigDecimal multiplierFor(Size size) {
        switch (size) {
            case SMALL:
                return new BigDecimal("1.00");
            case MEDIUM:
                return new BigDecimal("1.20");
            case LARGE:
                return new BigDecimal("1.40");
            default:
                throw new IllegalArgumentException("invalid size");
        }
    }

    public Size getSize() {
        return size;
    }

    public BigDecimal sizeMultiplier() {
        return multiplierFor(size);
    }
}