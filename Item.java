//Blueprint for individual entries in the wider inventory record, holds name, quantity, and price
class Item {
    private String name; // Items name
    private int quantity; // Items quantity
    private double price; // Items price
    private Supplier supplier; // Items supplier
    // Constructor: runs when a new item is built, e.g. new Item(apple, 5, 2.50)
    // Sets fields using information received from user input (from Main)

    public Item(String name, int quantity, double price, Supplier supplier) {
        this.name = name;
        this.quantity = quantity;
        this.price = price;
        this.supplier = supplier;
    }

    // Get method to retrieve specfic data
    // Retrieves supplier
    public Supplier getSupplier() {
        return supplier;
    }

    // Retrieves name
    public String getName() {
        return name;
    }

    // Retrives quantity
    public int getQuantity() {
        return quantity;
    }

    // Retrieves price
    public double getPrice() {
        return price;
    }

    // Setter is used to alter item quantity (to add or subtract stock)
    public void setQuantity(int i) {
        this.quantity = i;
    }

    // Overrides the default returning value which is a unique address/code, enables
    // the returning address to be changed
    // Returns a readable name for each item being checked
    @Override
    public String toString() {
        return " Item: " + name + " | Quantity: " + quantity + " | Price: $" + price + " | " + supplier;
    }
}
