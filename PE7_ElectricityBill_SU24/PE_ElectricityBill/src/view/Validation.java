package view;

import model.Bill;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
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
    public static double getDouble(String message, double min, double max) {
        double input;
        while (true) {
            System.out.print(message);
            try {
                input = Double.parseDouble(scanner.nextLine());
                if (input >= min && input <= max) {
                    break;
                }
                System.out.println("Input must be between " + min + " and " + max + ".");
            } catch (NumberFormatException e) {
                System.out.println("Invalid input. Please enter a number.");
            }
        }
        return input;
    }

    public static Date checkValidDate(String dateStr) throws ParseException {
        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
        sdf.setLenient(false);
        return sdf.parse(dateStr);
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
