package controller;

import model.Student;
import model.StudentList;
import model.comparator.StudentComparatorByDateOfBirth;
import model.comparator.StudentComparatorByGPA;
import model.comparator.StudentComparatorByID;
import model.comparator.StudentComparatorByName;
import view.Menu;
import view.Validation;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

public class StudentManagement extends Menu<String> {
    static String[] menu = {
            "Display all students",
            "Add new student",
            "Search student",
            "Sort students",
            "Edit student information",
            "Statistical amount of students by date of birth",
            "Exit"
    };

    private final StudentList studentList = new StudentList();
    Validation val = new Validation();

    public StudentManagement() {
        super("Student Management System", menu);
    }

    @Override
    public void execute(int choice) {
        switch (choice) {
            case 1 -> studentList.listAllStudents();
            case 2 -> addNewStudent();
            case 3 -> searchStudent();
            case 4 -> sortStudents();
            case 5 -> editStudentInfo();
            case 6 -> listAllAgeOfStudent(studentList);
            case 7 -> System.exit(0);
            default -> System.out.println("Invalid choice!");
        }
    }

    private void addNewStudent() {
        studentList.addNewStudent();
    }

    private void searchStudent() {
        String[] searchOptions = {"Search by ID", "Search by Name", "Return"};
        new Menu<String>("Search Student", searchOptions) {
            @Override
            public void execute(int choice) {
                ArrayList<Student> results = null;
                switch (choice) {
                    case 1 -> {
                        String id = val.getString("Enter student ID: ");
                        //results = studentList.search(s -> s.getId().equalsIgnoreCase(id));
                        studentList.listAllStudents(studentList.search(s -> s.getId().equalsIgnoreCase(id)));
                    }
                    case 2 -> {
                        String name = val.getString("Enter student name: ");
                        //results = studentList.search(s -> s.getName().equalsIgnoreCase(name));
                        studentList.listAllStudents(studentList.search(s -> s.getName().equalsIgnoreCase(name)));
                    }
                    default -> {
                        this.stop();
                        System.out.println("Back to main menu!");
                    }
                }
//                if (results != null && !results.isEmpty()) {
//                    studentList.listAllStudents(results);
//                } else {
//                    System.out.println("No students found.");
//                }
            }
        }.run();
    }

    private void sortStudents() {
        String[] sortOptions = {"Sort by ID", "Sort by Name","Sort by Date of Birth", "Sort by GPA", "Return"};
        new Menu<String>("Sort Students", sortOptions) {
            @Override
            public void execute(int choice) {
                Comparator<Student> comparator = null;
                switch (choice) {
                    case 1 -> comparator = new StudentComparatorByID();
                    case 2 -> comparator = new StudentComparatorByName();
                    case 3 -> comparator = new StudentComparatorByDateOfBirth();
                    case 4 -> comparator = new StudentComparatorByGPA();
                    default -> {
                        this.stop();
                        System.out.println("Back to main menu!");
                    }
                }
                if (comparator != null) studentList.listAllStudents(comparator);
            }
//            @Override
//            public void execute(int choice) {
//                Comparator<Student> comparator = null;
//                switch (choice) {
//                    case 1 -> comparator = Comparator.comparing(Student::getId);
//                    case 2 -> comparator = Comparator.comparing(Student::getName);
//                    case 3 -> comparator = Comparator.comparingDouble(Student::getGPA);
//                    case 4 -> comparator = Comparator.comparing(Student::getDateOfBirth);
//                    default -> {
//                        this.stop();
//                        System.out.println("Back to main menu!");
//                    }
//                }
//                if (comparator != null) studentList.listAllStudents(comparator);
//            }
        }.run();
    }

    private void editStudentInfo() {
        studentList.editInfo();
    }
    private void listAllAgeOfStudent(StudentList ArrayList) {
        ArrayList<ArrayList> studentsByAge = new ArrayList<>();
        for (Student student : studentList) {
            int age = calculateAge(student.getDateOfBirth());
            while (studentsByAge.size() <= age) {
                studentsByAge.add(new ArrayList<>());
            }
            studentsByAge.get(age).add(student);
        }
        System.out.println("Student Information Grouped by Age:");
        for (int age = 0; age < studentsByAge.size(); age++) {
            ArrayList<Student> studentsWithAge = studentsByAge.get(age);
            if (!studentsWithAge.isEmpty()) {
                System.out.println("------------Age: " + age);
                studentsWithAge.forEach(System.out::println);
                System.out.println();
            }
        }
        System.out.println("------------------------------");
    }
//    private void listStudentSameAge() {
//        ArrayList<ArrayList> ageList = new ArrayList<>();
//        for (Student student : studentList) {
//            boolean addedToGroup = false;
//            for (ArrayList<Student> ageGroup : ageList) {
//                if (!ageGroup.isEmpty() && calculateAge(ageGroup.get(0).getDateOfBirth()) == calculateAge(student.getDateOfBirth())) {
//                    ageGroup.add(student);
//                    addedToGroup = true;
//                    break;
//                }
//            }
//            if (!addedToGroup) {
//                ArrayList<Student> newAgeList = new ArrayList<>();
//                newAgeList.add(student);
//                ageList.add(newAgeList);
//            }
//        }
//        Collections.sort(ageList, new Comparator<ArrayList<Student>>() {
//            @Override
//            public int compare(ArrayList<Student> list1, ArrayList<Student> list2) {
//                return Integer.compare(calculateAge(list1.get(0).getDateOfBirth()),calculateAge(list2.get(0).getDateOfBirth()));
//            }
//        });
//        System.out.println("List all students with the same age:");
//        System.out.println("------------------------------------");
//        for (int age = 0; age < ageList.size(); age++) {
//            ArrayList<Student> ageGroup = ageList.get(age);
//            if (!ageGroup.isEmpty()) {
//                System.out.println("------------Age: " + age);
//                ageGroup.forEach(System.out::println);
//                System.out.println();
//            }
//        }
//        System.out.println("------------------------------------");
//    }

    private int calculateAge(LocalDate dob) {
        LocalDate now = LocalDate.now();
        int age = now.getYear() - dob.getYear();
        if (now.getMonthValue() < dob.getMonthValue() ||
                (now.getMonthValue() == dob.getMonthValue() && now.getDayOfMonth() < dob.getDayOfMonth())) {
            age--;
        }
        return age;
    }

    public static void main(String[] args) {
        new StudentManagement().run();
    }
}
