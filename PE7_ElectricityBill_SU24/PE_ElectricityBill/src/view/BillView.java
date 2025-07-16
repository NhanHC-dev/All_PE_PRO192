package view;

import model.Bill;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.List;

public class BillView {
    private final Validation val = new Validation();

    public int inputId() {
        return val.getInt("Enter Bill ID: ", 1, Integer.MAX_VALUE);
    }

    public String inputCustomerName() {
        return val.getString("Enter customer name: ");
    }

    public Date inputDueDate() throws ParseException {
        return val.inputDate("Enter due date (dd/MM/yyyy): ");
    }

    public double inputAmount() {
        return val.getDouble("Enter bill amount: ");
    }

    public boolean inputIsPaid() {
        return val.getBoolean("Is the bill paid? (true/false): ");
    }

    public Bill inputBill() throws ParseException {
        int id = inputId();
        String name = inputCustomerName();
        double amount = inputAmount();
        Date dueDate = inputDueDate();
        boolean isPaid = inputIsPaid();
        return new Bill(id, name, amount, dueDate, isPaid);
    }

    public void displayList(List<Bill> list, String allBills) {
        displayList(list, Comparator.comparing(Bill::getId), "All Bills");
    }

    public void displayList(List<Bill> list, Comparator<Bill> comparator) {
        displayList(list, comparator, "Bill List");
    }

    public void displayList(List<Bill> list, Comparator<Bill> comparator, String title) {
        if (list == null || list.isEmpty()) {
            System.out.println("No bills to display.");
            return;
        }

        if (comparator != null) {
            Collections.sort(list, comparator);
        }

        System.out.println("\n--- " + title + " ---");
        System.out.println("-----------------------------------------------------------");
        for (Bill b : list) {
            System.out.println(b);
        }
        System.out.println("-----------------------------------------------------------");
        System.out.println("Total: " + list.size() + " bills.\n");
    }

    public void displaySingle(Bill bill, String title) {
        System.out.println("\n--- " + title + " ---");
        if (bill != null) {
            System.out.println(bill);
        } else {
            System.out.println("No bill found.");
        }
    }

    public void showMessage(String msg) {
        System.out.println(msg);
    }

    public String formatDate(Date d) {
        return new SimpleDateFormat("dd/MM/yyyy").format(d);
    }
    public void displayByPaidStatus(List<Bill> paid, List<Bill> unpaid) {
        System.out.println("\n--- UNPAID BILLS ---");
        if (unpaid == null || unpaid.isEmpty()) {
            System.out.println("No unpaid bills.");
        } else {
            unpaid.stream()
                    .sorted(Comparator.comparingInt(Bill::getId))
                    .forEach(System.out::println);
            System.out.println("Total: " + unpaid.size() + " unpaid bills.\n");
        }

        System.out.println("--- PAID BILLS ---");
        if (paid == null || paid.isEmpty()) {
            System.out.println("No paid bills.");
        } else {
            paid.stream()
                    .sorted(Comparator.comparingInt(Bill::getId))
                    .forEach(System.out::println);
            System.out.println("Total: " + paid.size() + " paid bills.\n");
        }
    }
}
