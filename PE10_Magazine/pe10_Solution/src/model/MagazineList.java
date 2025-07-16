package model;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.Date;
import java.util.List;
import java.util.function.Predicate;

public class MagazineList extends ArrayList<Magazine> {
    public MagazineList() {
        super();
        loadFromFile("magazines.txt");
    }
    public void loadFromFile(String filename) {
        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
        try (BufferedReader br = new BufferedReader(new FileReader(filename))) {
            String line;
            while ((line = br.readLine()) != null) {
                try {
                    String[] parts = line.split("\\|");
                    if (parts.length != 5) continue;
                    String title = parts[0].trim();
                    String publisher = parts[1].trim();
                    Date date = sdf.parse(parts[2].trim());
                    int issue = Integer.parseInt(parts[3].trim());
                    int pages = Integer.parseInt(parts[4].trim());
                    this.add(new Magazine(title, publisher, date, issue, pages));
                } catch (Exception e) {
                    // skip invalid lines
                }
            }
        } catch (IOException e) {
            System.err.println("Failed to read file: " + e.getMessage());
        }
    }
    public boolean addMagazine(Magazine m) {
        return this.add(m);
    }

    public void sortByPages() {
        this.listAll(Comparator.comparingInt(Magazine::getPages));
    }

    public List<Magazine> search(Predicate<Magazine> condition) {
        List<Magazine> results = new ArrayList<>();
        for (Magazine m : this) {
            if (condition.test(m)) results.add(m);
        }
        return results;
    }

    public void deleteOldMagazines(int minAge) {
        this.removeIf(m -> m.getAge() >= minAge);
    }

    public void listAll() {
        listAll(this, null);
    }

    public void listAll(List<Magazine> list) {
        listAll(list, null);
    }

    public void listAll(Comparator<Magazine> comparator) {
        listAll(this, comparator);
    }

    public void listAll(List<Magazine> list, Comparator<Magazine> comparator) {
        int total = list.size();
        if (total <= 0) {
            System.out.println("Sorry. Nothing to print!");
            return;
        }
        if (comparator != null) {
            list.sort(comparator);
        }
        System.out.println("List all magazines:");
        System.out.println("---------------------------------------------------------------------------------");
        System.out.printf("%-25s | %-20s | %-10s | %-6s | %-5s\n", "Title", "Publisher", "Date", "Issue", "Pages");
        System.out.println("---------------------------------------------------------------------------------");
        list.forEach(System.out::println);
        System.out.println("---------------------------------------------------------------------------------");
        System.out.println("Total: " + total + " magazines.");
    }
}
