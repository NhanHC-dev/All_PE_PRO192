package model;

import java.text.SimpleDateFormat;
import java.util.Date;

public class Candidate {
    private String id;
    private String name;
    private Date dateOfBirthday;
    private double grades;
    private String email;

    public Candidate(String id, String name, Date dateOfBirthday, double grades, String email) {
        this.id = id;
        this.name = name;
        this.dateOfBirthday = dateOfBirthday;
        this.grades = grades;
        this.email = email;
    }
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Date getDateOfBirthday() {
        return dateOfBirthday;
    }

    public void setDateOfBirthday(Date dateOfBirthday) {
        this.dateOfBirthday = dateOfBirthday;
    }

    public double getGrades() {
        return grades;
    }

    public void setGrades(double grades) {
        this.grades = grades;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    @Override
    public String toString() {
        SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy");
        return String.format("%s %s %s %.2f %s", id, name, sdf.format(dateOfBirthday), grades, email);
    }
}
