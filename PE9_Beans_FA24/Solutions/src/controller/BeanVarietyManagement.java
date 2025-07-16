package controller;

import model.BeanVariety;
import model.BeanVarietyList;
import view.BeanVarietyListView;
import view.Menu;

import java.util.Comparator;
import java.util.Date;
import java.util.List;

public class BeanVarietyManagement extends Menu<String>{
    BeanVarietyList listBeans = new BeanVarietyList();

    public BeanVarietyManagement() {
        super("Bean Variety Management", new String[]{
                "Add a new bean variety",
                "Display all bean varieties",
                "Update information of a bean variety",
                "Search bean varieties",
                "Sort bean varieties by yield",
                "Delete bean variety",
                "Exit"
        });
    }
    @Override
    public void execute(int choice) {
        switch (choice) {
            case 1 -> addNewBean();
            case 2 -> listBeans.listAll();
            case 3 -> updateBean();
            case 4 -> searchBeans();
            case 5 -> {
                System.out.println("Sorted by yield!");
                listBeans.listAll(Comparator.comparingDouble(BeanVariety::getYield));
            }
            case 6 -> deleteBean();
            case 7 -> this.stop();
        }
    }

    private void addNewBean() {
        BeanVariety bean = BeanVarietyListView.inputBeanVariety();
        if (listBeans.addBean(bean)) {
            System.out.println("Bean variety added.");
        } else {
            System.out.println("Failed to add bean variety.");
        }
    }

    private void updateBean() {
        String name = BeanVarietyListView.inputKeyword("Enter bean name to update: ");
        BeanVariety b = listBeans.stream().filter(x -> x.getName().equalsIgnoreCase(name)).findFirst().orElse(null);
        if (b == null) {
            System.out.println("Not found.");
            return;
        }
        double newYield = BeanVarietyListView.inputBeanVariety().getYield();
        String newResistance = BeanVarietyListView.inputKeyword("Enter new resistance: ");
        b.setYield(newYield);
        b.setDiseaseResistance(newResistance);
        System.out.println("Updated.");
    }

    private void searchBeans() {
        String[] searchOptions = {
                "Find by name",
                "Find by origin",
                "Find by date",
                "Find by level",
                "Return",
        };
        new Menu<String>("Search bean varieties", searchOptions) {
            @Override
            public void execute(int choice) {
                switch (choice){
                    case 1 -> {
                        String name = BeanVarietyListView.inputKeyword("Enter bean name to search: ");
                        List<BeanVariety> beans = listBeans.search(b -> b.getName().toLowerCase().contains(name.toLowerCase()));
                        listBeans.listAll(beans);
                    }
                    case 2 -> {
                        String origin = BeanVarietyListView.inputKeyword("Enter bean origin to search : ");
                        List<BeanVariety> beans = listBeans.search(b -> b.getOrigin().toLowerCase().contains(origin.toLowerCase()));
                        listBeans.listAll(beans);
                    }
                    case 3 -> {
                        Date date = BeanVarietyListView.inputKeywordDate("Enter bean date: ");
                        List<BeanVariety> beans = listBeans.search(beanVariety -> beanVariety.getHarvestDate().equals(date));
                        // truoc ngay can tim List<BeanVariety> beans = listBeans.search(b -> b.getHarvestDate().before(date));
                        //sau ngay can tim List<BeanVariety> beans = listBeans.search(b -> b.getHarvestDate().after(date));
                        // nam trong khoang tu ngay nao den ngay nao
//                        Date from = BeanVarietyListView.inputKeywordDate("Enter bean from date: ");
//                        Date to = BeanVarietyListView.inputKeywordDate("Enter bean to date: ");
//                        List<BeanVariety> beans = listBeans.search(b ->
//                                !b.getHarvestDate().before(from) && !b.getHarvestDate().after(to));
                        listBeans.listAll(beans);
                    }
                    case 4 -> {
                        String diseaseResistance = BeanVarietyListView.inputKeyword("Enter bean Disease Resistance level to search : ");
                        listBeans.listAll(listBeans.search(b -> b.getDiseaseResistance().toLowerCase().contains(diseaseResistance.toLowerCase())));
                    }
                    case 5 -> this.stop();
                }
            }
        }.run();
    }

    private void deleteBean() {
        String name = BeanVarietyListView.inputKeyword("Enter bean name to delete: ");
        boolean removed = listBeans.delete(b -> b.getName().toLowerCase().contains(name.toLowerCase()));
        if (removed) {
            System.out.println("Deleted successfully.");
        }
    }

    public static void main(String[] args) {
        new BeanVarietyManagement().run();
    }
}
