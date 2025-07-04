package model;

public class Brands {
    private String brandID;
    private String model;
    private String audioBrand;
    private double price;

    public Brands(String brandID, String model, String audioBrand, double price) {
        this.brandID = brandID;
        this.model = model;
        this.audioBrand = audioBrand;
        this.price = price;
    }

    public String getBrandID() {
        return brandID;
    }

    public void setBrandID(String brandID) {
        this.brandID = brandID;
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public String getAudioBrand() {
        return audioBrand;
    }

    public void setAudioBrand(String audioBrand) {
        this.audioBrand = audioBrand;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    @Override
    public String toString() {
        return  "brandID=" + brandID  +
                ", model=" + model +
                ", audioBrand=" + audioBrand +
                ", price=" + price;
    }
}
