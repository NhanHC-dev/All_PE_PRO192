package model;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Objects;

public class Vaccine {
    private String name;
    private String code;
    private int quantity;
    private Date expirationDate;
    private double price;
    private Date lastInjectedDate;

    SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");

    public Vaccine(String code, String name,  int quantity, Date expirationDate, double price, Date lastInjectedDate) {
        this.name = name;
        this.code = code;
        this.quantity = quantity;
        this.expirationDate = expirationDate;
        this.price = price;
        this.lastInjectedDate = lastInjectedDate;
    }

    public String getName() { return name; }
    public String getCode() { return code; }
    public int getQuantity() { return quantity; }
    public Date getExpirationDate() { return expirationDate; }
    public double getPrice() { return price; }
    public Date getLastInjectedDate() { return lastInjectedDate; }

    public void setName(String name) { this.name = name; }
    public void setCode(String code) { this.code = code; }
    public void setQuantity(int quantity) { this.quantity = quantity; }
    public void setExpirationDate(Date expirationDate) { this.expirationDate = expirationDate; }
    public void setPrice(double price) { this.price = price; }
    public void setLastInjectedDate(Date lastInjectedDate) { this.lastInjectedDate = lastInjectedDate; }

    @Override
    public String toString() {
        return String.format("Code: %s, Name: %s, Qty: %d, Exp: %s, Price: %.2f, LastInjected: %s\n",
                getCode(), getName(), quantity, sdf.format(expirationDate), price, sdf.format(lastInjectedDate));
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (!(obj instanceof Vaccine)) return false;
        Vaccine v = (Vaccine) obj;
        return Objects.equals(this.code, v.code);
    }

    public String toFileString() {
        return String.format("%s,%s,%d,%s,%.2f,%s",
                name, code, quantity, sdf.format(expirationDate), price, sdf.format(lastInjectedDate));
    }
}
