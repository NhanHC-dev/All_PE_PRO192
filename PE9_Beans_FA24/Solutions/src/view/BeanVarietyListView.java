package view;

import model.BeanVariety;

import java.util.Date;
import java.util.List;

public class BeanVarietyListView {
    public static BeanVariety inputBeanVariety() {
        String name = Validation.getString("Enter name: ");
        String origin = Validation.getString("Enter origin: ");
        Date harvestDate = Validation.getDate("Enter harvest date (dd/MM/yyyy): ");
        double yield = Validation.getDouble("Enter yield: ", 0.1, 100);
        String resistance = Validation.getString("Enter disease resistance level: ");
        return new BeanVariety(name, origin, harvestDate, yield, resistance);
    }

    public static String inputKeyword(String msg) {
        return Validation.getString(msg);
    }
    public static Date inputKeywordDate(String msg) {
        return Validation.getDate(msg);
    }
}
