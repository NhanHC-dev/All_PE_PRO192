package model;

import view.Validation;

import java.io.*;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.stream.Collectors;

public class ComicBookList extends ArrayList<ComicBook> {
    public ComicBookList() {
        this.readFile("comic_input.txt");
    }

    public void readFile(String filename) {
        try (BufferedReader br = new BufferedReader(new FileReader(filename))) {
            String line;
            while ((line = br.readLine()) != null) {
                try {
                    String[] parts = line.split(" \\| ");
                    if (parts.length < 6) {
                        System.out.println("Invalid line format: " + line);
                        continue;
                    }
                    Date date;
                    try {
                        date = Validation.checkValidDate(parts[2].trim());
                    } catch (ParseException e) {
                        System.out.println("Invalid date format for: " + parts[2].trim() + ". Skipping this entry.");
                        continue;
                    }
                    ComicBook comicBook = new ComicBook(
                        parts[0].trim(),
                        parts[1].trim(),
                        date,
                        Integer.parseInt(parts[3].trim()),
                        Integer.parseInt(parts[4].trim()),
                        parts[5].trim());
                    this.addComicBook(comicBook);
                } catch (Exception e) {
                    Logger.getLogger(ComicBookList.class.getName()).log(Level.SEVERE, "Error adding list: ", e);
                }
            }
        } catch (IOException e) {
            Logger.getLogger(ComicBookList.class.getName()).log(Level.SEVERE, "Error reading file: ", e);
        }
    }
    public void addComicBook(ComicBook comicBook) {
        this.add(comicBook);
    }
    public void listAllComicBooks() {
        this.listAllComicBooks(this, Comparator.comparing(ComicBook::getComicBookID));
    }

    public void listAllComicBooks(Comparator<ComicBook> comparator) {
        this.listAllComicBooks(this, comparator);
    }

    public void listAllComicBooks(ArrayList<ComicBook> list, Comparator<ComicBook> comparator) {
        int total = list.size();
        if (total <= 0) {
            System.out.println("Sorry. Nothing to print!");
            return;
        }
        list.sort(comparator);
        System.out.println("List of All Comic Books");
        System.out.println("--------------------------------");
        System.out.printf("%-5s | %-20s | %-20s | %-7s | %-7s | %-15s | %-15s\n",
                            "ID", "Title", "Author", "Volume", "Pages", "Release Date", "Country");

        list.forEach(System.out::println);

        System.out.println("--------------------------------");
        System.out.println("Total: " + total + " comic book(s).");
    }
    public void sortByPages() {
        this.listAllComicBooks(Comparator.comparingInt(ComicBook::getPages));
    }

    public ArrayList<ComicBook> searchByTitleOrAuthor(String searchTerm) {
        return this.stream()
                .filter(comic -> comic.getTitle().contains(searchTerm) || comic.getAuthor().contains(searchTerm))
                .collect(Collectors.toCollection(ArrayList::new));
    }

    public void updateComicBook(String title, ComicBook updatedComicBook) {
        for (int i = 0; i < this.size(); i++) {
            if (this.get(i).getTitle().equalsIgnoreCase(title)) {
                this.set(i, updatedComicBook);
                break;
            }
        }
    }

    public void deleteOldComicBooks() {
        Date tenYearsAgo = new Date(System.currentTimeMillis() - (10L * 365 * 24 * 60 * 60 * 1000));
        this.removeIf(comic -> comic.getReleaseDate().before(tenYearsAgo));
    }

    public void exportToFile(String filename) {
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(filename))) {
            for (ComicBook comic : this) {
                bw.write(comic.toString());
                bw.newLine();
            }
            System.out.println("Comic books exported successfully!");
        } catch (IOException e) {
            Logger.getLogger(ComicBookList.class.getName()).log(Level.SEVERE, "Error writing to file: ", e);
        }
    }
}
