package model;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class Ticket {
    private String ticketID;
    private String eventName;
    private LocalDate eventDate;
    private double price;
    private boolean sold;

    public Ticket(String ticketID, String eventName, LocalDate eventDate, double price, boolean sold) {
        this.ticketID = ticketID;
        this.eventName = eventName;
        this.eventDate = eventDate;
        this.price = price;
        this.sold = sold;
    }

    public String getTicketID() {
        return ticketID;
    }

    public void setTicketID(String ticketID) {
        this.ticketID = ticketID;
    }

    public String getEventName() {
        return eventName;
    }

    public void setEventName(String eventName) {
        this.eventName = eventName;
    }

    public LocalDate getEventDate() {
        return eventDate;
    }

    public void setEventDate(LocalDate eventDate) {
        this.eventDate = eventDate;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public boolean isSold() {
        return sold;
    }

    public void setSold(boolean sold) {
        this.sold = sold;
    }


    public String toData() {
        return String.format("%s, %s, %s, %.2f, %s",
                ticketID,
                eventName,
                eventDate.format(DateTimeFormatter.ofPattern("yyyy-MM-dd")),
                price,
                sold);
    }

    @Override
    public String toString() {
        return "Ticket{" +
                "ticketID='" + ticketID + '\'' +
                ", eventName='" + eventName + '\'' +
                ", eventDate=" + eventDate.format(DateTimeFormatter.ofPattern("yyyy-MM-dd")) +
                ", price=" + price +
                ", sold=" + sold +
                '}';
    }
}