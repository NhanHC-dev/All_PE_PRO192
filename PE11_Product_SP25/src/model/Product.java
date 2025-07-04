package model;

import java.text.ParseException;
import java.util.Date;
import java.util.concurrent.TimeUnit;
import java.text.SimpleDateFormat;

public class Product {
    private String productID;
    private String name;
    private String category;
    private int quantity;
    private double price;
    private Date dateAdded;
    private SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");

    public Product(String productID, String name, String category, int quantity, double price, Date dateAdded) {
        this.productID = productID;
        this.name = name;
        this.category = category;
        this.quantity = quantity;
        this.price = price;
        this.dateAdded = dateAdded;
    }

    public String getProductID() {
        return productID;
    }

    public void setProductID(String productID) {
        this.productID = productID;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public Date getDateAdded() {
        return dateAdded;
    }

    public void setDateAdded(String dateStr) throws ParseException {
        sdf.setLenient(false);
        Date d = sdf.parse(dateStr);
        if (d.after(new Date())) throw new IllegalArgumentException("Date cannot be in the future");
        this.dateAdded = d;
    }
    public long getProductAge() {
        long diff = new Date().getTime() - dateAdded.getTime();
        return TimeUnit.DAYS.convert(diff, TimeUnit.MILLISECONDS);
    }


    @Override
    public String toString() {
        return String.format("%-8s | %-15s | %-12s | %4d | %8.2f | %s", productID, name, category, quantity, price, sdf.format(dateAdded));
    }
}
