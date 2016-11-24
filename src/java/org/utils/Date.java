/**
 * ********************************************************************
 * Class Date
 * Management of the Dates (Formatting and computing)
 * --------------------------------------------------------------------
 * Last update : 16/06/2016
 * Author : Laura GUILLOT
 *********************************************************************
 */

package org.utils;

import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.StringTokenizer;

public class Date {

    /**
     * Return the current date with the format : dd/MM/yy H:mm:ss
     *
     * @return Date
     */
    public static String getDate() {
        String format = "dd/MM/yy H:mm:ss";
        java.text.SimpleDateFormat formater = new java.text.SimpleDateFormat(format);
        java.util.Date date = new java.util.Date();
        String d = formater.format(date);
        return d;
    }

    /**
     * Compute the number of days between two dates
     *
     * @param d1 Date
     * @param d2 Date
     * @return Number of days between d1 and d2
     */
    public static int nbJour(String d1, String d2) {

        StringTokenizer st1 = new StringTokenizer(d1, "/ ");
        int j1 = Integer.parseInt(st1.nextToken());
        int m1 = Integer.parseInt(st1.nextToken());
        int a1 = Integer.parseInt(st1.nextToken());

        StringTokenizer st2 = new StringTokenizer(d2, "/ ");
        int j2 = Integer.parseInt(st2.nextToken());
        int m2 = Integer.parseInt(st2.nextToken());
        int a2 = Integer.parseInt(st2.nextToken());

        Calendar calendar1 = new GregorianCalendar();
        calendar1.set(Calendar.YEAR, a1);
        calendar1.set(Calendar.MONTH, m1);
        calendar1.set(Calendar.DAY_OF_MONTH, j1);
        java.util.Date date1 = calendar1.getTime();

        Calendar calendar2 = new GregorianCalendar();
        calendar2.set(Calendar.YEAR, a2);
        calendar2.set(Calendar.MONTH, m2);
        calendar2.set(Calendar.DAY_OF_MONTH, j2);
        java.util.Date date2 = calendar2.getTime();

        long diff = Math.abs(date2.getTime() - date1.getTime());
        long CONST_DURATION_OF_DAY = 1000l * 60 * 60 * 24;
        long numberOfDay = (long) diff / CONST_DURATION_OF_DAY;

        return (int) numberOfDay;
    }

}
