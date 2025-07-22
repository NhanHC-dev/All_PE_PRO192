package view;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

public class Validation {
    final static String DATE_FORMAT = "dd/MM/yyyy";

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
                System.err.println("Please enter a valid integer...");
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
                System.err.println("Please enter a valid float...");
            }
        } while (value < minRange || value > maxRange);

        return value;
    }

    public static String getString(String msg, String pattern) {
        String value;
        do {
            value = getString(msg);
        } while (!value.matches(pattern));

        return value;
    }

    public static String getString(String msg) {
        String value;
        while (true) {
            if (msg != null) {
                System.out.print(msg);
            }
            value = scanner.nextLine().replaceAll("\\s+", " ").trim();

            if (!value.isEmpty()) {
                break;
            }
            System.err.println("Please enter a non-empty string...");
        }
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
                System.err.println("Please enter a valid double...");
            }
        } while (value < minRange || value > maxRange);

        return value;
    }

    public static long getLong() {
        return getLong(null, Long.MIN_VALUE, Long.MAX_VALUE);
    }

    public static long getLong(long minRange, long maxRange) {
        return getLong(null, minRange, maxRange);
    }

    public static long getLong(String msg) {
        return getLong(msg, Long.MIN_VALUE, Long.MAX_VALUE);
    }

    public static long getLong(String msg, long minRange, long maxRange) {
        if (minRange > maxRange) {
            long temp = minRange;
            minRange = maxRange;
            maxRange = temp;
        }

        long value = Long.MIN_VALUE;

        do {
            try {
                if (msg != null) {
                    System.out.print(msg);
                }
                value = Long.parseLong(scanner.nextLine());
            } catch (NumberFormatException e) {
                System.err.println("Please enter a valid long integer...");
            }
        } while (value < minRange || value > maxRange);
        return value;
    }

    public static String getNumericString(String msg) {
        String value;
        while (true) {
            if (msg != null) {
                System.out.print(msg);
            }
            value = scanner.nextLine().trim();

            if (value.matches("\\d+")) {
                break;
            }
            System.err.println("Please enter only numeric digits...");
        }
        return value;
    }

    public static Date checkValidDate(String msg) {
        while (true) {
            try {
                return validStringToDate(msg);
            } catch (ParseException ex) {
                System.out.println(msg + "Invalid date. Date format: dd/mm/yyyy");
                return null;
            }
        }
    }

    public static Date validStringToDate(String date) throws ParseException {
        DateFormat df = new SimpleDateFormat(DATE_FORMAT);
        return df.parse(date);
    }

    public static String showDate(Date date) {
        DateFormat df = new SimpleDateFormat(DATE_FORMAT);
        return df.format(date);
    }
}