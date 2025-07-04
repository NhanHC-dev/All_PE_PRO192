package controller;

import model.Bill;
import model.ListBill;
import view.Menu;
import view.Validation;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.Date;

public class BillManagement extends Menu<String> {
    private static String[] options = {
            "Display all bills",
            "Add new bill",
            "Delete a bill",
            "Find largest amount bill",
            "List of unpaid bills",
            "Exit"
    };

    private ListBill listBill;

    public BillManagement() {
    }

    public BillManagement(String title, String[] options, ListBill listBill) {
        super(title, options);
        this.listBill = listBill;
    }

    @Override
    public void execute(int choice) {
        switch (choice) {
            case 1 -> listBill.listAllBills();
            case 2 -> listBill.addNewBill();
            case 3 -> deleteBill();
            case 4 -> {
                Bill largestBill = listBill.getLargestAmountBill();
                System.out.println("Bill with the largest amount: " + largestBill);
            }
            case 5 -> {
                ArrayList<Bill> unpaidBills = listBill.getUnpaidBills();
                System.out.println("Unpaid Bills:");
                for (Bill bill : unpaidBills) {
                    System.out.println(bill);
                }
                //unpaidBills.forEach(System.out::println);
            }
            case 6 -> this.stop();
        }
    }

    private void deleteBill() {
        String[] deleteOptions = {
                "Delete by Bill ID",
                "Delete by Customer Name",
                "Delete by Due Date",
                "Return"
        };

        new Menu<String>("Delete Bill", deleteOptions) {
            @Override
            public void execute(int choice) {
                switch (choice) {
                    case 1 -> {
                        int billID = Validation.getInt("Enter Bill ID: ", 1, Integer.MAX_VALUE);
                        listBill.removeIf(bill -> bill.getId() == billID);
                    }
                    case 2 -> {
                        String customerName = Validation.getString("Enter Customer Name: ");
                        listBill.removeIf(bill -> bill.getCustomerName().equalsIgnoreCase(customerName));
                    }
                    case 3 -> {
                        String dateStr = Validation.getString("Enter Due Date (dd/MM/yyyy): ");
                        try {
                            Date dueDate = Validation.checkValidDate(dateStr);
                            listBill.removeIf(bill -> bill.getDueDate().equals(dueDate));
                        } catch (ParseException e) {
                            System.out.println("Invalid date format. Please try again.");
                        }
                    }
                    default -> this.stop();
                }
            }
        }.run();
    }

    public static void main(String[] args) {
        new BillManagement("Electricity Bill Management", options, new ListBill()).run();
    }
}
