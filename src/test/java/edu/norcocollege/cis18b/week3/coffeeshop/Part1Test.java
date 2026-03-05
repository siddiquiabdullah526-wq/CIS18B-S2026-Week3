package edu.norco.cis18b.coffeeshop;

import org.junit.jupiter.api.Test;

import java.math.BigDecimal;

import static org.junit.jupiter.api.Assertions.*;

import edu.norco.cis18b.coffeeshop.grading.Points;
import edu.norco.cis18b.coffeeshop.grading.ScoreExtension;

@Points(2)
@ExtendWith(ScoreExtension.class)
public class Part1Test {

    @Test
    void menuItem_validConstruction() {
        MenuItem item = new MenuItem("LATTE-M", "Latte", new BigDecimal("4.50"));
        assertEquals("LATTE-M", item.getSku());
        assertEquals("Latte", item.getName());
        assertEquals(new BigDecimal("4.50"), item.getPrice());
        assertFalse(item.toString().isBlank());
    }

    @Test
    void menuItem_rejectsInvalidSkuName() {
        assertThrows(IllegalArgumentException.class, () -> new MenuItem(null, "Latte", new BigDecimal("1.00")));
        assertThrows(IllegalArgumentException.class, () -> new MenuItem("  ", "Latte", new BigDecimal("1.00")));
        assertThrows(IllegalArgumentException.class, () -> new MenuItem("LATTE", null, new BigDecimal("1.00")));
        assertThrows(IllegalArgumentException.class, () -> new MenuItem("LATTE", "   ", new BigDecimal("1.00")));
    }

    @Test
    void menuItem_rejectsInvalidPrice() {
        assertThrows(IllegalArgumentException.class, () -> new MenuItem("LATTE", "Latte", null));
        assertThrows(IllegalArgumentException.class, () -> new MenuItem("LATTE", "Latte", new BigDecimal("-0.01")));
        // allow zero
        assertDoesNotThrow(() -> new MenuItem("WATER", "Water", BigDecimal.ZERO));
    }

    @Test
    void order_addItemsAndTotal() {
        Order order = new Order("ORD-1");
        order.addItem(new MenuItem("A", "A", new BigDecimal("1.10")));
        order.addItem(new MenuItem("B", "B", new BigDecimal("2.20")));
        assertEquals(new BigDecimal("3.30"), order.total());
        assertEquals(2, order.getItems().size());
    }

    @Test
    void order_rejectsInvalidInputs() {
        assertThrows(IllegalArgumentException.class, () -> new Order(null));
        assertThrows(IllegalArgumentException.class, () -> new Order("   "));
        Order order = new Order("ORD-OK");
        assertThrows(IllegalArgumentException.class, () -> order.addItem(null));
    }

    @Test
    void order_itemsListIsUnmodifiable() {
        Order order = new Order("ORD-2");
        order.addItem(new MenuItem("A", "A", new BigDecimal("1.00")));
        assertThrows(UnsupportedOperationException.class, () -> order.getItems().add(new MenuItem("B", "B", new BigDecimal("1.00"))));
    }
}