WHAT THIS PROGRAM SOLVES: This app is a very simple inventory management system for businesses that hold physical stock in warehouses or storage facilities. This program covers the basic functions of stock management such as being able to add, remove, check, and display inventory items when running the application in terminal. While many businesses pay hefty subscription fees to run CRM and other business tools or waste resources in maintaining Excel spreadsheets which have to be edited cell by cell, this system allows business to seamlessly keep track of or edit their inventory records.

STRUCTURE OF PROGRAM: This program involves 5 main classes: Item, Inventory, Main, and InventoryTest. 
Supplier.java: This class stores supplier name and email as details to be referenced to in other classes.
Item.java: The item class defines individual entries in the inventory, it stores a name, quantity, and price. Item.java also has get methods and a set method which help check and alter values in Inventory class.
Inventory.java: Manages the collection of items by storing them in an ArrayList called stock. This class holds the core logic where most of the work is done through methods, from addItem, removeItem, checkItem, and displayAll. A private method 'locate' helps check items in the ArrayList.
Main.java: Runs the main program, allowing users to interact with the app's functions through Java's built in Scanner class using their keyboard. This class creates an Inventory object (buildInv) which stores all Item records created.
InventoryTest: Holds the JUnit, Test cases file.


HOW TO RUN THIS APPLICATION: 
- Ensure a Java Development Kit (JDK) is installed on your machine.
- Download and extract the project folder from the zip file.
- Open a terminal and navigate into the folder containing Item.java, Inventory.java, and Main.java.
- Compile all files with: javac *.java
- Run the program with: java Main
- Follow the menu to add, remove, check, or display inventory items.
- Choose option 5 from the menu to exit the program

*Some of this code was written using the help of AI as topics were unclear or not covered in depth.
