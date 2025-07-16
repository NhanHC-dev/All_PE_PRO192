package model;

import java.io.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.function.Predicate;
import java.util.stream.Collectors;

public class TicketList extends ArrayList<Ticket> {
    private final String filePath = "tickets-input.txt";

    public TicketList() {
        loadFromFile();
    }
    public void loadFromFile() {
        this.clear();
        try (BufferedReader br = new BufferedReader(new FileReader(filePath))) {
            String line;
            while ((line = br.readLine()) != null) {
                String[] parts = line.split(",");
                if (parts.length == 5) {
                    String id = parts[0].trim();
                    String name = parts[1].trim();
                    LocalDate date = LocalDate.parse(parts[2].trim());
                    double price = Double.parseDouble(parts[3].trim());
                    boolean sold = Boolean.parseBoolean(parts[4].trim());
                    this.add(new Ticket(id, name, date, price, sold));
                }
            }
        } catch (IOException e) {
            System.err.println("Could not read file: " + e.getMessage());
        }
    }

    public void saveToFile() {
        try (PrintWriter pw = new PrintWriter(new FileWriter(filePath))) {
            for (Ticket t : this) {
                pw.println(t.toData());
            }
        } catch (IOException e) {
            System.err.println("Error saving file: " + e.getMessage());
        }
    }

    public boolean addTicket(Ticket ticket) {
        if (getTicketByID(ticket.getTicketID()) != null) return false;
        boolean added = this.add(ticket);
        if (added) saveToFile();
        return added;
    }

    public Ticket getTicketByID(String id) {
        for (Ticket t : this) {
            if (t.getTicketID().equalsIgnoreCase(id)) return t;
        }
        return null;
    }

    public boolean updateTicket(String id, double price, boolean sold) {
        Ticket t = getTicketByID(id);
        if (t != null) {
            t.setPrice(price);
            t.setSold(sold);
            saveToFile();
            return true;
        }
        return false;
    }

    public boolean deleteTicket(String id) {
        Ticket t = getTicketByID(id);
        if (t != null) {
            boolean removed = this.remove(t);
            if (removed) saveToFile();
            return removed;
        }
        return false;
    }

    public List<Ticket> searchByEventName(String keyword) {
        return this.stream()
                .filter(t -> t.getEventName().toLowerCase().contains(keyword.toLowerCase()))
                .collect(Collectors.toList());
    }

    public List<Ticket> filterBySoldStatus(boolean isSold) {
        return this.stream()
                .filter(t -> t.isSold() == isSold)
                .collect(Collectors.toList());
    }

    public List<Ticket> filterByDateRange(LocalDate start, LocalDate end) {
        return this.stream()
                .filter(t -> !t.getEventDate().isBefore(start) && !t.getEventDate().isAfter(end))
                .collect(Collectors.toList());
    }
    public List<Ticket> filterBy(Predicate<Ticket> predicate) {
        return this.stream().filter(predicate).collect(Collectors.toList());
    }

    public void listAllTickets() {
        listAllTickets(this, Comparator.comparing(Ticket::getEventDate));
    }

    public void listAllTickets(Comparator<Ticket> comparator) {
        listAllTickets(this, comparator);
    }

    public void listAllTickets(ArrayList<Ticket> list, Comparator<Ticket> comparator) {
        int total = list.size();
        if (total <= 0) {
            System.out.println("Sorry. Nothing to print!");
            return;
        }
        Collections.sort(list, comparator);
        System.out.println("List of all tickets:");
        System.out.println("--------------------------------");
        list.forEach(System.out::println);
        System.out.println("--------------------------------");
        System.out.println("Total: " + total + " tickets");
    }
}

