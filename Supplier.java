//Blueprint for supplier detail entries that feeds other classes like Item
public class Supplier {
    private String supplierName;
    private String supplierEmail;

    //Constructor that holds fields for when a new supplier object is made
    public Supplier(String supplierName, String supplierEmail) {
        this.supplierName = supplierName;
        this.supplierEmail = supplierEmail;
    }

    //Get methods that allow other classes to access supplier name and email as they cannot access private fields at the top
    public String getSupplierName() {
        return supplierName;
    }

    public String getSupplierEmail() {
        return supplierEmail;
    }

    //Override translates the built in Java address for supplierName and supplierEmail to String to make it readable
    @Override
    public String toString() {
        return "Supplier Name: " + supplierName + " | Supplier Email: " + supplierEmail; 
    }
}
