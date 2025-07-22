package model;

import view.Validation;

import java.io.*;
import java.util.*;
import java.util.function.Predicate;

public class VaccineList extends ArrayList<Vaccine> {
    private final String fileName = "vaccines_input.txt";
    Validation val = new Validation();
    public VaccineList() {
        super();
        loadDataFromFile("vaccines_input.txt");
    }
    public void loadDataFromFile(String fileName) {
        File file = new File(fileName);
        if (!file.exists()) {
            System.err.println("File " + fileName + " does not found");
            return;
        }
        try(BufferedReader br = new BufferedReader(new FileReader(file))) {
            String line;
            int lineCount = 0;
            while ((line = br.readLine()) != null) {
                lineCount++;
                try{
                    String[] parts = line.split(",");
                    if (parts.length != 6) {
                        System.err.println("Line " + lineCount + " skipped: wrong number of fields.");
                        continue;
                    }
                    String code = parts[0].split("=")[1].trim();
                    String name = parts[1].split("=")[1].trim();
                    int quantity = Integer.parseInt(parts[2].split("=")[1].trim());
                    Date exDate = Validation.checkValidDate(parts[3].split("=")[1].trim());
                    double price = Double.parseDouble(parts[4].split("=")[1].trim());
                    Date lastInjDate = Validation.checkValidDate((parts[5].split("=")[1].trim()));
                    this.add(new Vaccine(code, name, quantity, exDate, price, lastInjDate));
                }catch (Exception e) {
                    System.err.println("Line " + lineCount + " skipped due to parsing error.");
                }
            }
            System.out.println("Load complete. " + this.size() + " vaccine(s) loaded.");
        }  catch (IOException e) {
            System.err.println("Cannot read file: " + e.getMessage());
        }
    }
    public void listAll() {
        listAll(this, null);
    }

    public void listAll(List<Vaccine> list) {
        listAll(list, null);
    }

    public void listAll(Comparator<Vaccine> comparator) {
        listAll(this, comparator);
    }
    public void listAll(List<Vaccine> list, Comparator<Vaccine> comparator){
     int total = list.size();
        if (total <= 0) {
            System.out.println(" Nothing to print!");
            return;
        }
        if (comparator != null) {
            list.sort(comparator);
        }
        System.out.println("List Vaccines:");
        System.out.println("--------------------------------");
        list.forEach(System.out::print);
        System.out.println("--------------------------------");
        System.out.println("Total: " + total + " vaccines.");
    }
    public void addVaccine(Vaccine e) {
        this.add(e);
    }

    public void delete(Predicate<Vaccine> predicate) {
        this.removeIf(predicate);
    }

//    public void sort() {
//        this.sort((s1, s2) -> s1.getName().toLowerCase().compareTo(s2.getName().toLowerCase()));
//    }

    public void saveToFile(String fName) {
        if (this.size() == 0) {
            System.out.println("No Vaccine");
            return;
        }
        try (FileWriter fileWriter = new FileWriter(fName);
             PrintWriter printWriter = new PrintWriter(fileWriter)) {
            this.forEach(e -> printWriter.printf("code=%s, name=%s, quantity=%d, expirationDate=%s, price=%.2f, lastInjectedDate=%s\n",
                    e.getCode(), e.getName(), e.getQuantity(), val.showDate(e.getExpirationDate()), e.getPrice(), val.showDate(e.getLastInjectedDate())));
            System.out.println("Vaccines saved to file.");
        } catch (IOException e) {
            System.out.println("Error saving vaccines: " + e.getMessage());
        }
    }

//    public void listExpiredVaccines() {
//        Date today = new Date();
//        this.stream().filter(v -> v.getExpirationDate().before(today)).forEach(System.out::println);
//    }
//
//    public void listRecentInjectedVaccines() {
//        Calendar cal = Calendar.getInstance();
//        cal.add(Calendar.YEAR, -1);
//        Date oneYearAgo = cal.getTime();
//        this.stream().filter(v -> v.getLastInjectedDate().after(oneYearAgo)).forEach(System.out::println);
//    }
public List<Vaccine> getExpiredVaccines() {
    Date today = new Date();
    return this.stream()
            .filter(v -> v.getExpirationDate().before(today))
            .toList();
}

    public List<Vaccine> getRecentlyInjectedVaccines() {
        Calendar cal = Calendar.getInstance();
        cal.add(Calendar.YEAR, -1);
        Date oneYearAgo = cal.getTime();
        Date today = new Date();

        return this.stream()
                .filter(v -> v.getLastInjectedDate() != null &&
                        v.getLastInjectedDate().after(oneYearAgo) &&
                        v.getLastInjectedDate().before(today))
                .toList();
    }


}
