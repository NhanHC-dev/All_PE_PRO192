package view;

import model.BrandList;
import model.Brands;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Scanner;

public class Validation {

    private static final Scanner scanner = new Scanner(System.in);

    public static int getInt() {
        return getInt(null, Integer.MIN_VALUE, Integer.MAX_VALUE);
    }

    public static int getInt(int minRange, int maxRange) {
        return getInt(null, minRange, maxRange);
    }

    public static int getInt(String msg) {
        return getInt(msg, Integer.MIN_VALUE, Integer.MAX_VALUE);
    }

    public static int getInt(String msg, int minRange, int maxRange) {
        if (minRange > maxRange) {
            int temp = minRange;
            minRange = maxRange;
            maxRange = temp;
        }

        int value = Integer.MIN_VALUE;

        do {
            try {
                if (msg != null) {
                    System.out.print(msg);
                }

                value = Integer.parseInt(scanner.nextLine());
            } catch (NumberFormatException e) {
                System.err.println("Please enter again...");
            }
        } while (value < minRange || value > maxRange);

        return value;
    }

    public static String getString(String msg, String pattern) {
        String value = null;

        do {
            value = getString(msg);
        } while (!value.matches(pattern));

        return value;
    }

    public static String getString(String msg) {
        String value = null;

        while (true) {
            if (msg != null) {
                System.out.print(msg);
            }

            value = scanner.nextLine().replaceAll("\\s+", " ").trim();

            if (!value.isEmpty() && !value.isBlank()) {
                break;
            }

            System.err.println("Please enter again...");
        }

        return value;
    }

    public static float getFloat() {
        return getFloat(null, Float.MIN_VALUE, Float.MAX_VALUE);
    }

    public static float getFloat(float minRange, float maxRange) {
        return getFloat(null, minRange, maxRange);
    }

    public static float getFloat(String msg) {
        return getFloat(msg, Float.MIN_VALUE, Float.MAX_VALUE);
    }

    public static float getFloat(String msg, float minRange, float maxRange) {
        if (minRange > maxRange) {
            float temp = minRange;
            minRange = maxRange;
            maxRange = temp;
        }

        float value = Float.MIN_VALUE;

        do {
            try {
                if (msg != null) {
                    System.out.print(msg);
                }

                value = Float.parseFloat(scanner.nextLine());
            } catch (NumberFormatException e) {
                System.err.println("Please enter again...");
            }
        } while (value < minRange || value > maxRange);

        return value;
    }

    public static LocalDate convertDate(String formattedDate) {
        DateTimeFormatter DATE_FORMATTER = DateTimeFormatter.ofPattern("[d-M-yyyy][dd-MM-yyyy]");
        return LocalDate.parse(formattedDate,DATE_FORMATTER);
    }

    public static LocalDate inputDate(String msg) {
        while (true) {
            try {
                String input = getString(msg);
                LocalDate date = convertDate(input);
                return date;
            } catch (Exception e) {
                System.out.println("Invalid date format or date not exist!");
            }
        }
    }

    public static double getDouble() {
        return getDouble(null, Double.MIN_VALUE, Double.MAX_VALUE);
    }

    public static double getDouble(double minRange, double maxRange) {
        return getDouble(null, minRange, maxRange);
    }

    public static double getDouble(String msg) {
        return getDouble(msg, Double.MIN_VALUE, Double.MAX_VALUE);
    }

    public static double getDouble(String msg, double minRange, double maxRange) {
        if (minRange > maxRange) {
            double temp = minRange;
            minRange = maxRange;
            maxRange = temp;
        }

        double value = Double.MIN_VALUE;

        do {
            try {
                if (msg != null) {
                    System.out.print(msg);
                }

                value = Double.parseDouble(scanner.nextLine());
            } catch (NumberFormatException e) {
                System.err.println("Please enter again...");
            }
        } while (value < minRange || value > maxRange);

        return value;
    }

    public static boolean checkValid(String id) {
        return id.startsWith("B") && id.contains("-");
    }

    public static boolean isPatientIDDuplicated(ArrayList<Brands> list, String productID) {
        productID = productID.trim().toUpperCase();

        for (Brands brands : list) {
            if (brands.getBrandID().equalsIgnoreCase(productID)) {
                return true;
            }
        }
        return false;
    }

    public static int extractYearFromModel(String model) {
        try {
            int start = model.lastIndexOf("(") + 1;
            int end = model.lastIndexOf(")");
            return Integer.parseInt(model.substring(start, end));
        } catch (Exception e) {
            System.out.println("Invalid model year format for model: " + model);
            return -1;
        }
    }
}