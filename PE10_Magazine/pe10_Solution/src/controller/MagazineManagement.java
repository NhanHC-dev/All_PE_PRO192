package controller;

import model.Magazine;
import model.MagazineList;
import view.MagazineListView;
import view.Menu;
import java.util.List;

public class MagazineManagement extends Menu<String> {
    private final MagazineList magazines;

    public MagazineManagement() {
        super("MAGAZINE MANAGEMENT SYSTEM", new String[]{
                "Add a new magazine",
                "Display all magazines",
                "Display sorted magazines by pages",
                "Search magazines",
                "Delete old magazines (>= 5 years)",
                "Exit"
        });
        magazines = new MagazineList();
    }

    @Override
    public void execute(int choice) {
        switch (choice) {
            case 1 -> {
                Magazine m = MagazineListView.inputMagazine();
                magazines.addMagazine(m);
                System.out.println("Magazine added.");
            }
            case 2 -> magazines.listAll();
            case 3 -> magazines.sortByPages();
            case 4 -> searchMagazines();
            case 5 -> {
                magazines.deleteOldMagazines(5);
                System.out.println("Deleted magazines older than 5 years.");
            }
            case 6 -> this.stop();
        }
    }

    private void searchMagazines() {
        String[] searchOptions = {"Find by title", "Find by publisher", "Find by issue number", "Return"};
        new Menu<String>("Search Magazine", searchOptions) {
            @Override
            public void execute(int choice) {
                switch (choice) {
                    case 1 -> {
                        String kw = MagazineListView.inputKeyword("Enter title keyword: ");
                        List<Magazine> r = magazines.search(m -> m.getTitle().toLowerCase().contains(kw.toLowerCase()));
                        magazines.listAll(r);
                    }
                    case 2 -> {
                        String kw = MagazineListView.inputKeyword("Enter publisher: ");
                        List<Magazine> r = magazines.search(m -> m.getPublisher().equalsIgnoreCase(kw));
                        magazines.listAll(r);
                    }
                    case 3 -> {
                        int issue = MagazineListView.inputIssueNumber("Enter issue number: ");
                        List<Magazine> r = magazines.search(m -> m.getIssueNumber() == issue);
                        magazines.listAll(r);
                    }
                    case 4 -> this.stop();
                }
            }
        }.run();
    }

    public static void main(String[] args) {
        new MagazineManagement().run();
    }
}