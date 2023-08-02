package com.ckw.common.utils;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class DateUtils {
    private static SimpleDateFormat formatter= new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

    private static SimpleDateFormat dateFormat= new SimpleDateFormat("yyyy-MM-dd");
    private static Calendar calendar = Calendar.getInstance();

    public static String getCurrentTime(){
        return formatter.format(new Date(System.currentTimeMillis()));
    }

    public static String getCurrentDate(){
        return dateFormat.format(new Date(System.currentTimeMillis()));
    }
    public static int getYear(){
        return calendar.get(Calendar.YEAR);
    }


    public static int getMonth(){
        return calendar.get(Calendar.MONTH)+1;
    }
    public static int getDay(){
        return calendar.get(Calendar.DATE);
    }
    public static int getMonthDays(int year, int month) {
        Calendar cal = Calendar.getInstance();
        cal.set(Calendar.YEAR, year);
        cal.set(Calendar.MONTH, (month - 1));
        cal.set(Calendar.DATE, 1);
        cal.roll(Calendar.DATE, -1);
        return cal.getActualMaximum(Calendar.DATE);
    }


}
