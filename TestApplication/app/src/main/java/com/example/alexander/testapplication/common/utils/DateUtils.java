package com.example.alexander.testapplication.common.utils;

import android.content.Context;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

public class DateUtils {

    public static String getDateDifference(Date thenDate) {
        Locale local = new Locale("ru","RU");
        Calendar now = Calendar.getInstance(local);
        Calendar then = Calendar.getInstance(local);

        Calendar calendar = Calendar.getInstance();
        calendar.setTimeInMillis(thenDate.getTime());

        int mYear = calendar.get(Calendar.YEAR);
        int mMonth = calendar.get(Calendar.MONTH);
        int mDay = calendar.get(Calendar.DAY_OF_MONTH);
        int hour       = calendar.get(Calendar.HOUR);
        int hourOfDay  = calendar.get(Calendar.HOUR_OF_DAY);
        int minute     = calendar.get(Calendar.MINUTE);

        now.setTime(new Date());
        then.setTime(thenDate);

        // Get the represented date in milliseconds
        long nowMs = now.getTimeInMillis();
        long thenMs = then.getTimeInMillis();

        // Calculate difference in milliseconds
        long diff = nowMs - thenMs;

        // Calculate difference in seconds
        long diffMinutes = diff / (60 * 1000);
        long diffHours = diff / (60 * 60 * 1000);
        long diffDays = diff / (24 * 60 * 60 * 1000);

        if (diffMinutes < 60) {
            if (diffMinutes == 1)
                return diffMinutes + " minute ago";
            else
                return diffMinutes + " minutes ago";
        } else if (diffHours < 24) {
            if (diffHours == 1)
                return diffHours + " hour ago";
            else
                return diffHours + " hours ago";
        } else if (diffDays < 30) {
            if (diffDays == 1)
                return diffDays + " day ago";
            else
                return diffDays + " days ago";
        } else {
            return "a long time ago..";
        }
    }


    public static String getLocateDate(String date, Context context) {
        try {
            SimpleDateFormat df = new SimpleDateFormat("EEE, dd MMM yyyy kk:mm:ss Z", Locale.ENGLISH);
            Date pDate = df.parse(date);
            DateFormat dateFormat = android.text.format.DateFormat.getLongDateFormat(context);
            DateFormat timeFormat = android.text.format.DateFormat.getTimeFormat(context);
            return dateFormat.format(pDate) + " " + timeFormat.format(pDate);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return date;
    }
}
