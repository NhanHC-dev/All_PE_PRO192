
package model;

import java.io.BufferedReader;
import java.io.FileReader;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.function.Predicate;

public class FruitList extends ArrayList<Fruit>{

    public FruitList() {
        super();
        loadFromFile();
    }
    
    public void listAllFruits() {
        listAllFruits(this, Comparator.comparing(Fruit::getPrice));
    }
    public void listAllFruits(Comparator<Fruit> comparator ) {
        listAllFruits(this, comparator);
    };
    public void listAllFruits(ArrayList<Fruit> list, Comparator<Fruit> comparator) {
        int total = list.size();
        if (total <= 0) {
            System.out.println("Sorry. Nothing to print!");
            return;
        }
        Collections.sort(list,comparator );
        System.out.println("List of all fruit:");
        System.out.println("--------------------------------");
        list.forEach(System.out::println);
        System.out.println("--------------------------------");
        System.out.println("Total: " + total + " fruit");
    }
    


    public boolean addFruit(Fruit fruit) {
        for (Fruit f : this) {
            if (f.getName().equalsIgnoreCase(fruit.getName())) return false;
        }
        return this.add(fruit);
    }

    public void loadFromFile() {
        try (BufferedReader reader = new BufferedReader(new FileReader("FruitsList.txt"))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] parts = line.split(",");
                if (parts.length != 4) continue;
                String name = parts[0].trim();
                double price = Double.parseDouble(parts[1].trim());
                int quantity = Integer.parseInt(parts[2].trim());
                LocalDate date = LocalDate.parse(parts[3].trim());
                add(new Fruit(name, price, quantity, date));
            }
        } catch (Exception e) {
            System.out.println("Error loading file: " + e.getMessage());
        }
    }
    
    public void sortByName(){
        listAllFruits(Comparator.comparing(Fruit::getName));
    }
        
    public void deleteFruit(Predicate<Fruit> predicate) {
        boolean removed = this.removeIf(predicate);
        if (removed) System.out.println("Deleted fruits with quantity < 2");
        else System.out.println("No fruit to delete.");
    }

    public double calculateTotal() {
        double total = 0;
        for (Fruit f : this) {
            double discount = 0;
            if (f.getQuantity() >= 100) discount = 0.1;
            else if (f.getQuantity() >= 50) discount = 0.05;
            total += f.getPrice() * f.getQuantity() * (1 - discount);
        }
        return total;
    }
}
