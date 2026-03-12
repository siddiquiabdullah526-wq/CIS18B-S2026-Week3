package edu.norco.cis18b.week3.coffeeshop;

import java.math.BigDecimal;

public interface PaymentMethod {
    PaymentReceipt pay(String orderId, BigDecimal amount);
}