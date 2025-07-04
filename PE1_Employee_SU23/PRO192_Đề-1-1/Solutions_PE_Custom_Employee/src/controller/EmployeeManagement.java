package controller;

import model.Employee;
import model.EmployeeList;
import view.Menu;
import view.Validation;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;

public class EmployeeManagement extends Menu<String> {
    static String[] menu = {
            "List all employee",
            "Calculate money",
            "Add new employee",
            "Remove by empId",
            "Promoting by empId",
            "Sorting employee",
            "Search Employee",
            "Save to file",
            "Exit"
    };
    private final EmployeeList empList = new EmployeeList();
    Validation val = new Validation();
    public EmployeeManagement() {
        super("Employee Management System", menu);
    }
    @Override
    public void execute(int choice) {
        switch (choice){
            case 1 -> EmployeeList.display(empList);
            case 2 -> empList.calUpdateMoney(empList);
            case 3 -> {
                try {
                    addNewEmployee();
                } catch (Exception ex) {
                    Logger.getLogger(EmployeeList.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
            case 4 -> removeEmployeeById();
            case 5 -> promoteEmployeeById();
            case 6 -> sortEmployees();
            case 7 -> searchEmp();
            case 8 -> saveToFile("src/model/employee.txt");
            case 9 -> System.exit(0);
            default ->{
                this.stop();
                System.out.println("Invalid!");
            }
        }
    }
    private void addNewEmployee() throws Exception {
        String empId = val.checkEmpId(val.getString("Enter employee ID:"));
        String account = val.getString("Enter employee account:");
        Date workStartingDate = val.checkValidDate(val.getString("Enter work starting date (dd/MM/yyyy):"));
        float productivityScore = Float.parseFloat(val.getString("Enter productivity score:"));
        int allowance = Integer.parseInt(val.getString("Enter allowance:"));
        Employee newEmployee = new Employee(empId, account, workStartingDate, productivityScore, allowance);
        empList.addEmployee(newEmployee);
        System.out.println("Employee added successfully.");
    }
    private void removeEmployeeById() {
        String empId = val.getString("Enter employee ID to remove:");
        empList.removeEmployee(empId);
        System.out.println("Employee removed successfully.");
    }

    private void promoteEmployeeById() {
        String empId = val.getString("Enter employee ID to promote:");
        empList.promoteEmployee(empId);
        System.out.println("Employee promoted successfully.");
    }
    private void sortEmployees() {
        String[] sortOptions = {
                "Sort by Productivity Score",
                "Sort by Monthly Income",
                "Return"
        };
        new Menu("Sort Employees", sortOptions) {
            @Override
            public void execute(int choice) {
                switch (choice) {
                    case 1 -> empList.sort(Comparator.comparing(Employee::getProductivityScore));
                    case 2 -> empList.sort(Comparator.comparing(Employee::getMonthlyIncome));
                    default -> {
                        this.stop();
                        System.out.println("Invalid!");
                    }
                }
                EmployeeList.display(empList);
            }
        }.run();
    }
    private void searchEmp(){
        String[] mSearch = {"Find by Account", "Find by workStartingDate", "Return"};
        new Menu("Employee Searching", mSearch) {
            @Override
            public void execute(int choice) {
                ArrayList<Employee> rs = null;
                switch (choice){
                    case 1 ->{
                        String input1 = val.getString("Enter employee account :");
                        rs = empList.search(b -> b.getAccount().equals(input1));
                    }
                    case 2 ->{
                        Date input2 = val.checkValidDate(val.getString("Enter workStartingDate  :"));
                        if(input2 != null){
                            rs = empList.search(b -> b.getWorkStartingDate().before(input2));
                        }
                    }
                    default -> {
                        this.stop();
                        System.out.println("Invalid!");
                    }
                }
                if(rs!=null)
                    EmployeeList.display(rs);
            }
        }.run();
    }
    private void saveToFile(String fname) {
        empList.saveToFile(fname);
        System.out.println("Employee data saved successfully.");
    }
    public static void main(String[] args) {
        new EmployeeManagement().run();
    }
}
