package controller;

import model.BrandList;
import model.Brands;
import view.Menu;
import view.Validation;

public class BMWManagement extends Menu<String> {
    private BrandList list;

    public BMWManagement() {
        super("Brand Management", new String[]{
                "Display all brands",
                "Search brands",
                "Delete brands",
                "Update brands",
                "Statistical amount of cars by audio brand",
                "Exit"
        });
        this.list = new BrandList();
    }

    @Override
    public void execute(int choice) {
        switch (choice) {
            case 1 -> listAllBrands();
            case 2 -> find();
            case 3 -> deleteList();
            case 4 -> update();
            case 5 -> displayStatisticsByAudioBrand();
            case 6 -> this.stop();
            default -> System.err.println("Input again :)");
        }
    }
    public void listAllBrands() {
        this.list.listAllBrands();
    }
    public void displayStatisticsByAudioBrand() {
        this.list.displayStatisticsByAudioBrand();
    }
    public void find() {
        String[] options = {
                "Search by id",
                "Search by model year",
                "Exit"
        };

        new Menu<String>("Searching model", options) {
            @Override
            public void execute(int choice) {
                switch (choice) {
                    case 1 -> {
                        String modelID = Validation.getString("Enter model id: ");
                        list.listAllBrands(list.search(b -> b.getBrandID().contains(modelID)));
                    }
                    case 2 -> {
                        int year = Validation.getInt("Enter model year: ");
                        System.out.println("Cars with model year before " + year + ":");
                        System.out.println("--------------------------------");
                        list.listAllBrands(list.search(brands -> {
                            int modelYear = Validation.extractYearFromModel(brands.getModel());
                            return modelYear != -1 && modelYear <= year;
                        }));
                    }
                    case 3 -> stop();
                }
            }
        }.run();
    }

    public void deleteList() {
        String[] options = {
                "Delete by id",
                "Delete by model",
                "Exit"
        };

        new Menu<String>("Deleting model",options){
            @Override
            public void execute(int choice) {
                switch (choice) {
                    case 1 -> {
                        String modelID = Validation.getString("Enter id to delete: ");
                        if (list.deleteByCondition(b -> b.getBrandID().equals(modelID))){
                            System.out.println("Brand deleted successfully.");
                        }else {
                            System.out.println("No matching brand found.");
                        }
                    }
                    case 2 -> {
                        String model = Validation.getString("Enter model to delete: ");
                        if (list.deleteByCondition(b -> b.getModel().equals(model))){
                            System.out.println("Brand deleted successfully.");
                        }else {
                            System.out.println("No matching brand found.");
                        }
                    }
                    case 3 -> stop();
                }
            }
        }.run();
    }

    public void update() {
        String brandID;
        while (true) {
            brandID = Validation.getString("Enter ID");
            if (Validation.checkValid(brandID)) {
                break;
            }
            System.out.println("The brandID must follow the format starting with 'B' and include a '-' character");
        }
        String finalBrandID = brandID;
        this.list.forEach(brand -> {
            if (brand.getBrandID().equalsIgnoreCase(finalBrandID)) {
                brand.setModel(Validation.getString("Enter new model: "));
                brand.setAudioBrand(Validation.getString("Enter new audio brand: "));
                brand.setPrice(Validation.getDouble("Enter new price: "));

                System.out.println("Brand updated successfully.");
            }
        });
        System.out.println("Brand ID " + finalBrandID + " not found.");
    }

    public static void main(String[] args) {
        new BMWManagement().run();
    }
}
