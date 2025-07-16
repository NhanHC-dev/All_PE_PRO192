package model;


import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class BeanVariety {
    private String name;
    private String origin;
    private Date harvestDate;
    private double yield;
    private String diseaseResistance;

    public BeanVariety(String name, String origin, Date harvestDate, double yield, String diseaseResistance) {
        this.name = name;
        this.origin = origin;
        this.harvestDate = harvestDate;
        this.yield = yield;
        this.diseaseResistance = diseaseResistance;
    }

    public String getName() { return name; }
    public void setName(String name) { this.name = name; }

    public String getOrigin() { return origin; }
    public void setOrigin(String origin) { this.origin = origin; }

    public Date getHarvestDate() { return harvestDate; }
    public void setHarvestDate(Date harvestDate) { this.harvestDate = harvestDate; }

    public double getYield() { return yield; }
    public void setYield(double yield) { this.yield = yield; }

    public String getDiseaseResistance() { return diseaseResistance; }
    public void setDiseaseResistance(String diseaseResistance) { this.diseaseResistance = diseaseResistance; }

    public int getAge() {
        Calendar now = Calendar.getInstance();
        Calendar harvest = Calendar.getInstance();
        harvest.setTime(harvestDate);
        return now.get(Calendar.YEAR) - harvest.get(Calendar.YEAR);
    }
    @Override
    public String toString() {
        return String.format("%-12s | %-10s | %-10s | %4.2f | %-8s | %-3d",
                name, origin, new SimpleDateFormat("dd/MM/yyyy").format(harvestDate), yield, diseaseResistance, getAge());
    }
}


