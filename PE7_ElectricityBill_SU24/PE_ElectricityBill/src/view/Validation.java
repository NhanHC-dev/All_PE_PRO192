
package view;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Scanner;


public class Validation {
    final static String DATE_FORMAT = "dd/MM/yyyy";
    final static Scanner scanner = new Scanner(System.in);
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
    public Date checkValidDate(String msg) {
        while (true) {
            try {
                return validStringToDate(msg);
            } catch (ParseException ex) {
                System.out.println(msg+"Invalid date. Date format: dd/mm/yyyy");
                return null;
            }
        }
    }

    public Date validStringToDate(String date) throws ParseException {
        DateFormat df = new SimpleDateFormat(DATE_FORMAT);
        return df.parse(date);
    }

    public String showDate(Date date) {
        DateFormat df = new SimpleDateFormat(DATE_FORMAT);
        return df.format(date);
    }
    public String checkEmpId(String input) throws Exception {
        String temp = input.trim();
        if (temp == null || temp.length() != 4) {
            return null;
        }
        String kh = "KH";
        String subStr = temp.substring(0, 2);
        if (!kh.equals(subStr)) {
            return null;
        }
        String subStr1 = temp.substring(2, 3);
        for (int i = 0; i < subStr1.length(); i++) {
            if (subStr1.charAt(i) < '0' || subStr1.charAt(i) > '9') {
                return null;
            }
        }
        if (temp == null) {
            throw new Exception("ID customer is wrong");
        }
        return temp;
    }

    public static String checkProId(String input) throws Exception{
        if (input == null || input.trim().length() != 4) {
            throw new Exception("ID product is wrong");
        }
        String temp = input.trim();
        String pattern = "[pP]\\d{3}";

        if (!temp.matches(pattern)) {
            throw new Exception("ID product is wrong");
        }

        return temp;
    }

    public String validName(String name) throws IllegalArgumentException {
        if (name == null || name.isEmpty()) {
            throw new IllegalArgumentException("Name cannot be empty");
        }
        return name;
    }
    public Date inputDate(String msg) {
        SimpleDateFormat df = new SimpleDateFormat(DATE_FORMAT);
        while (true) {
            try {
                System.out.print(msg);
                return df.parse(scanner.nextLine());
            } catch (ParseException e) {
                System.out.println("Invalid date format. Please use dd/MM/yyyy.");
            }
        }
    }

    public String inputValidProductId(String msg) {
        String id;
        while (true) {
            System.out.print(msg);
            id = scanner.nextLine().trim();
            if (id.length() == 4 && id.toUpperCase().startsWith("P") && id.substring(1).matches("\\d{3}")) {
                return id.toUpperCase();
            }
            System.out.println("The ProductID is in wrong format (start with 'P' and has 4 chars, e.g. P001).");
        }
    }

    public static boolean getBoolean(String message) {
        while (true) {
            System.out.print(message);
            String input = scanner.nextLine().trim().toLowerCase();
            if (input.equals("true")) {
                return true;
            } else if (input.equals("false")) {
                return false;
            }
            System.out.println("Invalid input. Please enter true or false.");
        }
    }
}
