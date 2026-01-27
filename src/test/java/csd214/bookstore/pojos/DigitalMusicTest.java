package csd214.bookstore.pojos;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;

public class DigitalMusicTest {
    @Test
    void testEquality() {
        // Arrange
        DigitalMusic dm1 = new DigitalMusic("How To Be A Human Being", "Glass Animals", "Psychedelic Pop",2016,
                60.00, "local-library.xwz/files/glass-animals/access-g548FkN9OEyKFLVv");
        DigitalMusic dm2 = new DigitalMusic("How To Be A Human Being", "Glass Animals", "Psychedelic Pop",2016,
                60.00, "local-library.xwz/files/glass-animals/access-g548FkN9OEyKFLVv");
        DigitalMusic dm3 = new DigitalMusic("Os Afro-sambas", "Baden Powell & Vinicius de Moraes", "MPB",1966,
                135.00, "local-library.xwz/files/baden-powell-and-vinicius-de-moraes/access-WZaZcqqcIywXmKKb");
        // Act & Assert
        assertEquals(dm1, dm2, "DigitalMusics with same state should be equal");
        assertEquals(dm1.hashCode(), dm2.hashCode(), "HashCodes must match");
        assertNotEquals(dm1, dm3, "Different color digital musics should not be equal");
    }

    @Test
    void testConstructor(){
        DigitalMusic dm = new DigitalMusic("K-12", "Melanie Martinez", "Pop",2019,
                45.00, "local-library.xwz/files/melanie-martinez/RY4nbPRykexP0uR1");
        assertEquals("K-12", dm.getTitle(), "DigitalMusic`s brand was not inputted correctly");
        assertEquals("Melanie Martinez", dm.getArtist(), "DigitalMusic`s price was not inputted correctly");
        assertEquals("Pop", dm.getGenre(), "DigitalMusic`s quantity was not inputted correctly");
        assertEquals(2019, dm.getYear(), "DigitalMusic`s color was not inputted correctly");
        assertEquals(45.00, dm.getPrice(), "DigitalMusic`s color was not inputted correctly");
        assertEquals("local-library.xwz/files/melanie-martinez/RY4nbPRykexP0uR1", dm.getLink(), "DigitalMusic`s color was not inputted correctly");
    }

    // ---------------- DIGITAL MUSIC does not have copies ------------------------
//    @Test
//    void testSellItem() {
//        DigitalMusic dm = new DigitalMusic("From Rotting Fantasylands", "Nero's Day At Disneyland", "Electro-Industrial",2009,
//                275.00, "Multicolored", 3);
//        dm.sellItem();
//        assertEquals(2, dm.getCopies(),"DigitalMusic was not sold correctly");
//        dm.sellItem();
//        assertEquals(1, dm.getCopies(),"DigitalMusic was not sold correctly");
//    }
}
