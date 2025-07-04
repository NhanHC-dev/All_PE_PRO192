package model;

import java.sql.SQLOutput;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class Student {
    private String id;
    private String name;
    private LocalDate dateOfBirth;
    private float GPA;

    public Student() {
        super();
    }

    public Student(String id, String name, LocalDate dateOfBirth, float GPA) {
        this.id = id;
        this.name = name;
        this.dateOfBirth = dateOfBirth;
        this.GPA = GPA;
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

    public LocalDate getDateOfBirth() {
        return dateOfBirth;
    }

    public void setDateOfBirth(LocalDate dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
    }

    public float getGPA() {
        return GPA;
    }

    public void setGPA(float GPA) {
        this.GPA = GPA;
    }

    @Override
    public String toString() {
        DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        return "%-8s | %-15s | %-15s | %-5s".formatted(this.getId(), this.getName(), dateTimeFormatter.format(this.getDateOfBirth()), this.getGPA());
    }
}
