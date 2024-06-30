package com.example.fitwithme.util;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.TextStyle;
import java.util.Locale;

public class DateUtil {

    public static String getDayByDate(String date){
        LocalDate localDate = LocalDate.parse(date, DateTimeFormatter.ISO_LOCAL_DATE);
        DayOfWeek dayOfWeek = localDate.getDayOfWeek();
        String day = dayOfWeek.getDisplayName(TextStyle.NARROW, Locale.KOREAN);

        return day;
    }

    public static String getToday(){
        LocalDate today = LocalDate.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        String date = today.format(formatter);

        return date;
    }
}
