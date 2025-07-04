package model;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

public class StaffList {
    ArrayList<Staff> list = new ArrayList<>();

    public ArrayList<Staff> readData() throws FileNotFoundException, IOException {
        String url = "staff.txt";
        // Đọc dữ liệu từ File với Scanner
        FileInputStream fileInputStream = new FileInputStream(url);
        Scanner scanner = new Scanner(fileInputStream);
        try {
            while (scanner.hasNextLine()) {
                String line = scanner.nextLine();
                String[] line_list = line.split(":");
                Staff s = new Staff();
                s.setStaffID(line_list[0]);
                s.setFullName(line_list[1]);
                s.setDepartment(line_list[2]);
                s.setPassword(line_list[4]);
                list.add(s);
            }
        } finally {
            try {
                scanner.close();
                fileInputStream.close();
            } catch (IOException ex) {
                System.out.println(ex.toString());
            }
        }
        return list;
    }
    
    public ArrayList<Staff> searchStaff(String str){
        ArrayList<Staff> subList = new ArrayList<>();
        for (Staff staff : list) {
            if(staff.getStaffID().contains(str) || staff.getPassword().contains(str)||staff.getFullName().contains(str)||staff.getDepartment().contains(str)){
                subList.add(staff);
            }
        }
        return subList;
    }
    
    public void remove(String str){
        for (Staff staff : list) {
            if(staff.getStaffID().contains(str) || staff.getPassword().contains(str)||staff.getFullName().contains(str)||staff.getDepartment().contains(str)){
                list.remove(staff);
            }
        }
    }
}