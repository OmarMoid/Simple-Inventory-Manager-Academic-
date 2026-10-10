import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class InventoryTest {
    private Inventory inventory;
    private Supplier supplier;
    private PrintStream originalOut;
    private ByteArrayOutputStream capturedOut;

    //Fresh objects for every test, and System.out redirected so printed text can be checked
    @BeforeEach
    public void setUp() {
        inventory = new Inventory();
        supplier = new Supplier("Logitech", "sales@logitech.com");
        originalOut = System.out;
        capturedOut = new ByteArrayOutputStream();
        System.setOut(new PrintStream(capturedOut));
    }

    //Puts System.out back to the normal screen output
    @AfterEach
    public void restoreOutput() {
        System.setOut(originalOut);
    }

    //addItem: the same name adds to the quantity instead of creating a second entry
    @Test
    public void addItemExistingNameMergesQuantity() {
        inventory.addItem("Keyboard", 25, 49.99, supplier);
        inventory.addItem("Keyboard", 5, 49.99, supplier);
        inventory.checkItem("Keyboard");
        assertTrue(capturedOut.toString().contains("Quantity: 30"));
    }

    //addItem + locate: matching ignores upper/lower case (edge case)
    @Test
    public void addItemMergesRegardlessOfCase() {
        inventory.addItem("Keyboard", 25, 49.99, supplier);
        inventory.addItem("KEYBOARD", 5, 49.99, supplier);
        inventory.checkItem("keyboard");
        assertTrue(capturedOut.toString().contains("Quantity: 30"));
    }

    //removeItem: removing more than exists is refused and the stock is unchanged (boundary case)
    @Test
    public void removeItemTooMuchIsRefused() {
        inventory.addItem("Mouse", 40, 19.5, supplier);
        assertFalse(inventory.removeItem("Mouse", 41));
        assertTrue(inventory.removeItem("Mouse", 40)); //all 40 are still there
    }

    //checkItem + locate: an unknown name reports it is not in inventory
    @Test
    public void checkItemMissingNameReportsNotFound() {
        inventory.addItem("Keyboard", 25, 49.99, supplier);
        inventory.checkItem("Ghost");
        assertTrue(capturedOut.toString().contains("Ghost is not in inventory."));
    }

    //displayAll: an empty inventory says so (edge case)
    @Test
    public void displayAllEmptyInventory() {
        inventory.displayAll();
        assertTrue(capturedOut.toString().contains("Inventory is Empty"));
    }
}