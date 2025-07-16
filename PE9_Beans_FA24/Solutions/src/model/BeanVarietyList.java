package model;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.function.Predicate;

public class BeanVarietyList extends ArrayList<BeanVariety> {
    private String filePath = "bean_varieties.csv";

    public BeanVarietyList() {
        loadFromFile();
    }

    public void loadFromFile() {
        try (BufferedReader br = new BufferedReader(new FileReader(filePath))) {
            String line;
            while ((line = br.readLine()) != null) {
                try {
                    String[] parts = line.split(",");
                    if (parts.length != 5) continue;
                    String name = parts[0].trim();
                    String origin = parts[1].trim();
                    Date date = new SimpleDateFormat("dd/MM/yyyy").parse(parts[2].trim());
                    double yield = Double.parseDouble(parts[3].trim());
                    String resistance = parts[4].trim();
                    this.add(new BeanVariety(name, origin, date, yield, resistance));
                } catch (Exception ignored) {}
            }
        } catch (IOException e) {
            System.err.println("Failed to read file: " + e.getMessage());
        }
    }

    public boolean addBean(BeanVariety b) {
        return this.add(b);
    }

    public void listAll() {
        listAll(this,Comparator.comparing(BeanVariety::getName));
    }
    public void listAll(List<BeanVariety> beans) {
        listAll(beans, null);
    }
    public void listAll(Comparator<BeanVariety> comparator) {
        listAll(this, comparator);
    }

    private void listAll(List<BeanVariety> list, Comparator<BeanVariety> comparator) {
        int total = list.size();

        if (total <= 0) {
            System.out.println("Sorry. Nothing to print!");
            return;
        }
        if (comparator != null) {
            Collections.sort(list, comparator);
        }
        System.out.println("List of All Bean Varieties");
        System.out.println("---------------------------------------------------------------");
        list.forEach(System.out::println);
        System.out.println("---------------------------------------------------------------");
        System.out.println("Total: " + total + " bean variety(ies).");
    }


    public boolean delete(Predicate<BeanVariety> predicate) {
        boolean removed = this.removeIf(predicate);
        if (!removed) {
            System.err.println("No matching bean variety found to delete.");
        }
        return removed;
    }
    public List<BeanVariety> search(Predicate<BeanVariety> predicate) {
        List<BeanVariety> resultList = new ArrayList<>();
        for (BeanVariety variety : this) {
            if (predicate.test(variety)) {
                resultList.add(variety);
            }
        }
        if (resultList.isEmpty()) {
            System.err.println("No matching bean varieties found.");
        }
        return resultList;
    }
}
