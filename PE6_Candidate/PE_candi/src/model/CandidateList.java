package model;

import view.Validation;

import java.io.*;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.function.Predicate;

public final class CandidateList extends ArrayList<Candidate> {

    public CandidateList() {
        super();
        this.loadDataFromFile();
    }

    public void listAllCandidates() {
        this.listAllCandidates(this);
    }

    public void listAllCandidates(ArrayList<Candidate> list) {
        int total = list.size();

        if (total <= 0) {
            System.err.println("Sorry. Nothing to print.");
            return;
        }

        list.sort(Comparator.comparing(Candidate::getGrades).reversed());

        System.out.println("List all candidates");
        System.out.println("--------------------------------");

        for (Candidate candidate : list) {
            System.out.println(candidate);
        }

        System.out.println("--------------------------------");
        System.out.println("Total: " + total + " candidates.");
    }

    public void addNewCandidate() {
        System.out.println("Add new candidate");
        System.out.println("--------------------------------");
        String newCandidateID;
        String newName;
        Date newDateOfBirth = null;
        double newGrades;
        String newEmail;

        while (true) {
            newCandidateID = Validation.getString("Enter Candidate ID: ");
            if (!this.isCandidateIDDuplicated(newCandidateID)) {
                break;
            }
            System.out.println("Candidate ID already exists. Please enter a different ID.");
        }

        newName = Validation.getString("Enter Candidate Name: ");
        newGrades = Validation.getDouble("Enter Grades: ", 0, 10);

        while (true) {
            String dateStr = Validation.getString("Enter Date of Birth (dd-MM-yyyy): ");
            try {
                newDateOfBirth = Validation.checkValidDate(dateStr);
                break;
            } catch (ParseException e) {
                System.out.println("Invalid date format. Please try again.");
            }
        }

        newEmail = Validation.getString("Enter Email: ");

        this.add(new Candidate(newCandidateID, newName, newDateOfBirth, newGrades, newEmail));
    }

    private boolean isCandidateIDDuplicated(String candidateID) {
        for (Candidate candidate : this) {
            if (candidate.getId().equalsIgnoreCase(candidateID)) {
                return true;
            }
        }
        return false;
    }

    private void loadDataFromFile() {
        File file = new File("candidates_input.txt");

        try (BufferedReader br = new BufferedReader(new FileReader(file))) {
            String line;
            SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy");
            while ((line = br.readLine()) != null) {
                String[] parts = line.split(",");
                String id = parts[0];
                String name = parts[1];
                Date dateOfBirth = sdf.parse(parts[2]);
                double grades = Double.parseDouble(parts[3]);
                String email = parts[4];
                this.add(new Candidate(id, name, dateOfBirth, grades, email));
            }
        } catch (FileNotFoundException e) {
            System.err.println("File not found: " + file.getName());
        } catch (IOException | ParseException e) {
            e.printStackTrace();
        }
    }

    public ArrayList<Candidate> search(Predicate<Candidate> predicate) {
        ArrayList<Candidate> list = new ArrayList<>();

        for (Candidate candidate : this) {
            if (predicate.test(candidate)) {
                list.add(candidate);
            }
        }
        if (list.isEmpty()) {
            System.err.println("Cannot find any matching candidates.");
        }

        return list;
    }

    public Candidate getLargestAmountBill() {
        return Collections.max(this, Comparator.comparingDouble(Candidate::getGrades));
    }
}
