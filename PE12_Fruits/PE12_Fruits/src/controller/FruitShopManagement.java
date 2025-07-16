package controller;

import model.Fruit;
import model.FruitList;
import view.*;

import java.time.LocalDate;

public class FruitShopManagement extends Menu<String>{
    private FruitList list;
    public FruitShopManagement() {
        super("WELCOME TO FRUIT SHOP", new String[] {
                "Display all fruits",
                "Add new fruit",
                "Sort fruits",
                "Calculate total price after discount",
                "Delete fruits with quantity < 2",
                "Exit"
        });
        list = new FruitList();
    }

    @Override
    public void execute(int choice) {
        switch(choice) {
            case 1 -> list.listAllFruits();
            case 2 -> addNewFruit();
            case 3 -> sortFruits();
            case 4 -> System.out.printf("Total price after discount: %.2f\n",list.calculateTotal());
            case 5 -> list.deleteFruit(f -> f.getQuantity() < 2);
            case 6 -> this.stop();
            default -> System.out.println("Invalid choice.");
        }
    }

    private void addNewFruit() {
        String name;
        while (true) {
            name = Validation.getString("Enter fruit name: ");
            if (!isFruitNameExists(name.trim())) break;
            System.out.println("Fruit name already exists! Try again.");
        }
        double price = Validation.getDouble("Enter price: ", 0.1f, 99999999);
        int qty = Validation.getInt("Enter quantity: ", 1, 10000);
        LocalDate date = Validation.getDate("Enter packaging date ");
        boolean added = list.addFruit(new Fruit(name, price, qty, date));
        System.out.println(added ? "Fruit added." : "Failed to add fruit.");
    }
    private boolean isFruitNameExists(String name) {
        if (list == null || list.isEmpty()) {
            return false;
        }
        for (Fruit fruit : list) {
            if (fruit.getName() != null && fruit.getName().equalsIgnoreCase(name)) {
                return true;
            }
        }
        return false;
    }

    private void sortFruits() {
        list.sortByName();
        System.out.println("Sorted by name.");  
    }
        
        public static void main(String[] args) {
        new FruitShopManagement().run();
    }
}
