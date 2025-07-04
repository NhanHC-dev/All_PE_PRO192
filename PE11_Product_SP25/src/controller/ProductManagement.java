package controller;

import model.Product;
import model.ProductList;
import view.View;
import view.Validation;

import java.util.Date;

public class ProductManagement extends View<String> {
    private ProductList productList;

    public ProductManagement() {
        super("====== Warehouse Product Management ======", new String[]{
                "Display all products",
                "Sort products by quantity",
                "Search product by name",
                "Search product by category",
                "Add new product",
                "Delete products over 2 years",
                "Exit"
        });
        this.productList = new ProductList();
    }
    @Override
    public void execute(int choice) {
        switch (choice) {
            case 1 -> printAll();
            case 2 -> productList.sortByQuantity();
            case 3 -> searchByName();
            case 4 -> searchByCategory();
            case 5 -> addProduct();
            case 6 -> delete();
            case 7 -> {
                System.out.println("Exiting application...");
                stop();
            }
            default -> System.out.println("Invalid choice. Please try again.");
        }
    }
    private void searchByName() {
        String name = Validation.getString("Enter name to search: ");
        productList.searchBy(n -> n.getName().toLowerCase().contains(name.toLowerCase()));
    }

    private void searchByCategory() {
        String category = Validation.getString("Enter category to search: ");
        productList.searchBy(n -> n.getCategory().toLowerCase().contains(category.toLowerCase()));
    }
    private void printAll(){
        productList.listAllProducts();
    }
    private void addProduct() {
        try {
            String id = Validation.getString("Enter Product ID: ");
            String name = Validation.getString("Enter Name: ");
            String category = Validation.getString("Enter Category: ");
            int qty = Validation.getInt("Enter Quantity: ", 0, Integer.MAX_VALUE);
            double price = Validation.getDouble("Enter Price: ");
            Date date = Validation.inputDate("Enter Date Added (dd/MM/yyyy): ");

            productList.addProduct(new Product(id, name, category, qty, price, date));
            System.out.println("Product added successfully!");
        } catch (Exception e) {
            System.out.println("Failed to add product.");
        }
    }

    private void delete() {
        productList.deleteOldProducts();
        System.out.println("Comic books older than 10 years have been deleted.");
    }


    public static void main(String[] args) {
        new ProductManagement().run();
    }
}
