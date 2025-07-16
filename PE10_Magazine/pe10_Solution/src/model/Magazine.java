package model;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class Magazine {
    private String title;
    private String publisher;
    private Date publishDate;
    private int issueNumber;
    private int pages;

    public Magazine(String title, String publisher, Date publishDate, int issueNumber, int pages) {
        this.title = title;
        this.publisher = publisher;
        this.publishDate = publishDate;
        this.issueNumber = issueNumber;
        this.pages = pages;
    }

    public String getTitle() { return title; }
    public void setTitle(String title) { this.title = title; }

    public String getPublisher() { return publisher; }
    public void setPublisher(String publisher) { this.publisher = publisher; }

    public Date getPublishDate() { return publishDate; }
    public void setPublishDate(Date publishDate) { this.publishDate = publishDate; }

    public int getIssueNumber() { return issueNumber; }
    public void setIssueNumber(int issueNumber) { this.issueNumber = issueNumber; }

    public int getPages() { return pages; }
    public void setPages(int pages) { this.pages = pages; }

    public int getAge() {
        Calendar cal = Calendar.getInstance();
        cal.setTime(publishDate);
        int yearPublished = cal.get(Calendar.YEAR);
        int currentYear = Calendar.getInstance().get(Calendar.YEAR);
        return currentYear - yearPublished;
    }

    @Override
    public String toString() {
        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
        return String.format("%-25s | %-20s | %-10s | %4d | %3d pages",
                title, publisher, sdf.format(publishDate), issueNumber, pages);
    }
}
