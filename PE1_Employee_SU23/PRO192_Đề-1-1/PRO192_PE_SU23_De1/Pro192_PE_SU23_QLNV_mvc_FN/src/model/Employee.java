package model;

import java.util.Date;
import view.Validation;

public class Employee {

    private String empld;
    private String account;
    private Date workStartingDate;
    private float productivityScore;
    private float monthlyIncome;
    private float rewardSalary;
    private int allowance;

    public Employee(String empld, String account, Date workStartingDate, float productivityScore) {
        this.empld = empld;
        this.account = account;

        this.workStartingDate = workStartingDate;
        this.productivityScore = productivityScore;
    }

    public Employee(String empld, String account, Date workStartingDate, float productivityScore, int allowance) {
        this.empld = empld;
        this.account = account;

        this.workStartingDate = workStartingDate;
        this.productivityScore = productivityScore;

        this.allowance = allowance;
    }

    public String getEmpld() {
        return empld;
    }

    public void setEmpld(String empld) {
        this.empld = empld;
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

    public float getAllowance() {
        return allowance;
    }

    public void setAllowance(int allowance) {
        this.allowance = allowance;
    }

    @Override
    public String toString() {

        return String.format("%-10s | %-10s | %-15s | %-10s | %s", empld, account, (new Validation()).showDate(workStartingDate), productivityScore, monthlyIncome);
    }

    public float calReward() {
        return this.productivityScore * 3000000;
    }

    public float calMonthlyIncome() {
        return calReward() + this.allowance;

    }

}
