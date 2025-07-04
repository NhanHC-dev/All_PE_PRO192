package model;

public class Staff {
    private String fullName,department,password,staffID;

    public Staff(String fullName, String department, String password, String staffID) {
        this.fullName = fullName;
        this.department = department;
        this.password = password;
        this.staffID = staffID;
    }

    Staff() {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public String getDepartment() {
        return department;
    }

    public void setDepartment(String department) {
        this.department = department;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getStaffID() {
        return staffID;
    }

    public void setStaffID(String staffID) {
        this.staffID = staffID;
    }

    @Override
    public String toString() {
        return "Staff{" + "fullName=" + fullName + ", department=" + department + ", password=" + password + ", staffID=" + staffID + '}';
    }

    public boolean changePassword(String oldPass, String newPass){
        if(oldPass.equals(getPassword())){
            setPassword(newPass);
            return true;
        }
        return false;
    }
    
    
}
