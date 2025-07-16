package controller;

import model.Bill;
import model.BillList;
import view.BillView;
import view.Menu;

import java.text.ParseException;
import java.util.Date;
import java.util.List;

public class BillManagement extends Menu<String> {
    BillList billList;
    BillView view;

    static String[] options = {
            "Display all bills",
            "Display paid and unpaid bills",
            "Display overdue bills",
            "Add a new bill",
            "Delete a bill",
            "Find bill with largest amount",
            "Save to file",
            "Exit"
    };

    public BillManagement() {
        super("========= ELECTRICITY BILL MANAGEMENT =========", options);
        billList = new BillList();
        view = new BillView();
    }

    @Override
    public void execute(int n) throws ParseException {
        switch (n) {
            case 1 -> view.displayList(billList, "All Bills");

            case 2 -> {
                    List<Bill> paid = billList.getPaidBills();
                    List<Bill> unpaid = billList.getUnpaidBills();
                    view.displayByPaidStatus(paid, unpaid);
            }

            case 3 -> {
                List<Bill> overdue = billList.getOverdueBills();
                view.displayList(overdue, "Overdue Bills");
            }

            case 4 -> {
                Bill newBill = view.inputBill();
                if (newBill != null) {
                    billList.addBill(newBill);
                    view.showMessage("Bill added successfully.");
                }
            }

            case 5 -> deleteMenu();

            case 6 -> {
                Bill max = billList.getLargestAmountBill();
                view.displaySingle(max, "Bill with Largest Amount");
            }

            case 7 -> {
                billList.saveToFile();
                view.showMessage("Bills saved to file successfully.");
            }

            case 8 -> {
                view.showMessage("Exiting program...");
                System.exit(0);
            }
        }
    }

    private void deleteMenu() throws ParseException {
        String[] deleteOptions = {
                "Delete by ID",
                "Delete by Customer Name",
                "Delete by Due Date",
                "Return"
        };
        new Menu("Delete Bill", deleteOptions) {
            @Override
            public void execute(int n) throws ParseException {
                switch (n) {
                    case 1 -> {
                        int id = view.inputId();
                        billList.deleteIf(b -> b.getId() == id);
                        view.showMessage("Deleted bills with ID: " + id);
                    }
                    case 2 -> {
                        String name = view.inputCustomerName();
                        billList.deleteIf(b -> b.getCustomerName().equalsIgnoreCase(name));
                        view.showMessage("Deleted bills with name: " + name);
                    }
                    case 3 -> {
                        Date d = view.inputDueDate();
                        billList.deleteIf(b -> b.getDueDate().equals(d));
                        view.showMessage("Deleted bills with due date: " + view.formatDate(d));
                    }
                    case 4 -> this.stop();
                }
            }
        }.run();
    }

    public static void main(String[] args) throws ParseException {
        new BillManagement().run();
    }
}
