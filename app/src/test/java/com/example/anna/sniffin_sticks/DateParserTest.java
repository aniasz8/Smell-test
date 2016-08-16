package com.example.anna.sniffin_sticks;

import org.junit.Assert;
import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Created by Anna on 2016-08-16.
 */
public class DateParserTest {

    @Test
    public void parseDateTest(){
        String date = "26/12/1999";
        int [] dateInt = {26, 12, 1999};

        int [] toTest = DateParser.parseDate(date);

        Assert.assertArrayEquals(dateInt, toTest);
    }
}