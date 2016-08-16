package com.example.anna.sniffin_sticks;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by Anna on 2016-08-16.
 */
public class DateParser {


    public static int [] parseDate(String dateStr){

        int day = Integer.parseInt(dateStr.substring(0,2));
        int month = Integer.parseInt(dateStr.substring(3,5));
        int year = Integer.parseInt(dateStr.substring(6));

        int [] result = {day, month, year};
        return result;
    }
}
