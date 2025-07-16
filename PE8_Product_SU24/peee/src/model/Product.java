package model;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.concurrent.TimeUnit;

public class Product implements Comparable<Product> {
    private String productID;
    private String productName;
    private String category;
    private double price;
    private Date productDate;

    public Product(String productID, String productName, String category, Date productDate, double price) {
        this.productID = productID;
        this.productName = productName;
        this.category = category;
        this.productDate = productDate;
        this.price = price;
    }

    public String getProductID() { return productID; }
    public String getProductName() { return productName; }
    public String getCategory() { return category; }
    public double getPrice() { return price; }
    public Date getProductDate() { return productDate; }

    public void setProductID(String productID) { this.productID = productID; }
    public void setProductName(String productName) { this.productName = productName; }
    public void setCategory(String category) { this.category = category; }
    public void setPrice(double price) { this.price = price; }
    public void setProductDate(Date productDate) { this.productDate = productDate; }

    public double getDiscount() {
        long ageInDays = TimeUnit.DAYS.convert(
                new Date().getTime() - productDate.getTime(), TimeUnit.MILLISECONDS);
        double ageInYears = ageInDays / 365.0;
        if (ageInYears < 1) return 0;
        else if (ageInYears < 2) return 0.2 * price;
        else return 0.5 * price;
    }

    @Override
    public String toString() {
        SimpleDateFormat df = new SimpleDateFormat("dd/MM/yyyy");
        return String.format("%s | %-12s | %-12s | %.2f | %s | Discount: %.2f",
                productID, productName, category, price, df.format(productDate), getDiscount());
    }
    @Override
    public int compareTo(Product o) {
        return Double.compare(getPrice(), o.getPrice());
    }
}