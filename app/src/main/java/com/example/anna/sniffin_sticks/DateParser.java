package com.example.anna.sniffin_sticks;


/**
 * Created by Anna on 2016-08-16.
 */
// Copyright (C) <2016> <Anna Szagdaj with the help of Jakub Licznerski>
public class DateParser {


    public static int [] parseDate(String dateStr){

        int day = Integer.parseInt(dateStr.substring(0,2));
        int month = Integer.parseInt(dateStr.substring(3,5));
        int year = Integer.parseInt(dateStr.substring(6));

        int [] result = {day, month, year};
        return result;
    }
}
