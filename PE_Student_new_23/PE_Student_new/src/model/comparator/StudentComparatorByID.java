package model.comparator;

import model.Student;

import java.util.Comparator;

public class StudentComparatorByID implements Comparator<Student> {
    @Override
    public int compare(Student o1, Student o2) {
        return o1.getId().compareToIgnoreCase(o2.getId());
    }
}
