package com.litesoftwares.covidcheckbot.utils;

import java.text.DateFormat;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;

public class Util {

    public static String getTime(String time) {
        try {
            DateFormat formatter = new SimpleDateFormat("dd/MM/yyyy h:mm:ss");
            Calendar calendar = Calendar.getInstance();
            calendar.setTimeInMillis(Long.valueOf(time));
            return formatter.format(calendar.getTime()) + " (GMT)";
        } catch (Exception e){
            e.printStackTrace();
            return "not available.";
        }


    }

    public static String formatNumber(String number) {

        try {
            DecimalFormat formatter = new DecimalFormat("#,###");
            if (number == null){
                return "";
            }
            return formatter.format(Long.parseLong(number));
        } catch (Exception e) {
            e.printStackTrace();
            return "";
        }
    }
}
