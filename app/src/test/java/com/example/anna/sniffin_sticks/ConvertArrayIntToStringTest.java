package com.example.anna.sniffin_sticks;

import org.junit.Assert;
import org.junit.Test;

import java.util.ArrayList;

import static org.junit.Assert.*;

/**
 * Created by Anna on 2016-08-13.
 */
public class ConvertArrayIntToStringTest {

    @Test
    public void testConvertArray(){
        int [] testInt = {1, 2, 3};
        String [] testString = {"1", "2", "3"};
        Assert.assertArrayEquals(ConvertArrayIntToString.convertArray(testInt), testString);
    }

    @Test
    public void testConvertList(){
        ArrayList<Integer> testInt = new ArrayList<>();
        testInt.add(1);
        testInt.add(2);
        testInt.add(3);

        String [] testString = {"1", "2", "3"};
        Assert.assertArrayEquals(ConvertArrayIntToString.convertList(testInt), testString);
    }
}