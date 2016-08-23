package com.example.anna.sniffin_sticks;

import java.util.Calendar;
import java.util.GregorianCalendar;

/**
 * Created by Anna on 2016-08-16.
 */
// Copyright (C) <2016> <Anna Szagdaj with the help of Jakub Licznerski>
public class CountAge {

    private int birthYear;
    private int birthMonth;
    private int birthDay;
    private int age;


    public int patientAge (int birthDay, int birthMonth, int birthYear){

        GregorianCalendar cal = new GregorianCalendar();
        int y, m, d;

        y = cal.get(Calendar.YEAR);
        m = cal.get(Calendar.MONTH);
        d = cal.get(Calendar.DAY_OF_MONTH);
        cal.set(birthYear, birthMonth, birthDay);
        age = y - cal.get(Calendar.YEAR);
        if ((m < cal.get(Calendar.MONTH))
                || ((m == cal.get(Calendar.MONTH)) && (d < cal
                .get(Calendar.DAY_OF_MONTH)))) {
            --age;
        }
        if(age < 0)
            throw new IllegalArgumentException("Age < 0");

       return age;
    }
}
