package model;
import java.time.LocalDate;

public class Product implements Comparable<Product> {
    private String productID;
    private String productName;
    private float unitPrice;
    private int quantity;
    private float version;
    private LocalDate productManufacturingDate;

    public Product() {
        this.productID = this.productName = "";
        this.unitPrice = this.version = 0;
        this.quantity = 0;
    }

    public Product(String productID, String productName, float unitPrice, int quantity, float version, LocalDate productManufacturingDate) {
        this.setProductID(productID);
        this.setProductName(productName);
        this.setUnitPrice(unitPrice);
        this.setQuantity(quantity);
        this.setVersion(version);
        this.setProductManufacturingDate(productManufacturingDate);
    }

    public String getProductID() {

        return productID;
    }

    public void setProductID(String productID) {
        if (productID != null && !productID.isEmpty() && !productID.isBlank()) {
            this.productID = productID.replaceAll("\\s+", " ").trim().toUpperCase();
        }
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        if (productName != null && !productName.isEmpty() && !productName.isBlank()) {
            this.productName = productName.replaceAll("\\s+", " ").trim();
        }
    }

    public float getUnitPrice() {
        return unitPrice;
    }

    public void setUnitPrice(float unitPrice) {
        this.unitPrice = unitPrice;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public float getVersion() {
        return version;
    }

    public void setVersion(float version) {
        this.version = version;
    }

    public LocalDate getProductManufacturingDate() {
        return productManufacturingDate;
    }

    public void setProductManufacturingDate(LocalDate productManufacturingDate) {
        this.productManufacturingDate = productManufacturingDate;
    }

    @Override
    public String toString() {
//        return "Product{" +
//                "productID='" + productID + '\'' +
//                ", productName='" + productName + '\'' +
//                ", unitPrice=" + unitPrice +
//                ", quantity=" + quantity +
//                ", version=" + version +
//                ", productManufacturingDate=" + productManufacturingDate +
//                '}';
        return "Product{" + this.productID  + " - "+ this.productName+ " - " + this.unitPrice+ " - " + this.quantity + " - " + this.version + " - " + this.productManufacturingDate + '}';
    }

    @Override
    public int compareTo(Product o) {
        return this.productID.compareToIgnoreCase(o.productID);
    }
}
