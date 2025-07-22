package model;

import java.io.*;
import java.text.*;
import java.util.*;
import java.util.function.Predicate;

public class BillList extends ArrayList<Bill> {
    private final String FILE_NAME = "ElectricityBill.txt";

    public BillList() {
        loadFromFile(FILE_NAME);
    }

    private void loadFromFile(String filename) {
        SimpleDateFormat df = new SimpleDateFormat("dd/MM/yyyy");
        try (BufferedReader br = new BufferedReader(new FileReader(filename))) {
            String line;
            while ((line = br.readLine()) != null) {
                String[] b = line.split(",");
                if (b.length == 5) {
                    try {
                        int id = Integer.parseInt(b[0].trim());
                        String customer = b[1].trim();
                        double amount = Double.parseDouble(b[2].trim());
                        Date dueDate = df.parse(b[3].trim());
                        boolean paid = Boolean.parseBoolean(b[4].trim());

                        this.add(new Bill(id, customer, amount, dueDate, paid));
                    } catch (Exception e) {
                        System.err.println("Invalid line skipped: " + line);
                        System.err.println("  Reason: " + e.getMessage());
                    }
                } else {
                    System.err.println("Malformed line skipped: " + line);
                }
            }
        } catch (IOException e) {
            System.err.println("Error reading file: " + e.getMessage());
        }
    }

    public void saveToFile() {
        SimpleDateFormat df = new SimpleDateFormat("dd/MM/yyyy");
        try (PrintWriter pw = new PrintWriter(new FileWriter(FILE_NAME))) {
            for (Bill b : this) {
                pw.printf("%d,%s,%.0f,%s,%b\n",
                        b.getId(),
                        b.getCustomerName(),
                        b.getAmount(),
                        df.format(b.getDueDate()),
                        b.isPaid());
            }
        } catch (IOException e) {
            System.err.println("Error saving to file: " + e.getMessage());
        }
    }

    public void addBill(Bill b) {
        super.add(b);
        saveToFile();
    }

    public void deleteIf(Predicate<Bill> condition) {
        if (this.removeIf(condition)) {
            saveToFile();
        }
    }

    public ArrayList<Bill> search(Predicate<Bill> condition) {
        ArrayList<Bill> result = new ArrayList<>();
        for (Bill b : this) {
            if (condition.test(b)) result.add(b);
        }
        return result;
    }

    public List<Bill> getPaidBills() {
        return search(Bill::isPaid);
    }

    public List<Bill> getUnpaidBills() {
        return search(b -> !b.isPaid());
    }


    public ArrayList<Bill> getOverdueBills() {
        Date now = new Date();
        return search(b -> !b.isPaid() && b.getDueDate().before(now));
    }

    public Bill getLargestAmountBill() {
        return this.stream().max(Comparator.comparingDouble(Bill::getAmount)).orElse(null);
    }
}
