package controller;

import model.Vaccine;
import model.VaccineList;
import view.Menu;
import view.Validation;

import java.util.Comparator;
import java.util.Date;
import java.util.List;

public class VaccineManagement extends Menu<String> {
    VaccineList vaccineList = new VaccineList();
    public VaccineManagement(String title, String[] menuOptions) {
        super(title, menuOptions);    }

    @Override
    public void execute(int selection) {
        switch (selection) {
            case 1 -> vaccineList.listAll();
            case 2 -> addNewVaccine();
            case 3 -> deleteByCode();
            case 4 -> {
                System.out.println("Vaccines sorted by name.");
                vaccineList.listAll(Comparator.comparing(Vaccine::getName));            }
            case 5 -> vaccineList.saveToFile("vaccines_output.txt");
            case 6 -> searchByKeyword();
            case 7 -> {
                System.out.println("Expired Vaccines:");
                vaccineList.listAll(vaccineList.getExpiredVaccines());
            }
            case 8->{
                System.out.println("Recently Injected Vaccines (within last year):");
                vaccineList.listAll(vaccineList.getRecentlyInjectedVaccines());
            }
            case 9 -> System.exit(0);
            default-> System.out.println("Invalid option!");
        }
    }
    public void addNewVaccine() {
        String code ;
        while (true){
            code = Validation.getString("Enter Vaccine Code: ");
            if (isVaccineCodeDuplicated(code)) {
                System.out.println("Code is duplicated, please try again.");
            } else {
                break;
            }
        }
        String name = Validation.getString("Enter name: ");
        int quantity = Validation.getInt("Enter quantity: ");
        Date exDate = Validation.checkValidDate("Enter expiration date (dd/MM/yyyy): ");
        double price = Validation.getDouble("Enter price: ");
        Date lastInjectedDate = Validation.checkValidDate("Enter injected date (dd/MM/yyyy): ");
        vaccineList.addVaccine( new Vaccine(code, name, quantity, exDate, price, lastInjectedDate));
        System.out.println("Vaccine added successfully.");
    }

    private boolean isVaccineCodeDuplicated(String code) {
        return vaccineList.stream().anyMatch(v -> v.getCode().equalsIgnoreCase(code));
    }
    public void deleteByCode() {
        String code = Validation.getString("Enter vaccine code to delete: ");
        vaccineList.delete(v -> v.getCode().equalsIgnoreCase(code));
        System.out.println("Vaccine deleted (if it existed).\n");
    }

    public void searchByKeyword() {
        String keyword = Validation.getString("Enter keyword to search (by name/code): ").toLowerCase();

        List<Vaccine> result = vaccineList.stream()
                .filter(v -> v.getName().toLowerCase().contains(keyword) || v.getCode().toLowerCase().contains(keyword))
                .toList();

        if (result.isEmpty()) {
            System.out.println("No matching vaccines found.");
        } else {
            result.forEach(System.out::println);
        }
    }
    public static void main(String[] args) {
        String[] menuOptions = {
                "Display all vaccines from load vaccine data from file",
                "Add new vaccine",
                "Delete a vaccine by code",
                "Sort vaccines by name",
                "Save data to file",
                "Search vaccine by name/code",
                "List expired vaccines",
                "List recently injected vaccines (within last year)",
                "Exit"
        };
         new VaccineManagement("========= VACCINE STOCK MANAGEMENT =========", menuOptions).run();
    }
}
