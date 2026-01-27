package csd214.bookstore.pojos;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;

public class VinylTest {
    @Test
    void testEquality() {
        // Arrange
        Vinyl v1 = new Vinyl("How To Be A Human Being", "Glass Animals", "Psychedelic Pop",2016,
                60.00, "Orange", 70);
        Vinyl v2 = new Vinyl("How To Be A Human Being", "Glass Animals", "Psychedelic Pop",2016,
                60.00, "Orange", 70);
        Vinyl v3 = new Vinyl("Os Afro-sambas", "Baden Powell & Vinicius de Moraes", "MPB",1966,
                135.00, "Black", 10);
        // Act & Assert
        assertEquals(v1, v2, "Vinyls with same state should be equal");
        assertEquals(v1.hashCode(), v2.hashCode(), "HashCodes must match");
        assertNotEquals(v1, v3, "Different color vinyls should not be equal");
    }

    @Test
    void testConstructor(){
        Vinyl v = new Vinyl("K-12", "Melanie Martinez", "Pop",2019,
                45.00, "Light Pink", 30);
        assertEquals("K-12", v.getTitle(), "Vinyl`s brand was not inputted correctly");
        assertEquals("Melanie Martinez", v.getArtist(), "Vinyl`s price was not inputted correctly");
        assertEquals("Pop", v.getGenre(), "Vinyl`s quantity was not inputted correctly");
        assertEquals(2019, v.getYear(), "Vinyl`s color was not inputted correctly");
        assertEquals(45.00, v.getPrice(), "Vinyl`s color was not inputted correctly");
        assertEquals("Light Pink", v.getDiscColor(), "Vinyl`s color was not inputted correctly");
        assertEquals(30, v.getCopies(), "Vinyl`s color was not inputted correctly");
    }

    @Test
    void testSellItem() {
        Vinyl v = new Vinyl("From Rotting Fantasylands", "Nero's Day At Disneyland", "Electro-Industrial",2009,
                275.00, "Multicolored", 3);
        v.sellItem();
        assertEquals(2, v.getCopies(),"Vinyl was not sold correctly");
        v.sellItem();
        assertEquals(1, v.getCopies(),"Vinyl was not sold correctly");
    }
    
}
