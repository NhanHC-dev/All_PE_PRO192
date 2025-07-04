package model;
import java.io.BufferedReader;
import java.io.FileReader;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.function.Predicate;
import java.util.logging.Level;
import java.util.logging.Logger;
import view.Validation;
public class EmployeeList {
    private ArrayList<Employee> EmpList;
    String path;
    Validation val = new Validation();

    public EmployeeList() {
        EmpList = new ArrayList<>();
        path = Paths.get("").toAbsolutePath().toString();
        loadData(path + "/src/model/employee.txt");
    }

//------------------------------------------------
    public void loadData(String fName) {
        String emp = "";
        try ( FileReader f = new FileReader(fName);  BufferedReader br = new BufferedReader(f)) {
            while ((emp = br.readLine()) != null) {
                String[] b = emp.split(",");
                String id = val.checkEmpId(b[0]);
                Date d = val.checkValidDate(b[2]);

                if ((val.checkEmpId(b[0]) != null) && (d != null)) {
                    EmpList.add(new Employee(val.checkEmpId(b[0]), b[1], d, Float.parseFloat(b[3]), Integer.parseInt(b[4])));
                }
            }
        } catch (Exception ex) {
            Logger.getLogger(EmployeeList.class.getName()).log(Level.SEVERE, null, ex);
        }

    }
//------------------------------------------------

    public ArrayList<Employee> search(Predicate<Employee> p) {

        ArrayList<Employee> rs = new ArrayList<>();
        for (Employee b : EmpList) {
            if (p.test(b)) {
                rs.add(b);
            }
        }
        return rs;
    }
//------------------------------------------------

    public void sort(Comparator<Employee> c) {
        Collections.sort(EmpList, c);
    }
//------------------------------------------------    

    public ArrayList<Employee> getEmployeeList() {
        return EmpList;
    }
//------------------------------------------------

    public void setEmployeeList(ArrayList<Employee> EmpList) {
        this.EmpList = EmpList;
    }
//------------------------------------------------

    public static void display(ArrayList<Employee> ls) {
        System.out.println("List of Employees\n------------------------");

        for (Employee b : ls) {
            System.out.println(b);
        }
        System.out.println("-------------------------");
        System.out.println("Total : " + ls.size() + " employees.");
    }
    //------------------------------------------------

    public void calUpdateMoney(ArrayList<Employee> ls) {
        for (Employee e : ls) {
            e.setRewardSalary(e.calReward());
            e.setMonthlyIncome(e.calMonthlyIncome());
        }
        display(ls);
    }
}
