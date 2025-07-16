package controller;

import model.BeanVariety;
import model.BeanVarietyList;
import view.Menu;
import view.Validator;

import java.util.ArrayList;
import java.util.List;

public class BeanVarietyManagement extends Menu<String>{
    private static final BeanVarietyList varietyList = new BeanVarietyList();
    private static final Menu<String> menu = new Menu<>();
    public static void main(String[] args) {
        varietyList.readFile("bean_varieties.txt");
        boolean exit = false;

        while (!exit) {
            displayMenu();
            int choice = menu.int_getChoice(getMenuOptions());
            switch (choice) {
                case 1 -> addNewVariety();
                case 2 -> varietyList.listAll();
                case 3 -> searchVarietyByName();
                case 4 -> {
                    varietyList.sortByName();
                    System.out.println("Sorted by name.");
                }
                case 5 -> deleteVarietyById();
                case 6 -> exit = true;
                default -> System.out.println("Invalid choice. Please try again.");
            }
        }
    }

    private static void displayMenu() {
        System.out.println("\n=== Bean Variety Management ===");
    }

    private static ArrayList<String> getMenuOptions() {
        ArrayList<String> options = new ArrayList<>();
        options.add("Add a new bean variety");
        options.add("Display all bean varieties");
        options.add("Search bean varieties by name");
        options.add("Sort bean varieties by name");
        options.add("Delete a bean variety by ID");
        options.add("Exit");
        return options;
    }

    private static void addNewVariety() {
        String id = Validator.getString("Enter bean variety ID: ");
        String name = Validator.getString("Enter bean variety name: ");
        varietyList.addBeanVariety(new BeanVariety(id, name));
        System.out.println("Bean variety added successfully.");
    }

    private static void searchVarietyByName() {
        String name = Validator.getString("Enter name to search: ");
        List<BeanVariety> results = varietyList.search(variety ->
                variety.getName().toLowerCase().contains(name.toLowerCase())
        );
        if (!results.isEmpty()) {
            results.forEach(System.out::println);
        } else {
            System.out.println("No matching bean varieties found.");
        }
    }

    private static void deleteVarietyById() {
        String id = Validator.getString("Enter bean variety ID to delete: ");
        boolean isDeleted = varietyList.delete(variety ->
                variety.getId().equals(id)
        );
        if (isDeleted) {
            System.out.println("Bean variety deleted successfully.");
        } else {
            System.out.println("No matching bean variety found to delete.");
        }
    }
}
