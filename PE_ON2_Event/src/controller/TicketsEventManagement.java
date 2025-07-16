package controller;
import model.Ticket;
import model.TicketList;
import view.*;

import java.time.LocalDate;
import java.util.Comparator;
import java.util.List;

public class TicketsEventManagement extends Menu<String>{
    private TicketList list;
    public TicketsEventManagement() {
        super("EVENT TICKET MANAGEMENT", new String[]{
                "Display all tickets",
                "Add new ticket",
                "Update ticket by ID",
                "Delete ticket by ID",
                "Search tickets by event name",
                "Filter by sold status or date range",
                "Sort by event date (ascending)",
                "Sort by price (descending)",
                "Save to file",
                "Exit"
        });
        list = new TicketList();
    }

    @Override
    public void execute(int choice) {
        switch (choice) {
            case 1 -> list.listAllTickets();
            case 2 -> addNewTicket();
            case 3 -> updateTicketByID();
            case 4 -> deleteTicketByID();
            case 5 -> searchByEventName();
            case 6 -> filterByStatusOrDate();
            case 7 -> list.listAllTickets(Comparator.comparing(Ticket::getEventDate));
            case 8 -> list.listAllTickets((t1, t2) -> Double.compare(t2.getPrice(), t1.getPrice()));
            case 9 -> {
                list.saveToFile();
                System.out.println("Saved to file.");
            }
            case 10 -> this.stop();
            default -> System.out.println("Invalid choice.");
        }
    }

    private void addNewTicket() {
        String id = Validation.getString("Enter ticket ID: ");
        if (list.getTicketByID(id) != null) {
            System.out.println("ID already exists.");
            return;
        }
        String name = Validation.getString("Enter event name: ");
        LocalDate date = Validation.getDate("Enter event date");
        double price = Validation.getDouble("Enter price: ", 0.01, Double.MAX_VALUE);
        boolean sold = Boolean.parseBoolean(Validation.getString("Is sold? (true/false): "));

        boolean added = list.addTicket(new Ticket(id, name, date, price, sold));
        System.out.println(added ? "Ticket added." : "Add failed.");
    }

    private void updateTicketByID() {
        String id = Validation.getString("Enter ticket ID: ");
        Ticket t = list.getTicketByID(id);
        if (t == null) {
            System.out.println("Not found.");
            return;
        }
        double price = Validation.getDouble("Enter new price: ", 0.01, Double.MAX_VALUE);
        boolean sold = Boolean.parseBoolean(Validation.getString("Is sold? (true/false): "));
        boolean updated = list.updateTicket(id, price, sold);
        System.out.println(updated ? "Updated." : "Update failed.");
    }

    private void deleteTicketByID() {
        String id = Validation.getString("Enter ticket ID to delete: ");
        boolean deleted = list.deleteTicket(id);
        System.out.println(deleted ? "Deleted." : "Not found.");
    }

    private void searchByEventName() {
        String kw = Validation.getString("Enter keyword to search: ");
        List<Ticket> results = list.filterBy(t -> t.getEventName().toLowerCase().contains(kw.toLowerCase()));
        if (results.isEmpty()) System.out.println("No match.");
        else list.listAllTickets(new java.util.ArrayList<>(results), Comparator.comparing(Ticket::getEventDate));
    }

    private void filterByStatusOrDate() {
        int opt = Validation.getInt("1. Filter by sold status\n2. Filter by date range\nSelect option: ", 1, 2);
        if (opt == 1) {
            boolean isSold = Boolean.parseBoolean(Validation.getString("Enter status (true/false): "));
            List<Ticket> filtered = list.filterBy(t -> t.isSold() == isSold);
            list.listAllTickets(new java.util.ArrayList<>(filtered), Comparator.comparing(Ticket::getEventDate));
        } else {
            LocalDate from = Validation.getDate("Enter start date");
            LocalDate to = Validation.getDate("Enter end date");
            List<Ticket> filtered = list.filterBy(t -> !t.getEventDate().isBefore(from) && !t.getEventDate().isAfter(to));
            list.listAllTickets(new java.util.ArrayList<>(filtered), Comparator.comparing(Ticket::getEventDate));
        }
    }
        
        public static void main(String[] args) {
        new TicketsEventManagement().run();
    }
}
