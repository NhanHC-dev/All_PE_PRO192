package model;

import java.text.SimpleDateFormat;
import java.util.Date;

public class Bill {
    private int id;
    private String customerName;
    private double amount;
    private Date dueDate;
    private boolean isPaid;

    public Bill(int id, String customerName, double amount, Date dueDate, boolean isPaid) {
        this.id = id;
        this.customerName = customerName;
        this.amount = amount;
        this.dueDate = dueDate;
        this.isPaid = isPaid;
    }

    // Getters and Setters
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getCustomerName() {
        return customerName;
    }

    public void setCustomerName(String customerName) {
        this.customerName = customerName;
    }

    public double getAmount() {
        return amount;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }

    public Date getDueDate() {
        return dueDate;
    }

    public void setDueDate(Date dueDate) {
        this.dueDate = dueDate;
    }

    public boolean isPaid() {
        return isPaid;
    }

    public void setPaid(boolean paid) {
        isPaid = paid;
    }

    @Override
    public String toString() {
        return "Bill{" +
                "id=" + id +
                ", customerName='" + customerName + '\'' +
                ", amount=" + amount +
                ", dueDate=" + new SimpleDateFormat("dd/MM/yyyy").format(dueDate) +
                ", isPaid=" + isPaid +
                '}';
    }
}
