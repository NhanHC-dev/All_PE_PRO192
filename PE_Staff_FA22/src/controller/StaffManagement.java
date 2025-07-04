
package controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;
import model.Staff;
import model.StaffList;
import view_controller.Menu;

public class StaffManagement {
    
    StaffList sl = new StaffList();
    ArrayList<Staff> list;
    Scanner sc = new Scanner(System.in);
    
    public StaffManagement() throws IOException {
        this.list = sl.readData();
    }
    
    public void Menu(){
        String[] mc = {"Display all staff", "Search staff", "Login", "Change password", "Exit"};
        Menu<Object> menu = new Menu<Object>("STAFF MANAGEMENT", mc) {
        @Override
        public void execute(int n) {            
            if(n==1){
                displayAllStaff();
            }
            if(n==2){
                changePassStaff();
            }
            if(n==3){
                remove();
            }
        }
    };
        int n = menu.getSelected();
        menu.run();
        menu.execute(n);
    }

    
    public void displayAllStaff(){
        System.out.println("List staff: ");
        for (Staff staff : list) {
            System.out.println(staff.toString());
        }
    }
    
    public void remove(){
        System.out.println("Enter key to delete: ");
        String str = sc.nextLine();
        sl.remove(str);
        System.out.println("After delete: ");
        displayAllStaff();
    }
    
    public void changePassStaff(){
        System.out.println("Enter your ID: ");
        String str = sc.nextLine();
        Staff s = null;
        for (Staff staff : list) {
            if(staff.getStaffID().equals(str)){
                s = staff;
            }
        }
        if(s == null){
            System.out.println("No Staff match!");
        } else{
            System.out.println("Enter old pass");
            String oldPass = sc.nextLine();
            System.out.println("Enter new pass");
            String newPass = sc.nextLine();
            if(s.changePassword(oldPass, newPass)){
                System.out.println("Success");
            }else{
                System.out.println("Wrong password");
            }
        }
    }

    public static void main(String[] args) throws IOException {
        StaffManagement s = new StaffManagement();
        s.Menu();
    }
}