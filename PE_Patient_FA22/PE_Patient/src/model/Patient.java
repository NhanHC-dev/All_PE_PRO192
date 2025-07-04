package model;
import java.time.LocalDate;

public class Patient implements Comparable<Patient> {
    private String id;
    private String name;
    private LocalDate dob;
    private String phone;


    public Patient(String id, String name, LocalDate dob, String phone) {
        this.id = id;
        this.name = name;
        this.dob = dob;
        this.phone = phone;
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

    public LocalDate getDob() {
        return dob;
    }

    public void setDob(LocalDate dob) {
        this.dob = dob;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    @Override
    public String toString() {
        return "Patient{" + this.id  + " - "+ this.name+ " - " + this.dob+ " - " + this.phone + '}';
    }


    @Override
    public int compareTo(Patient o) {
        return this.dob.compareTo(o.dob);
    }
}
