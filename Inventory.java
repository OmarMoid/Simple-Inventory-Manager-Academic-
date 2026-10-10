import java.io.BufferedReader; // reads text efficiently, one line at a time
import java.io.FileNotFoundException; // thrown when the file doesn't exist
import java.io.FileReader; // opens a file for reading
import java.io.FileWriter; // opens a file for writing
import java.io.IOException; // general file problem (read/write failed)
import java.io.PrintWriter; // lets us use println() to write to a file
import java.util.ArrayList;

//Manages the full collection of stock entries
//This is the class Main talks to directly as this controls all access to the arrays holding the individual Item objects
public class Inventory {
    // ArrayList allows the entries to change (add/remove items) as needed
    // Private allows nothing outside the class to edit
    private ArrayList<Item> stock = new ArrayList<Item>();

    // No arguments constrcutor builds an empty ArrayList when new Inventory is made
    public Inventory() {
    }

    // Searches for a name to see if its in stock
    private Item locate(String name) {
        for(Item object : stock) {
            if(object.getName().equalsIgnoreCase(name)) {
                return object;
            }
        }
        return null;
    }

    // Checks for existing stock to add to or creates a new entry
    public void addItem(String name, int quantity, double price, Supplier supplier) {
        Item match = locate(name);
        // ^ Remember operates from left to right, locate(name) checks first

        if(match != null) {
            // setQuantity() updates the quantity value and appoints it to the variable
            match.setQuantity(match.getQuantity() + quantity);
            System.out.println("Updated Stock: " + name);
            System.out.println("New Quantity: " + match.getQuantity());
        }

        else {
            stock.add(new Item(name, quantity, price, supplier));
            System.out.println("New Stock Entry: " + name);
        }
    }

    // Checks for quantity of items being searched, then removes the amount required
    // if conditions meet
    // Those conditions are that (first if and else if statements) Item being
    // searched exists and the quantity wanted to remove is less than or equal to
    // quantity of stock available
    public boolean removeItem(String name, int quantity) {
        Item match = locate(name);

        if(match == null) {
            System.out.println("No entry found.");
            return false;
        }

        else if(quantity > match.getQuantity()) {
            System.out.println("Not enough stock to remove.");
            return false;
        }

        match.setQuantity(match.getQuantity() - quantity);

        if(match.getQuantity() == 0) {
            stock.remove(match);
            System.out.println("Stock Quantity: " + match.getQuantity());
            System.out.println(name + " removed from record.");
        }

        else {
            System.out.println("Removed " + quantity + " of Stock: " + name);
            System.out.println("Stock Quantity Remaining: " + match.getQuantity());
        }
        return true;
    }

    // Prints specific entry details (name, quantity, price, supplier), or says not
    // found
    public void checkItem(String name) {
        Item match = locate(name);
        if(match == null) {
            System.out.println(name + " is not in inventory.");
        }

        else {
            System.out.println(match.toString());
        }
    }

    // Lists every entry in stock, or says inventory is empty
    public void displayAll() {
        if(stock.size() == 0) {
            System.out.println("Inventory is Empty");
            return;
        }

        System.out.println("CURRENT STOCK");
        for(Item object : stock) {
            System.out.println(object.toString());
            System.out.println("--------------------------------");
        }
    }

    public void saveToFile() {
        try {
            // opens (or creates) inventory.txt; an existing file is overwritten
            PrintWriter writer = new PrintWriter(new FileWriter("inventory.txt"));

            // visits every Item in the list, one at a time
            for(Item entry : stock) {
                // joins all five fields into one comma-separated line
                String line = entry.getName() + ","
                        + entry.getQuantity() + ","
                        + entry.getPrice() + ","
                        + entry.getSupplier().getSupplierName() + ","
                        + entry.getSupplier().getSupplierEmail();

                // writes the line, then moves to the next line in the file
                writer.println(line);
            }

            // closing flushes buffered text so nothing is lost
            writer.close();
        } 
        catch(IOException e) {
            // runs only if the file cannot be opened or written
            System.out.println("Could not save inventory: " + e.getMessage());
        }
    }

    public void loadFromFile() {
        try {
            // opens inventory.txt for reading; throws FileNotFoundException if it is missing
            BufferedReader reader = new BufferedReader(new FileReader("inventory.txt"));

            // reads the first line; readLine() gives null when the file has no more lines
            String line = reader.readLine();

            while(line != null) {
                // cuts the line at every comma into an array of Strings
                String[] parts = line.split(",");

                // skips any line that does not have exactly five fields
                if(parts.length == 5) {
                    String name = parts[0];

                    // converts the text "25" into the int 25
                    int quantity = Integer.parseInt(parts[1]);

                    // converts the text "49.99" into the double 49.99
                    double price = Double.parseDouble(parts[2]);

                    // builds the Supplier from the last two fields
                    Supplier supplier = new Supplier(parts[3], parts[4]);

                    // builds the Item and adds it to the list
                    stock.add(new Item(name, quantity, price, supplier));
                }

                // reads the next line; without this the loop would never end
                line = reader.readLine();
            }

            reader.close();
        }
        catch(FileNotFoundException e) {
            // first run: no file yet, so the inventory simply starts empty
            System.out.println("No saved inventory found, starting empty.");
        }
        catch(IOException e) {
            // any other reading problem
            System.out.println("Could not load inventory: " + e.getMessage());
        }
    }
}
