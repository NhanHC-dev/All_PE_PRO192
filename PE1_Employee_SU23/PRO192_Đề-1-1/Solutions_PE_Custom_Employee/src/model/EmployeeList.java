package model;

import view.Validation;

import java.io.*;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.function.Predicate;
import java.util.logging.Level;
import java.util.logging.Logger;

public class EmployeeList extends ArrayList<Employee> {
    Validation val = new Validation();
    public EmployeeList() {
        loadData("src/model/employee.txt");
    }
    public void loadData(String fName){
        String content = "";
        try(FileReader f = new FileReader(fName); BufferedReader br = new BufferedReader(f) ){
            while ((content = br.readLine()) != null){
                String[] b = content.split(",");
                String id = val.checkEmpId(b[0]);
                Date d = val.checkValidDate(b[2]);
                if((id != null) && (d != null)){
                    this.add(new Employee(val.checkEmpId(b[0]),b[1], d, Float.parseFloat(b[3]), Integer.parseInt(b[4])));
                }
            }
        } catch (Exception ex) {
            Logger.getLogger(EmployeeList.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    public ArrayList<Employee> search(Predicate<Employee> p){
        ArrayList<Employee> rs = new ArrayList<>();
        this.forEach(b ->{
            if(p.test(b)) rs.add(b);
        });
        return rs;
    }
    public void sort(Comparator<? super Employee> c) {
        Collections.sort(this, c);
    }
    public static void display(ArrayList<Employee> ls){
        System.out.println("List of Employees\n------------------------");
        ls.forEach(System.out::println);
        System.out.println("-------------------------");
        System.out.println("Total : " + ls.size() + " employees.");
    }
    public void calUpdateMoney(ArrayList<Employee> ls){
        ls.forEach(e ->{
            e.setRewardSalary(e.calReward());
            e.setMonthlyIncome(e.calMonthlyIncome());
        });
        display(ls);
    }
    public void addEmployee(Employee e) {
        this.add(e);
    }

    public void removeEmployee(String empId) {
        this.removeIf(e -> e.getEmpId().equals(empId));
    }

    public void promoteEmployee(String empId) {
        this.forEach(e -> {
            if (e.getEmpId().equals(empId)) {
                e.setMonthlyIncome(e.calMonthlyIncome());
                System.out.println("Old salary: " + e.getAllowance());
                int newSalary;
                do {
                    newSalary = val.getInt("Enter new salary (must be greater than old one)");
                } while (newSalary <= e.getAllowance());
                e.setAllowance(newSalary);
                System.out.println("Salary has been set to " + newSalary);
            }
        });
    }

    public void saveToFile(String fName) {
        if (this.size() == 0) {
            System.out.println("No employee");
            return;
        }
        try (FileWriter fileWriter = new FileWriter(fName);
             PrintWriter printWriter = new PrintWriter(fileWriter)) {
            this.forEach(e -> printWriter.println(e.getEmpId() + "," + e.getAccount() + "," + val.showDate(e.getWorkStartingDate()) + "," + e.getProductivityScore() + "," + e.getAllowance()));
        } catch (Exception ex) {
            Logger.getLogger(EmployeeList.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
