package model;

import view.Validation;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.function.Predicate;
import java.util.logging.Level;
import java.util.logging.Logger;

public class BrandList extends ArrayList<Brands> {

    public BrandList() {
        loadDataFromFile("brands.txt");
    }

    public void addBrand(Brands brands) {
        this.add(brands);
    }

     public void listAllBrands() {
         this.listAllBrands(this);
     }

    public void listAllBrands(ArrayList<Brands> list) {
        int total = list.size();
        if (total <= 0) {
            System.out.println("Sorry. Nothing to print!");
            return;
        }
        System.out.println("List all product");
        System.out.println("--------------------------------");
        list.forEach(System.out::println);
        System.out.println("--------------------------------");
        System.out.println("Total: " + total + ".");
    }

    private void loadDataFromFile(String fname) {
        File file = new File(fname);
        try {
            Object[] lines = new BufferedReader(new FileReader(file)).lines().toArray();
            for(var line : lines){
                String[] row = line.toString().split(",");
                if (row.length == 3 && Validation.checkValid(row[0]) && !isBrandCodeDuplicated(row[0])) {
                    this.add(new Brands(row[0], row[1], row[2].split(":")[0], Double.parseDouble(row[2].split(":")[1])));
                }
            }
        }catch (Exception ioException){
            Logger.getLogger(BrandList.class.getName()).log(Level.SEVERE, "Error reading file: ", ioException);
        }
    }
    private boolean isBrandCodeDuplicated(String brandID) {
        return this.stream().anyMatch(brand -> brand.getBrandID().equalsIgnoreCase(brandID.trim()));
    }
    public ArrayList<Brands> search(Predicate<Brands> predicate){
        ArrayList<Brands> list = new ArrayList<>();

        for (Brands brands : this ) {
            if (predicate.test(brands)) {
                list.add(brands);
            }
        }
        if (list.isEmpty()) {
            System.err.println("List Empty.");
        }
        return list;
    }

    public boolean deleteByCondition(Predicate<Brands> condition) {
        return this.removeIf(condition);
    }

    public void displayStatisticsByAudioBrand() {
        ArrayList<String> countedBrands = new ArrayList<>();

        System.out.println("Statistics of cars by audio brand:");

        for (Brands brand : this) {
            String audioBrand = brand.getAudioBrand();

            if (countedBrands.contains(audioBrand)) {
                continue;
            }

            int count = 0;
            for (Brands b : this) {
                if (b.getAudioBrand().equals(audioBrand)) {
                    count++;
                }
            }

            System.out.println("Audio Brand: " + audioBrand + " - Number of Products: " + count);
            System.out.println("--------------------------------");

            for (Brands b : this) {
                if (b.getAudioBrand().equals(audioBrand)) {
                    System.out.println(b);
                }
            }

            countedBrands.add(audioBrand);
            System.out.println("--------------------------------");
        }
    }
}
