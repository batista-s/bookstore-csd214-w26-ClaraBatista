package csd214.bookstore.pojos;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;

public class NotebookTest {
    @Test
    void testEquality() {
        // Arrange
        Notebook n1 = new Notebook("Tilibra", 0.99, 30, 200);
        Notebook n2 = new Notebook("Tilibra", 0.99, 50, 200);
        Notebook n3 = new Notebook("Apica", 1.99, 30, 180);
        // Act & Assert
        assertEquals(n1, n2, "Notebooks with same state should be equal");
        assertEquals(n1.hashCode(), n2.hashCode(), "HashCodes must match");
        assertNotEquals(n1, n3, "Different color notebooks should not be equal");
    }

    @Test
    void testConstructor(){
        Notebook n = new Notebook("Midori", 4.67, 50, 50);
        assertEquals("Midori", n.getBrand(), "Notebook`s brand was not inputted correctly");
        assertEquals(4.67, n.getPrice(), "Notebook`s price was not inputted correctly");
        assertEquals(50, n.getQuantity(), "Notebook`s quantity was not inputted correctly");
        assertEquals(50, n.getPageCount(), "Notebook`s page count was not inputted correctly");
    }

    @Test
    void testSellItem() {
        Notebook n = new Notebook("Moleskine", 2.39, 10, 80);
        n.sellItem();
        assertEquals(9, n.getQuantity(),"Notebook was not sold correctly");
        n.sellItem();
        assertEquals(8, n.getQuantity(),"Notebook was not sold correctly");
    }
}
