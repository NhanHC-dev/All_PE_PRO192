package controller;

import model.Candidate;
import model.CandidateList;
import view.Menu;
import view.Validation;

import java.util.ArrayList;
import java.util.Collections;

public class CandidateManagement extends Menu<String> {
    private static String[] options = {
            "Load all candidates from file and display",
            "Add a candidate",
            "Search a candidate by name",
            "Delete a candidate by candidate id",
            "Sort descending the list of candidates by grades",
            "Exit"
    };

    private CandidateList candidateList;

    public CandidateManagement() {
    }

    public CandidateManagement(String title, String[] options, CandidateList candidateList) {
        super(title, options);
        this.candidateList = candidateList;
    }

    @Override
    public void execute(int choice) {
        switch (choice) {
            case 1 -> candidateList.listAllCandidates();
            case 2 -> candidateList.addNewCandidate();
            case 3 -> searchCandidateByName();
            case 4 -> deleteCandidateById();
            case 5 -> sortCandidatesByGrades();
            case 6 -> this.stop();
        }
    }

    private void searchCandidateByName() {
        String candidateName = Validation.getString("Enter Candidate Name: ");
        ArrayList<Candidate> result = candidateList.search(candidate -> candidate.getName().equalsIgnoreCase(candidateName));
        System.out.println("Search Results:");
        for (Candidate candidate : result) {
            System.out.println(candidate);
        }
    }

    private void deleteCandidateById() {
        String candidateID = Validation.getString("Enter Candidate ID: ");
        boolean removed = candidateList.removeIf(candidate -> candidate.getId().equalsIgnoreCase(candidateID));
        if (removed) {
            System.out.println("Candidate with ID " + candidateID + " has been removed.");
        } else {
            System.out.println("No candidate found with ID " + candidateID + ".");
        }
    }

    private void sortCandidatesByGrades() {
        Collections.sort(candidateList, (c1, c2) -> Double.compare(c2.getGrades(), c1.getGrades()));
        System.out.println("Candidates sorted by grades in descending order.");
        candidateList.listAllCandidates();
    }

    public static void main(String[] args) {
        new CandidateManagement("Candidate Management System", options, new CandidateList()).run();
    }
}
