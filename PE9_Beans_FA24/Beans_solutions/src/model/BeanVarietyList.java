package model;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.text.ParseException;
import java.util.*;
import java.util.function.Predicate;

public class BeanVarietyList {
    private List<BeanVariety> bean;

    public BeanVarietyList() {
        bean = new ArrayList<>();
    }

    public void readFile(String filename){
        try (BufferedReader br = new BufferedReader(new FileReader(filename))) {
            String line;
            while ((line = br.readLine()) != null) {
                String[] fields = line.split(",");
                if (fields.length == 2) {
                    try {
                        bean.add(new BeanVariety(fields[0].trim(), fields[1].trim()));
                    } catch (Exception e) {
                    }
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    public void listAll() {
        listAll(bean);
    }
    private void listAll(List<BeanVariety> list) {
        int total = list.size();

        if (total <= 0) {
            System.out.println("Sorry. Nothing to print!");
            return;
        }
        Collections.sort(list, Comparator.comparing(BeanVariety::getName));
        System.out.println("List of All Bean Varieties");
        System.out.println("--------------------------------");
        for (BeanVariety variety : list) {
            System.out.println(variety);
        }
        System.out.println("--------------------------------");
        System.out.println("Total: " + total + " bean variety(ies).");
    }

    public List<BeanVariety> getAllBeanVarieties() {
        return bean;
    }

    public void addBeanVariety(BeanVariety beanVariety) {
        bean.add(beanVariety);
    }
    public boolean delete(Predicate<BeanVariety> predicate) {
        boolean removed = bean.removeIf(predicate);
        if (!removed) {
            System.err.println("No matching bean variety found to delete.");
        }
        return removed;
    }
    public void sortByName() {
        bean.sort(Comparator.comparing(BeanVariety::getName));
        bean.sort(((o1, o2) -> o1.getName().compareTo(o2.getName())));
    }
    public List<BeanVariety> search(Predicate<BeanVariety> predicate) {
        List<BeanVariety> resultList = new ArrayList<>();

        for (BeanVariety variety : bean) {
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
