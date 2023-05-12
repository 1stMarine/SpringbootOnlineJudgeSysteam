package com.ckw.common.utils;

import java.util.Calendar;

public class DateUtils {

    private static Calendar calendar = Calendar.getInstance();

    public static int getYear(){
        return calendar.get(Calendar.YEAR);
    }


    public static int getMonth(){
        return calendar.get(Calendar.MONTH)+1;
    }
    public static int getDay(){
        return calendar.get(Calendar.DATE)+1;
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
