package model;

import java.io.*;
import java.text.*;
import java.util.*;
import java.util.function.Predicate;

public class ProductList extends ArrayList<Product> {
    public ProductList() {
        loadFromFile("product.txt");
    }

    private void loadFromFile(String filename) {
        SimpleDateFormat df = new SimpleDateFormat("dd/MM/yyyy");
        try (BufferedReader br = new BufferedReader(new FileReader(filename))) {
            String line;
            while ((line = br.readLine()) != null) {
                String[] b = line.split(",");
                if (b.length == 5) {
                    try {
                        String id = b[0].trim();
                        if (!id.matches("[Pp]\\d{3}")) {
                            throw new Exception("Invalid ProductID format. Must be 'Pxxx'");
                        }
                        String name = b[1].trim();
                        String category = b[2].trim();
                        Date prodDate = df.parse(b[3].trim());
                        double price = Double.parseDouble(b[4].trim());
                        this.add(new Product(id.toUpperCase(), name, category, prodDate, price));
                    } catch (Exception e) {
                        System.err.println("Invalid line skipped: " + line);
                        System.err.println("  Reason: " + e.getMessage());
                    }
                } else {
                    System.err.println("Malformed line skipped: " + line);
                }
            }
        } catch (IOException e) {
            System.err.println("Error reading file: " + e.getMessage());
        }
    }


    public ArrayList<Product> search(Predicate<Product> condition) {
        ArrayList<Product> result = new ArrayList<>();
        for (Product p : this) {
            if (condition.test(p)) result.add(p);
        }
        return result;
    }

    public void displayProductByCategory() {
        if (this.isEmpty()) {
            System.out.println("No products available.");
            return;
        }
        ArrayList<String> doneCategories = new ArrayList<>();
        for (Product p : this) {
            String cat = p.getCategory();
            if (!doneCategories.contains(cat)) {
                doneCategories.add(cat);
                System.out.println("\nCategory: " + cat);
                int count = 0;
                for (Product q : this) {
                    if (q.getCategory().equalsIgnoreCase(cat)) {
                        System.out.println(q);
                        count++;
                    }
                }
                System.out.println("Total: " + count + " products.");
            }
        }
    }
}