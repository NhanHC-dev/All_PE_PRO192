package controller;
import java.util.ArrayList;
import java.util.Date;
import java.util.Scanner;
import model.EmployeeList;
import model.Employee;
import view.Menu;
import view.Validation;

public class EmpManagement extends Menu<String> {

    static String[] menu = {"List all employee", "Caculate money", "Sorting employee", "Search Employee", "Exit"};
    private EmployeeList empList = new EmployeeList();
    Validation val = new Validation();

    public EmpManagement() {
        super("Employee Management System", menu);
    }
//--------------------------------------------------

    @Override
    public void execute(int n) {
        switch (n) {
            case 1:
                EmployeeList.display(empList.getEmployeeList());
                break;
            case 2:
                empList.calUpdateMoney(empList.getEmployeeList());
                break;
            case 3:
                empList.sort((o1, o2) -> Float.compare(o1.getMonthlyIncome(), o2.getMonthlyIncome()));
                empList.display(empList.getEmployeeList());
                break;
            case 4:
                searchEmp();
                break;
            case 5:
                System.exit(0);
        }
    }
//--------------------------------------------------

    private String getValue(String msg) {
        Scanner sc = new Scanner(System.in);
        System.out.print(msg);
        return sc.nextLine();
    }
//--------------------------------------------------

    private void searchEmp() {
        String[] mSearch = {"Find by Account", "Find by workStartingDate"};
        Menu m = new Menu("Employee Searching", mSearch) {
            public void execute(int n) {
                ArrayList<Employee> rs = null;
                switch (n) {
                    case 1:
                        String input1 = getValue("Enter employee account :");
                        rs = empList.search(b -> b.getAccount().equals(input1));
                        break;
                    case 2:
                        Date input2 = val.checkValidDate(getValue("Enter workStartingDate  :"));
                        
                        if(input2!=null)
                        rs = empList.search(b -> b.getWorkStartingDate().before(input2));
                        break;
                    default:
                        return;
                }
               if(rs!=null)
                EmployeeList.display(rs);

            }

        };
        m.run();
    }

    public static void main(String[] args) {
        EmpManagement mgnObj = new EmpManagement();
        mgnObj.run();
    }

}
