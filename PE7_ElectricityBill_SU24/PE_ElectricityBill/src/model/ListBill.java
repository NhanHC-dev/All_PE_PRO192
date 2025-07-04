package model;

import java.io.*;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.function.Predicate;
import view.Validation;

public final class ListBill extends ArrayList<Bill> {
    public ListBill() {
        super();
        this.loadDataFromFile();
    }

    public void listAllBills() {
        this.listAllBills(this);
    }

    private void listAllBills(ArrayList<Bill> list) {
        int total = list.size();

        if (total <= 0) {
            System.err.println("Sorry. Nothing to print.");
            return;
        }

        list.sort(Comparator.comparing(Bill::getDueDate).reversed());

        System.out.println("List all bills");
        System.out.println("--------------------------------");

        for (Bill bill : list) {
            System.out.println(bill);
        }

        System.out.println("--------------------------------");
        System.out.println("Total: " + total + " bills.");
    }

    public void addNewBill() {
        System.out.println("Add new bill");
        System.out.println("--------------------------------");
        int newBillID;
        String newCustomerName;
        double newAmount;
        Date newDueDate = null;
        boolean isPaid;

        while (true) {
            newBillID = Validation.getInt("Enter Bill ID: ", 1, Integer.MAX_VALUE);
            if (!this.isBillIDDuplicated(newBillID)) {
                break;
            }
            System.out.println("Bill ID already exists. Please enter a different ID.");
        }

        newCustomerName = Validation.getString("Enter Customer Name: ");
        newAmount = Validation.getDouble("Enter Amount: ", 0, Double.MAX_VALUE);

        while (true) {
            String dateStr = Validation.getString("Enter Due Date (dd/MM/yyyy): ");
            try {
                newDueDate = Validation.checkValidDate(dateStr);
                break;
            } catch (ParseException e) {
                System.out.println("Invalid date format. Please try again.");
            }
        }
        isPaid = Validation.getBoolean("Is Paid (true/false): ");
        this.add(new Bill(newBillID, newCustomerName, newAmount, newDueDate, isPaid));
    }

    private boolean isBillIDDuplicated(int billID) {
        for (Bill bill : this) {
            if (bill.getId() == billID) {
                return true;
            }
        }
        return false;
    }

    private void loadDataFromFile() {
        File file = new File("Electricitybill.txt");
        try (BufferedReader br = new BufferedReader(new FileReader(file))) {
            String line;
            while ((line = br.readLine()) != null) {
                String[] parts = line.split(",");
                int id = Integer.parseInt(parts[0]);
                String customerName = parts[1];
                double amount = Double.parseDouble(parts[2]);
                Date dueDate = Validation.checkValidDate(parts[3]);
                boolean isPaid = Boolean.parseBoolean(parts[4]);
                this.add(new Bill(id, customerName, amount, dueDate, isPaid));
            }
        } catch (FileNotFoundException e) {
            System.err.println("File not found: " + file.getName());
        } catch (IOException | ParseException e) {
            e.printStackTrace();
        }
    }

    public ArrayList<Bill> search(Predicate<Bill> predicate) {
        ArrayList<Bill> list = new ArrayList<>();

        for (Bill bill : this) {
            if (predicate.test(bill)) {
                list.add(bill);
            }
        }
        if (list.isEmpty()) {
            System.err.println("Cannot find any matching bills.");
        }

        return list;
    }

    public Bill getLargestAmountBill() {
        return Collections.max(this, Comparator.comparingDouble(Bill::getAmount));
    }

    public ArrayList<Bill> getUnpaidBills() {
        ArrayList<Bill> unpaidBills = new ArrayList<>();
        for (Bill bill : this) {
            if (!bill.isPaid()) {
                unpaidBills.add(bill);
            }
        }
        return unpaidBills;
    }
}
