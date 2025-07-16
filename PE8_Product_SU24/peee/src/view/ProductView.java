package view;

import model.Product;

import java.text.SimpleDateFormat;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.List;

public class ProductView {
    private final Validation val = new Validation();

    public String inputCategory() {
        return val.getString("Enter category: ");
    }

    public Date inputProductionDate() {
        return val.inputDate("Enter production date (dd/MM/yyyy): ");
    }

    public double inputPrice() {
        return val.getDouble("Enter price: ");
    }

    public void displayList(List<Product> list) {
        displayList(list, Comparator.naturalOrder(), "All Products");
    }

    public void displayList(List<Product> list, Comparator<Product> comparator) {
        displayList(list, comparator, "Product List");
    }

    public void displayList(List<Product> list, Comparator<Product> comparator, String title) {
        if (list == null || list.isEmpty()) {
            System.out.println("No products to display.");
            return;
        }

        if (comparator != null) {
            Collections.sort(list, comparator);
        }

        System.out.println("\n--- " + title + " ---");
        System.out.println("-----------------------------------------------------------");
        for (Product p : list) {
            System.out.println(p);
        }
        System.out.println("-----------------------------------------------------------");
        System.out.println("Total: " + list.size() + " products.\n");
    }

    public void showMessage(String msg) {
        System.out.println(msg);
    }

    public String formatDate(Date d) {
        return new SimpleDateFormat("dd/MM/yyyy").format(d);
    }
}
