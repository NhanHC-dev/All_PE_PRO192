
package model;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;


public class Fruit {
    private String name;
    private double price;
    private int quantity;
    private LocalDate packagingDate;

    public Fruit() {
    }

    public Fruit(String name, double price, int quantity, LocalDate packagingDate) {
        this.name = name;
        this.price = price;
        this.quantity = quantity;
        this.packagingDate = packagingDate;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public LocalDate getPackagingDate() {
        return packagingDate;
    }

    public void setPackagingDate(LocalDate packagingDate) {
        this.packagingDate = packagingDate;
    }

    @Override
    public String toString() {
        return String.format("Name: %-12s | Price: %6.2f | Qty: %4d | Date: %s",
                name, price, quantity,
                packagingDate.format(DateTimeFormatter.ofPattern("yyyy-MM-dd")));
    }

//    @Override
//    public String toString() {
//        return "Fruit{" +
//                "name='" + name + '\'' +
//                ", price=" + price +
//                ", quantity=" + quantity +
//                ", packagingDate=" + packagingDate.format(DateTimeFormatter.ofPattern("yyyy-MM-dd")) +
//                '}';
//    }
}
