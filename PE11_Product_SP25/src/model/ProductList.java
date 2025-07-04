package model;

import view.Validation;

import java.io.*;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.Date;
import java.util.function.Predicate;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.stream.Collectors;

public class ProductList extends ArrayList<Product> {
    public ProductList() {
        this.readFile("ProductData.txt");
    }

    public void readFile(String filename) {
        try (BufferedReader br = new BufferedReader(new FileReader(filename))) {
            String line;
            while ((line = br.readLine()) != null) {
                try {
                    String[] parts = line.split(",\\s*");
                    if (parts.length < 6) {
                        System.out.println("Invalid line format: " + line);
                        continue;
                    }
                    Date date;
                    try {
                        date = Validation.checkValidDate(parts[5].trim());
                    } catch (ParseException e) {
                        System.out.println("Invalid date format for: " + parts[5].trim() + ". Skipping this entry.");
                        continue;
                    }
                    Product product = new Product(
                        parts[0].trim(),
                        parts[1].trim(),
                        parts[2].trim(),
                        Integer.parseInt(parts[3].trim()),
                        Double.parseDouble(parts[4].trim()),
                        date);
                    this.addProduct(product);
                } catch (Exception e) {
                    Logger.getLogger(ProductList.class.getName()).log(Level.SEVERE, "Error adding list: ", e);
                }
            }
        } catch (IOException e) {
            Logger.getLogger(ProductList.class.getName()).log(Level.SEVERE, "Error reading file: ", e);
        }
    }
    public void addProduct(Product product) {
        this.add(product);
    }
    public void listAllProducts() {
        this.listAllProducts(this, Comparator.comparing(Product::getProductID));
    }

    public void listAllProducts(Comparator<Product> comparator) {
        this.listAllProducts(this, comparator);
    }

    public void listAllProducts(ArrayList<Product> list, Comparator<Product> comparator) {
        int total = list.size();
        if (total <= 0) {
            System.out.println("Sorry. Nothing to print!");
            return;
        }
        list.sort(comparator);
        System.out.println("List of All Product");
        System.out.println("--------------------------------");
        System.out.printf("%-8s | %-15s | %-12s | %4s | %8s | %-10s\n",
                "ID", "Name", "Category", "Qty", "Price", "Date Added");
        list.forEach(System.out::println);

        System.out.println("--------------------------------");
        System.out.println("Total: " + total + " product(s).");
    }
    public void sortByQuantity() {
        this.listAllProducts(Comparator.comparingInt(Product::getQuantity));
         System.out.println("Sorted by quantity.");
    }

    public ArrayList<Product> searchBy(Predicate<Product> predicate) {
        return this.stream()
                .filter(predicate)
                .collect(Collectors.toCollection(ArrayList::new));
    }

    public void deleteOldProducts() {
        boolean removed = this.removeIf(this::isOverTwoYears);
        if (removed) {
            System.out.println("Products older than 2 years deleted.");
        } else {
            System.out.println("No products to delete.");
        }
    }
    private boolean isOverTwoYears(Product p) {
        return p.getProductAge() >= 730;
    }

}
