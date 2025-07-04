package controller;

import model.PatientList;

import utils.Validation;
import utils.exception.ProducerFullException;
import view.Menu;
import java.time.LocalDate;

public final class ProductManagement extends Menu<String> {
    private static String[] options = {
            "List all products",
            "Search product",
            "Add new product",
            "Update",
            "Exit"};
    private PatientList patientList;

    public ProductManagement() {
    }

    public ProductManagement(String title, String[] options, PatientList patientList) {
        super(title, options);

        this.patientList = patientList;

    }

    @Override
    public void execute(int choice) {
        switch (choice) {
            case 1 -> {
                this.patientList.listAllProduct();
            }
            case 2 -> {
                this.search();
            }
            case 3 -> {
                try {
                    this.patientList.addNewProduct();
                } catch (ProducerFullException ex) {
                    System.err.println("A problem occured: " + ex);
                }
            }
            case 4 ->{
                this.patientList.editInfo();
            }
            case 5 -> {
                this.stop();
            }
        }
    }
        private void search() {
            String[] searchOptions = {
                    "Find by ID",
                    "Find by name",
                    "Find by Date o B",
                    "Find by Phone:",
                    "Return"
            };
            new Menu<String>("Patient Searching", searchOptions) {
                @Override
                public void execute(int choice) {
                    switch (choice) {
                        case 1 -> {
                            String productID = Validation.getString("Enter Patient ID (st. ID 1): ", "[pP][\\d]{3}");
                            patientList.listAllProduct(patientList.search(p -> p.getId().equalsIgnoreCase(productID)));
                        }

                        case 2 -> {
                            String productName = Validation.getString("Enter Patient Name: ");
                            patientList.listAllProduct(patientList.search(predicate -> predicate.getName().toLowerCase().contains(productName.toLowerCase())));
                        }
                        case 3 -> {
                            LocalDate productMaDate = Validation.inputDate("Enter Patient Date format yyyy-mm-dd: ");
                            patientList.listAllProduct(patientList.search(predicate -> predicate.getDob().equals(productMaDate)));
                        }
                        case 4 -> {
                            String phone = Validation.getString("Enter Patient phone : ");
                            patientList.listAllProduct(patientList.search(p -> p.getPhone().equalsIgnoreCase(phone)));
                        }
                        default -> {
                            this.stop();
                        }
                    }
                }
            }.run();
        }

    public static void main(String[] args) {
        new ProductManagement("Patient Management", options, new PatientList()).run();
    }
}