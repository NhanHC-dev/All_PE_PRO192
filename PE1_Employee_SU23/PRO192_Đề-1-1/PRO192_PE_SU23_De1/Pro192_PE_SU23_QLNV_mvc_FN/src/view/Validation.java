/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package view;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.Date;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author trang
 */
public class Validation {

    final static String DATE_FORMAT = "dd/MM/yyyy";

    public String getValue(String msg) {
        Scanner sc = new Scanner(System.in);
        System.out.println(msg);
        return sc.nextLine().trim();
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
        // df.setLenient(false);
        return df.parse(date);
    }

    public String showDate(Date date) {
        DateFormat df = new SimpleDateFormat(DATE_FORMAT);
        return df.format(date);
    }
    // Kiểm tra định dạng EmpId

    public String checkEmpId(String input) throws Exception {
        String temp = input.trim();
        if (temp == null || temp.length() != 6) {
            return null;
        }
        String mnv = "MNV";
        String subStr = temp.substring(0, 3);
        if (!mnv.equals(subStr)) {
            return null;
        }
        String subStr1 = temp.substring(3, 6);
        for (int i = 0; i < subStr1.length(); i++) {
            if (subStr1.charAt(i) < '0' || subStr1.charAt(i) > '9') {
                return null;
            }
        }
        if (temp == null) {
            throw new Exception("ID employee is wrong");
        }
        return temp;
    }
}
