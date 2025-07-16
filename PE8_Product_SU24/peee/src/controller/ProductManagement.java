package controller;

import model.Product;
import model.ProductList;
import view.Menu;
import view.ProductView;

import java.util.Comparator;
import java.util.Date;
import java.util.List;

public class ProductManagement extends Menu<String> {
    ProductList proList;
    ProductView view;

    static String[] options = {
            "Display all products",
            "Display all promoted products",
            "Search products",
            "Sort products by price descending",
            "Statistics by category",
            "Exit"
    };

    public ProductManagement() {
        super("Product Management Menu", options);
        proList = new ProductList();
        view = new ProductView();
    }

    @Override
    public void execute(int n) {
        switch (n) {
            case 1 -> view.displayList(proList, Comparator.naturalOrder(), "All Products");
            case 2 -> {
                List<Product> promo = proList.search(p -> p.getDiscount() > 0);
                view.displayList(promo, Comparator.naturalOrder(), "Promotional Products");
            }
            case 3 -> searchMenu();
            case 4 -> view.displayList(proList, (p1, p2) -> Double.compare(p2.getPrice(), p1.getPrice())
                        , "Sorted by Price Descending");
            case 5 -> proList.displayProductByCategory();
            case 6 ->{
                view.showMessage("Exiting...");
                System.exit(0);
            }
        }
    }

    private void searchMenu() {
        String[] searchOptions = {
                "Search by Category",
                "Search by Production Date",
                "Search by Price > X",
        };

        Menu search = new Menu("Search Menu", searchOptions) {
            @Override
            public void execute(int n) {
                List<Product> result;
                switch (n) {
                    case 1 ->{
                        String category = view.inputCategory();
                        result = proList.search(p -> p.getCategory().equalsIgnoreCase(category));
                        view.displayList(result, Comparator.naturalOrder(), "Search Result: Category = " + category);
                    }
                    case 2 -> {
                        Date d = view.inputProductionDate();
                        result = proList.search(p -> p.getProductDate().equals(d));
                        view.displayList(result, Comparator.naturalOrder(), "Search Result: Date = " + view.formatDate(d));
                    }
                    case 3 -> {
                        double price = view.inputPrice();
                        result = proList.search(p -> p.getPrice() > price);
                        view.displayList(result, Comparator.naturalOrder(), "Search Result: Price > " + price);
                    }
                    default -> System.out.println("Go Main Menu");
                }
            }
        };
        search.run();
    }

    public static void main(String[] args) {
        new ProductManagement().run();
    }
}
