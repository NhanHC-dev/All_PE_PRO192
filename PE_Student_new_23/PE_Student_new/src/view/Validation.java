package view;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.Date;
import java.util.Scanner;


public class Validation {
    final static String DATE_FORMAT = "dd/MM/yyyy";
    final static Scanner scanner = new Scanner(System.in);
    private static final DateTimeFormatter DATE_FORMATTER = DateTimeFormatter.ofPattern(DATE_FORMAT);
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
    public static LocalDate convertDate(String formattedDate) {
        LocalDate date = LocalDate.parse(formattedDate, DATE_FORMATTER);
        return date;
    }
    public static LocalDate inputDate(String msg) {
        while (true) {
            try {
                System.out.print(msg);
                String dateStr = scanner.nextLine();
                return LocalDate.parse(dateStr, DATE_FORMATTER);
            } catch (DateTimeParseException e) {
                System.err.println("Invalid date. Date format: " + DATE_FORMAT);
            }
        }
    }

    public static String checkStudentId(String input) throws Exception {
        if (input == null || input.trim().length() != 8) {
            throw new Exception("ID employee is wrong");
        }
        String temp = input.trim();
        String pattern = "[dD][eE]\\d{6}";
        if (!temp.matches(pattern)) {
            throw new Exception("ID employee is wrong");
        }
        return temp;
    }

//    public static String checkStudentId(String input) throws Exception {
//        String temp = input.trim();
//        if (temp == null || temp.length() != 8) {
//            return null;
//        }
//        String pattern = "[dD][eE]\\d{6}";
//        String de = "DE";
//        String subStr = temp.substring(0, 2);
//        if (!subStr.matches(de)) {
//            return null;
//        }
//        String subStr1 = temp.substring(2, 8);
//        for (int i = 0; i < subStr1.length(); i++) {
//            if (subStr1.charAt(i) < '0' || subStr1.charAt(i) > '9') {
//                return null;
//            }
//        }
//        if (temp == null) {
//            throw new Exception("ID employee is wrong");
//        }
//        return temp;
//    }
}
