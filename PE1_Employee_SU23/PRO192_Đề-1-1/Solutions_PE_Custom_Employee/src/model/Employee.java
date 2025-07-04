package model;

import view.Validation;
import java.util.Date;

public class Employee {
    private String empId;
    private String account;
    private Date workStartingDate;
    private float productivityScore;
    private float monthlyIncome;
    private float rewardSalary;
    private int allowance;

    public Employee() {
        super();
    }

    public Employee(String empId, String account, Date workStartingDate, float productivityScore, int allowance) {
        this.empId = empId;
        this.account = account;
        this.workStartingDate = workStartingDate;
        this.productivityScore = productivityScore;
        this.allowance = allowance;
    }

    public String getEmpId() {
        return empId;
    }

    public void setEmpId(String empId) {
        this.empId = empId;
    }
    public String getAccount() {
        return account;
    }

    public void setAccount(String account) {
        this.account = account;
    }

    public Date getWorkStartingDate() {
        return workStartingDate;
    }

    public void setWorkStartingDate(Date workStartingDate) {
        this.workStartingDate = workStartingDate;
    }

    public float getProductivityScore() {
        return productivityScore;
    }

    public void setProductivityScore(float productivityScore) {
        this.productivityScore = productivityScore;
    }

    public float getMonthlyIncome() {
        return monthlyIncome;
    }

    public void setMonthlyIncome(float monthlyIncome) {
        this.monthlyIncome = monthlyIncome;
    }

    public float getRewardSalary() {
        return rewardSalary;
    }

    public void setRewardSalary(float rewardSalary) {
        this.rewardSalary = rewardSalary;
    }

    public int getAllowance() {
        return allowance;
    }

    public void setAllowance(int allowance) {
        this.allowance = allowance;
    }

    @Override
    public String toString() {
        return String.format("%-10s | %-10s | %-15s | %-10s | %s", this.getEmpId(), this.getAccount(), new Validation().showDate(this.getWorkStartingDate()), this.getProductivityScore(), this.getMonthlyIncome());
    }
    public float calReward() {
        return this.productivityScore * 3000000;
    }
    public float calMonthlyIncome() {
        return calReward() + this.allowance;
    }
}
