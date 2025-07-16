package view;

import model.Magazine;
import utils.Validation;

import java.util.Date;

public record MagazineListView() {
    public static Magazine inputMagazine() {
        String title = Validation.getString("Enter title: ");
        String publisher = Validation.getString("Enter publisher: ");
        Date pubDate = Validation.getDate("Enter publish date (dd/MM/yyyy): ");
        int issue = Validation.getInt("Enter issue number: ", 1, 9999);
        int pages = Validation.getInt("Enter number of pages: ", 1, 2000);
        return new Magazine(title, publisher, pubDate, issue, pages);
    }

    public static String inputKeyword(String msg) {
        return Validation.getString(msg);
    }

    public static int inputIssueNumber(String msg) {
        return Validation.getInt(msg, 1, 9999);
    }
}
