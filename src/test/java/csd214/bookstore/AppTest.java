package csd214.bookstore;

import csd214.bookstore.pojos.*;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import java.io.ByteArrayInputStream;
import java.io.InputStream;
import static org.junit.jupiter.api.Assertions.*;

class AppTest {
    private final InputStream originalSystemIn = System.in;

    @AfterEach
    void tearDown() {
        System.setIn(originalSystemIn);
    }

    @Test
    void testAppFlow_AddAndEditBook() {
        // 1. Build the Clean Script
        StringBuilder script = new StringBuilder();

        // --- ADD BOOK ---
        script.append("1\n");             // Main Menu: Add Items
        script.append("1\n");             // Add Menu: Add Book
        script.append("Dune\n");          // Title
        script.append("Frank Herbert\n"); // Author
        script.append("10\n");            // Copies
        script.append("25.00\n");         // Price
        script.append("99\n");            // Exit Add Menu

        // --- EDIT BOOK ---
        script.append("2\n");             // Main Menu: Edit Items
        script.append("0\n");             // Select Index 0
        script.append("Dune Messiah\n");  // Change Title
        script.append("\n");              // Price: Keep
        script.append("\n");              // Copies: Keep
        script.append("\n");              // Author: Keep

        // --- QUIT ---
        script.append("99\n");            // Quit

        // 2. Inject
        System.setIn(new ByteArrayInputStream(script.toString().getBytes()));

        // 3. Run
        App app = new App() {
            @Override
            public void populate() { /* clean start */ }
        };
        app.run();

        // 4. Verify
        Book expected = new Book("Frank Herbert", "Dune Messiah", 25.00, 10);
        SaleableItem result = app.findItem(expected);

        assertNotNull(result);
        assertEquals("Dune Messiah", ((Book)result).getTitle());
    }

    @Test
    void testAppFlow_AddPen() {
        // 1. Build the Clean Script
        StringBuilder script = new StringBuilder();

        // --- ADD PEN ---
        script.append("1\n");             // Main Menu: Add Items
        script.append("6\n");             // Add Menu: Add Pen
        script.append("Copic\n");         // Brand
        script.append("5.99\n");          // Price
        script.append("30\n");            // Quantity
        script.append("Yellow\n");         // Color
        script.append("99\n");            // Exit Add Menu
        script.append("99\n");            // Quit

        // 2. Inject
        System.setIn(new ByteArrayInputStream(script.toString().getBytes()));

        // 3. Run
        App app = new App() {
            @Override
            public void populate() { /* clean start */ }
        };
        app.run();

        // 4. Verify
        Pen expected = new Pen("Copic", 5.99, 30, "Yellow");
        SaleableItem result = app.findItem(expected);

        assertNotNull(result,"Couldn't find expected pen");
        assertEquals("Copic", ((Pen)result).getBrand());
    }

    @Test
    void testAppFlow_AddNotebook() {
        // 1. Build the Clean Script
        StringBuilder script = new StringBuilder();

        // --- ADD PEN ---
        script.append("1\n");             // Main Menu: Add Items
        script.append("5\n");             // Add Menu: Add Notebook
        script.append("Paperage\n");         // Brand
        script.append("5.49\n");          // Price
        script.append("10\n");            // Quantity
        script.append("40\n");         // Color
        script.append("99\n");            // Exit Add Menu
        script.append("99\n");            // Quit

        // 2. Inject
        System.setIn(new ByteArrayInputStream(script.toString().getBytes()));

        // 3. Run
        App app = new App() {
            @Override
            public void populate() { /* clean start */ }
        };
        app.run();

        // 4. Verify
        Notebook expected = new Notebook("Paperage", 5.49, 10, 40);
        SaleableItem result = app.findItem(expected);

        assertNotNull(result,"Couldn't find expected notebook");
        assertEquals("Paperage", ((Notebook)result).getBrand());
    }

    @Test
    void testAppFlow_AddVinyl() {
        // 1. Build the Clean Script
        StringBuilder script = new StringBuilder();

        // --- ADD PEN ---
        script.append("1\n");                       // Main Menu: Add Items
        script.append("7\n");                       // Add Menu: Add Vinyl
        script.append("Healing\n");                 // Title
        script.append("In Love With a Ghost\n");    // Artist
        script.append("Chiptune\n");                // Genre
        script.append("2016\n");                    // Year
        script.append("109.50\n");                  // Price
        script.append("Light Yellow\n");            // DiscColor
        script.append("23\n");                      // Copies
        script.append("99\n");                      // Exit Add Menu
        script.append("5\n");                       // List Menu
        script.append("8\n");                       // List Vinyl
        script.append("99\n");                      // Exit List Menu
        script.append("99\n");                      // Quit

        // 2. Inject
        System.setIn(new ByteArrayInputStream(script.toString().getBytes()));

        // 3. Run
        App app = new App() {
            @Override
            public void populate() { /* clean start */ }
        };
        app.run();

        // 4. Verify
        Vinyl expected = new Vinyl("Healing", "In Love With a Ghost", "Chiptune", 2016, 109.50, "Light Yellow", 23);
        SaleableItem result = app.findItem(expected);

        assertNotNull(result,"Couldn't find expected vinyl");
        assertEquals("Healing", ((Vinyl)result).getTitle());
    }

    @Test
    void testAppFlow_AddDigitalMusic() {
        // 1. Build the Clean Script
        StringBuilder script = new StringBuilder();

        // --- ADD PEN ---
        script.append("1\n");                                                   // Main Menu: Add Items
        script.append("8\n");                                                   // Add Menu: Add Vinyl
        script.append("Yanni Live At The Acropolis\n");                         // Title
        script.append("Yanni\n");                                               // Artist
        script.append("Contemporary Instrumental\n");                           // Genre
        script.append("1994\n");                                                // Year
        script.append("160.89\n");                                              // Price
        script.append("local-library.xwz/files/yanni/sbDFc2PpIbRH2u3E\n");      // Link
        script.append("99\n");                                                  // Exit Add Menu
        script.append("5\n");                                                   // List Menu
        script.append("9\n");                                                   // List Digital Music
        script.append("99\n");                                                  // Exit List Menu
        script.append("99\n");                                                  // Quit

        // 2. Inject
        System.setIn(new ByteArrayInputStream(script.toString().getBytes()));

        // 3. Run
        App app = new App() {
            @Override
            public void populate() { /* clean start */ }
        };
        app.run();

        // 4. Verify
        DigitalMusic expected = new DigitalMusic("Yanni Live At The Acropolis", "Yanni", "Contemporary Instrumental", 1994, 160.89, "local-library.xwz/files/yanni/sbDFc2PpIbRH2u3E");
        SaleableItem result = app.findItem(expected);

        assertNotNull(result,"Couldn't find expected vinyl");
        assertEquals("Yanni Live At The Acropolis", ((DigitalMusic)result).getTitle());
    }


}