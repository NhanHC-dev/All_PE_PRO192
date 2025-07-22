package controller;

import model.ComicBook;
import model.ComicBookList;
import view.ComicListView;
import view.Validation;

import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.text.ParseException;
import java.util.Comparator;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;

public class ComicBookManagement extends ComicListView<String> {
    private ComicBookList comicBooks;

    public ComicBookManagement() {
        super("====== Comic Book Management ======", new String[]{
                "Display all comic books",
                "Add Comic Book",
                "Search comic book by name",
                "Update Comic Book",
                "Display sorted comic books by the number of pages",
                "Delete comic books",
                "Display comic books by country",
                "Export to new txt file",
                "Exit",
        });
        this.comicBooks = new ComicBookList();
    }

    @Override
    public void execute(int choice) {
        switch (choice) {
            case 1 -> printAll();
            case 2 -> add();
            case 3 -> search();
            case 4 -> update();
            case 5 -> displaySortedByPages();
            case 6 -> delete();
            case 7 -> comicBooks.groupByCountryOrigin();
            case 8 -> export();
            case 9 -> {
                this.stop();
                System.out.println("Exiting Comic Book Management System...");
            }
            default -> System.out.println("Invalid choice! Please try again.");
        }
    }
    private void printAll(){
        comicBooks.listAllComicBooks();
    }
    private void add() {
        System.out.println("Enter comic book details:");
        ComicBook newComicBook = new ComicBook(Validation.getString("Title: "),
                Validation.getString("Author: "),
                Validation.inputDate("Release Date (DD/MM/YYYY): "),
                Validation.getInt("Number of Pages: ", 1, Integer.MAX_VALUE),
                Validation.getInt("volume: ", 0, Integer.MAX_VALUE),
                Validation.getString("countryOrigin: "));
        comicBooks.addComicBook(newComicBook);
        System.out.println("Comic Book added successfully!");
    }

    private void search() {
        String query = Validation.getString("Enter comic book title or author to search: ");
        comicBooks.searchByTitleOrAuthor(query);
//        for (ComicBook comic : comicBooks) {
//            if (comic.getTitle().contains(query) || comic.getAuthor().contains(query)) {
//                System.out.println(comic);
//            }
//        }
    }

    private void update() {
        boolean check = false;
        String title = Validation.getString("Enter the title of the comic book to update: ");
        for (ComicBook comic : comicBooks) {
            if (comic.getTitle().equalsIgnoreCase(title)) {
//                comic.setAuthor(Validation.getString("New Author (leave blank to keep current): "));
//                comic.setReleaseDate(Validation.inputDate("New Release Date (DD/MM/YYYY, leave blank to keep current): "));
//                comic.setVolume(Validation.getInt("New Volume(leave blank to keep current): "));
//                comic.setPages(Validation.getInt("New Number of Pages (leave blank to keep current): "));
//                comic.setCountryOrigin(Validation.getString("New Country of Origin (leave blank to keep current): "));

                comicBooks.updateComicBook(title, new ComicBook(
                        Validation.getString("Title: "),
                        Validation.getString("Author: "),
                        Validation.inputDate("Release Date (DD/MM/YYYY): "),
                        Validation.getInt("Number of Pages: ", 1, Integer.MAX_VALUE),
                        Validation.getInt("Volume: ", 0, Integer.MAX_VALUE),
                        Validation.getString("Country of Origin: ")));
                check = true;
                return;
            }
        }
        System.out.println(check? "Comic Book updated successfully!": "Comic Book not found!");
    }

    private void displaySortedByPages() {
        comicBooks.sortByPages();
    }

    private void delete() {
        comicBooks.deleteOldComicBooks();
        System.out.println("Comic books older than 10 years have been deleted.");
    }


    private void export() {
        String filename = Validation.getString("Enter the filename to export to: ");
        comicBooks.exportToFile(filename);
//        try (PrintWriter writer = new PrintWriter(new FileWriter(filename))) {
//            for (ComicBook comic : comicBooks) {
//                writer.println(comic);
//            }
//            System.out.println("Comic books exported successfully!");
//        } catch (IOException e) {
//            Logger.getLogger(ComicBookManagement.class.getName()).log(Level.SEVERE, "Error exporting comic books: ", e.getMessage());
//        }
    }

    public static void main(String[] args) {
        new ComicBookManagement().run();
    }
}
