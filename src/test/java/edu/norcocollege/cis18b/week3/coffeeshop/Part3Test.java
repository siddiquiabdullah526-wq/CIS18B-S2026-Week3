package edu.norco.cis18b.coffeeshop;

import org.junit.jupiter.api.Test;

import java.math.BigDecimal;

import static org.junit.jupiter.api.Assertions.*;

import edu.norco.cis18b.coffeeshop.grading.Points;
import edu.norco.cis18b.coffeeshop.grading.ScoreExtension;

@Points(2)
@ExtendWith(ScoreExtension.class)
public class Part3Test {

    @Test
    void creditCardPayment_validAndFormatsMethod() {
        CreditCardPayment p = new CreditCardPayment("1234");
        PaymentReceipt r = p.pay("ORD-9", new BigDecimal("10.00"));

        assertEquals("ORD-9", r.orderId());
        assertEquals(new BigDecimal("10.00"), r.amount());
        assertEquals("CREDIT_CARD(****1234)", r.method());
        assertNotNull(r.timestamp());
    }

    @Test
    void creditCardPayment_rejectsInvalidLast4() {
        assertThrows(IllegalArgumentException.class, () -> new CreditCardPayment(null));
        assertThrows(IllegalArgumentException.class, () -> new CreditCardPayment("12"));
        assertThrows(IllegalArgumentException.class, () -> new CreditCardPayment("12345"));
    }

    @Test
    void giftCardPayment_deductsAndTracksBalance() {
        GiftCardPayment gc = new GiftCardPayment(new BigDecimal("25.00"));
        PaymentReceipt r = gc.pay("ORD-10", new BigDecimal("7.50"));

        assertEquals(new BigDecimal("17.50"), gc.getBalance());
        assertEquals("GIFT_CARD", r.method());
    }

    @Test
    void giftCardPayment_rejectsInsufficientFunds() {
        GiftCardPayment gc = new GiftCardPayment(new BigDecimal("5.00"));
        assertThrows(IllegalStateException.class, () -> gc.pay("ORD-11", new BigDecimal("5.01")));
    }
}