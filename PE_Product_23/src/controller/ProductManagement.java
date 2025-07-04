package controller;

import model.Producer;

import utils.Validation;
import utils.exception.ProducerFullException;
import view.Menu;
import java.time.LocalDate;
import java.util.ArrayList;

public final class ProductManagement extends Menu {
    private static String[] options = {
            "List all products",
            "Search product",
            "Add new product",
            "Exit"};
    private Producer producer;

    public ProductManagement() {
    }

    public ProductManagement(String title, String[] options, Producer producer) {
        super(title, options);

        this.producer = producer;

    }

    @Override
    public void execute(int choice) {
        switch (choice) {
            case 1 -> {
                this.producer.listAllProduct();
            }
            case 2 -> {
                this.search();
            }
            case 3 -> {
                try {
                    this.producer.addNewProduct();
                } catch (ProducerFullException ex) {
                    System.err.println("A problem occured: " + ex);
                }
            }
            case 4 -> {
                this.stop();
            }
        }
    }
        private void search() {
            String[] searchOptions = {
                    "Find by productID",
                    "Find by name product",
                    "Find by Unit Price:",
                    "Find by quantity:",
                    "Find by version: ",
                    "Find by Date manufacturing of product",
                    "Return"
            };
            new Menu<String>("Book Searching", searchOptions) {
                @Override
                public void execute(int choice) {
                    switch (choice) {
                        case 1 -> {
                            String productID = Validation.getString("Enter Product ID (st. ID 1): ", "[\\d]{1}");
                            producer.listAllProduct(producer.search(p -> p.getProductID().equalsIgnoreCase(productID)));
//                            ArrayList<Product> searchID = producer.search(product -> product.getProductID().equalsIgnoreCase(productID));
//                            producer.displaySearch(searchID);
                        }

                        case 2 -> {
                            String productName = Validation.getString("Enter Product Name: ");
                            producer.listAllProduct(producer.search(predicate -> predicate.getProductName().toLowerCase().contains(productName.toLowerCase())));
//                            ArrayList<Product> searchName = producer.search(product -> product.getProductName().toLowerCase().contains(productName.toLowerCase()));
//                            producer.displaySearch(searchName);
                        }

                        case 3 -> {
                            double unitPrice = Validation.getFloat("Enter unitPrice: ");
                            producer.listAllProduct(producer.search(predicate -> Double.compare(predicate.getUnitPrice(), unitPrice) == 0));
//                            ArrayList<Product> searchPrice = producer.search(product -> Double.compare(product.getUnitPrice(), unitPrice) == 0);
//                            producer.displaySearch(searchPrice);
                        }

                        case 4 -> {
                            int quantity = Validation.getInt("Enter Year Published: ");
                            producer.listAllProduct(producer.search(predicate -> predicate.getQuantity() == quantity));
//                            ArrayList<Product> searchQuantity = producer.search(product -> product.getQuantity() == quantity);
//                            producer.displaySearch(searchQuantity);
                        }
                        case 5 -> {
                            double version = Validation.getFloat("Enter version: ");
                            producer.listAllProduct(producer.search(predicate -> Double.compare(predicate.getVersion(), version) == 0));
// co the comment dong tren de doi cach code                           ArrayList<Product> searchVersion = producer.search(product -> Double.compare(product.getVersion(), version) == 0);
//                            producer.displaySearch(searchVersion);
                        }
                        case 6 -> {
                            LocalDate productMaDate = Validation.inputDate("Enter product Manufacturing Date format yyyy-mm-dd: ");
                            producer.listAllProduct(producer.search(predicate -> predicate.getProductManufacturingDate().equals(productMaDate)));
//                            ArrayList<Product> searchProductMaDate = producer.search(predicate -> predicate.getProductManufacturingDate().equals(productMaDate));
//                            producer.displaySearch(searchProductMaDate);
                        }

                        default -> {
                            this.stop();
                        }
                    }
                }
            }.run();
        }

    public static void main(String[] args) {
        new ProductManagement("Product Management", options, new Producer()).run();
    }
}