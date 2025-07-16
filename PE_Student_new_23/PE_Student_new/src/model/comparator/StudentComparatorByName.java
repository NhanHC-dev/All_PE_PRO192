package model.comparator;

import model.Student;

import java.util.Comparator;

public class StudentComparatorByName implements Comparator<Student> {
    @Override
    public int compare(Student o1, Student o2) {
        return o1.getName().compareToIgnoreCase(o2.getName());
    }
}
