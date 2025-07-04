package model;

import utils.Validation;
import utils.exception.ProducerFullException;

import java.io.*;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Collections;
import java.util.function.Predicate;


public final class PatientList extends ArrayList<Patient>{
    public PatientList(){
        super();
        this.loadDataFromFile();
    }
    public void listAllProduct(){
        this.listAllProduct(this);
    }
    public void listAllProduct(ArrayList<Patient> list){
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
        for (Patient patient : list) {
            System.out.println(String.valueOf(patient));
        }
        System.out.println("--------------------------------");
        System.out.println("Total: " + total + " product.");
    }
    private void loadDataFromFile() {
        File file = new File("patient.csv");
        try {
            Object[] lines = new BufferedReader(new FileReader(file)).lines().toArray();
            for(var line : lines){
                String[] row = line.toString().split(",");
                if(row.length <= 1) {
                    continue;
                }
                DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
                LocalDate dateLocal = LocalDate.parse(row[2],dateTimeFormatter);
                this.add(new Patient(row[0],row[1],dateLocal,row[3]));
            }
        }catch (Exception ioException){
            System.out.println("Error.");
        }
    }
    public void addNewProduct() throws ProducerFullException {
        if(this.size() >= 10000){
            throw new ProducerFullException("The producer can only hold 10,000 products and is full.");
        }
        System.out.println("Add new book");
        System.out.println("-----------------------------------------");
        String newID = null;
        String newName = null;
        LocalDate newDob =null;
        String newPhone = null;

        while(true){
            newID = Validation.getString("Enter Product ID: ");
            if(!this.isProDuctIDDuplicated(newID)){
                break;
            }
        }
        newName = Validation.getString("Enter Product Name: ");
        newDob = Validation.inputDate("Enter Patient dob (yyyy-mm-dd): ");
        newPhone = Validation.getString("Enter new phone: ");

        this.add(new Patient(newID,newName,newDob,newPhone));
    }
    private boolean isProDuctIDDuplicated(String productID) {
        productID = productID.trim().toUpperCase();

        for (Patient book : this) {
            if (book.getId().equalsIgnoreCase(productID)) {
                return true;
            }
        }

        return false;
    }
    public ArrayList<Patient> search(Predicate<Patient> predicate){
        ArrayList<Patient> list = new ArrayList<>();

        for (Patient patient : this ) {
            if (predicate.test(patient)) {
                list.add(patient);
            }
        }
        if (list.isEmpty()) {
            System.err.println("List Empty.");
        }
        return list;
    }
    private boolean isPatientPhoneDuplicated(String phone) {
        phone = phone.trim();
        for (Patient patient : this) {
            if (patient.getPhone().equalsIgnoreCase(phone)) {
                return true;
            }
        }

        return false;
    }
    private Patient search(String ID) {
        for (Patient p : this) {
            if (p.getId().equalsIgnoreCase(ID)) {
                return p;
            }
        }

        return null;
    }
    public void editInfo() {
        String phone = null;
        System.out.println("Edit Patient information");
        System.out.println("--------------------------------");

        String ID = Validation.getString("Enter ID (st. ID P001): ", "[pP][\\d]{3}");
        Patient patient = this.search(ID);

        if (patient == null) {
            System.err.println("Not found!");

            return;
        }
        System.out.println(patient);
        while(true){
            phone = Validation.getString("Enter new phone: ");
            if(!isPatientPhoneDuplicated(phone)){
                break;
            }
        }
        patient.setPhone(phone);
        System.out.println("Edit successfully!");
        listAllProduct();
    }
}
