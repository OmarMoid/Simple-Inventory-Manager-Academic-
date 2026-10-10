import java.util.Scanner;

//Main goes through Inventory's public methods
//Opens a menu and takes user input
public class Main {
    public static void main(String[] args) {
        Inventory buildInv = new Inventory(); // The invenotry the progtam uses
        buildInv.loadFromFile();
        Scanner keyboard = new Scanner(System.in); // Checks keyboard inputs (reads text types by user)
        boolean menuOpen = true; // Loop opens menu, keeps running until menu is closed

        while(menuOpen) { // Shows the menu until user exits
            System.out.println("MENU");
            System.out.println(" ");
            System.out.println("1. Add Item");
            System.out.println("2. Remove Item");
            System.out.println("3. Check Item");
            System.out.println("4. Display All");
            System.out.println("5. Exit");
            System.out.println("Choose an Option");

            // Converts keyboard input from text into integer so option can be picked 1-5
            int optionPick = Integer.parseInt(keyboard.nextLine());

            // Sets the 3 values required (name, quantity, price) for addItem() and appoints
            // them to the method
            if(optionPick == 1) {
                System.out.println("Name of Item: ");
                String nameToAdd = keyboard.nextLine();
                System.out.println("Quantity to Add");
                int quantityToAdd = Integer.parseInt(keyboard.nextLine());
                System.out.println("Price of Item: ");
                double priceToAdd = Double.parseDouble(keyboard.nextLine());
                System.out.println("Supplier Name: ");
                String supplierName = keyboard.nextLine();
                System.out.println("Supplier Email: ");
                String supplierEmail = keyboard.nextLine();
                Supplier supplierToAdd = new Supplier(supplierName, supplierEmail);
                buildInv.addItem(nameToAdd, quantityToAdd, priceToAdd, supplierToAdd);
            }

            // Gathers the name and quantity for removeItem() method, then appoints
            else if(optionPick == 2) {
                System.out.println("Name of Item: ");
                String nameToRemove = keyboard.nextLine();
                System.out.println("Quantity to Remove: ");
                int quantityToRemove = Integer.parseInt(keyboard.nextLine());
                buildInv.removeItem(nameToRemove, quantityToRemove);
            }

            // Lookup specfic stock details
            else if(optionPick == 3) {
                System.out.println("Name of Item: ");
                String nameToCheck = keyboard.nextLine();
                buildInv.checkItem(nameToCheck);
            }

            // Displays all items in stock using displayAll() method
            else if(optionPick == 4) {
                buildInv.displayAll();
            }

            // Ends the while loop
            else if(optionPick == 5) {
                buildInv.saveToFile();
                menuOpen = false; // Stops while loop
                System.out.println("Closing");
            }
        }
        // Stops taking in user input
        keyboard.close(); // Stops taking user input from class Scanner

    }
}
