package model;

import java.util.Date;
import java.text.SimpleDateFormat;
public class ComicBook {
    private static int idCounter = 0;
    private int comicBookID;  
    private String title;
    private String author;
    private Date releaseDate;
    private int volume;
    private int pages;
    private String countryOrigin;

    public ComicBook(String title, String author, Date releaseDate, int volume, int pages, String countryOrigin) {
        this.comicBookID = ++idCounter;
        this.title = title;
        this.author = author;
        this.releaseDate = releaseDate;
        this.volume = volume;
        this.pages = pages;
        this.countryOrigin = countryOrigin;
    }

    public int getComicBookID() {
        return comicBookID;
    }

    public void setComicBookID(int comicBookID) {
        this.comicBookID = comicBookID;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public Date getReleaseDate() {
        return releaseDate;
    }

    public void setReleaseDate(Date releaseDate) {
        this.releaseDate = releaseDate;
    }

    public int getVolume() {
        return volume;
    }

    public void setVolume(int volume) {
        this.volume = volume;
    }

    public int getPages() {
        return pages;
    }

    public void setPages(int pages) {
        this.pages = pages;
    }

    public String getCountryOrigin() {
        return countryOrigin;
    }

    public void setCountryOrigin(String countryOrigin) {
        this.countryOrigin = countryOrigin;
    }
    @Override
    public String toString() {
        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
        return String.format("%-5s | %-20s | %-20s | %-7s | %-7s | %-15s | %-15s",
                this.getComicBookID(),
                this.getTitle(),
                this.getAuthor(),
                this.getVolume(),
                this.getPages(),
                sdf.format(this.getReleaseDate()),
                this.getCountryOrigin());
    }
}
