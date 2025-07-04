package model;

import utils.Validation;
import utils.exception.ProducerFullException;

import java.io.*;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Collections;
import java.util.function.Predicate;


public final class Producer extends ArrayList<Product>{
    public Producer(){
        super();
        this.loadDataFromFile();
    }
    public void listAllProduct(){
        this.listAllProduct(this);
    }
    public void listAllProduct(ArrayList<Product> list){
        int total = list.size();

        if(total <= 0){
            System.out.println("Sorry. Nothing to print!");

            return;
        }
        Collections.sort(list);
        System.out.println("List all product");
        System.out.println("--------------------------------");

//        for (var product : this) {
//            System.out.println(product);
//        }
        for (Product product : list) {
            System.out.println(String.valueOf(product));
        }
        System.out.println("--------------------------------");
        System.out.println("Total: " + total + " product.");
    }
    private void loadDataFromFile() {
        File file = new File("products.txt");
        try {
            Object[] lines = new BufferedReader(new FileReader(file)).lines().toArray();
            for(var line : lines){
                String[] row = line.toString().split(",");
                String ID = row[0];
                ID = ID.substring(3);
                String name = row[1];
                name = name.substring(6);
                String price = row[2];
                price = price.substring(11);
                float priceD = Float.parseFloat(price);
                String quantity = row[3];
                quantity = quantity.substring(10);
                Integer quantityInt = Integer.parseInt(quantity);
                String version = row[4];
                version = version.substring(9);
                float versionD = Float.parseFloat(version);
                String date = row[5];
                date =  date.substring(26);
                DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
                LocalDate dateLocal = LocalDate.parse(date,dateTimeFormatter);
                this.add(new Product(ID,name,priceD,quantityInt,versionD,dateLocal));
            }
        }catch (IOException ioException){
            System.out.println("Error.");
        }
    }
    public void addNewProduct() throws ProducerFullException {
        if(this.size() >= 10000){
            throw new ProducerFullException("The producer can only hold 10,000 products and is full.");
        }
        System.out.println("Add new book");
        System.out.println("-----------------------------------------");
        String newProductID = null;
        String newProductName = null;
        float newUnitPrice = 0.0F;
        int newQuantity = 0;
        float newVersion = 0.0F;
        LocalDate newProductMaDa =null;
        while(true){
            newProductID = Validation.getString("Enter Product ID: ");
            if(!this.isProDuctIDDuplicated(newProductID)){
                break;
            }
        }
        newProductName = Validation.getString("Enter Product Name: ");
        newUnitPrice = Validation.getFloat("Enter new unit price: ");
        newQuantity = Validation.getInt("Enter new quantity: ");
        newVersion = Validation.getFloat("Enter new Version:");
        newProductMaDa = Validation.inputDate("Enter Date Manufacturing (yyyy-mm-dd): ");
        this.add(new Product(newProductID,newProductName,newUnitPrice,newQuantity,newVersion,newProductMaDa));
    }
    private boolean isProDuctIDDuplicated(String productID) {
        productID = productID.trim().toUpperCase();

        for (Product book : this) {
            if (book.getProductID().equalsIgnoreCase(productID)) {
                return true;
            }
        }

        return false;
    }
    public ArrayList<Product> search(Predicate<Product> predicate){
        ArrayList<Product> list = new ArrayList<>();

        for (Product product : this ) {
            if (predicate.test(product)) {
                list.add(product);
            }
        }
        if (list.isEmpty()) {
            System.err.println("List Empty.");
        }
        return list;
    }
    public void displaySearch(ArrayList<Product> list) {
        int total = list.size();
        if (list.isEmpty()) {
            System.out.println("List Empty.");
        } else {
            System.out.println("List of Doctors:");
            for (Product product : list) {
                System.out.printf(String.valueOf(product));
            }
        }
        System.out.println("--------------------------------");
        System.out.println("Total: " + total + " product.");
    }
}
