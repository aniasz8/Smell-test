package com.example.anna.sniffin_sticks;

import java.util.Arrays;

/**
 * Created by Anna on 2016-08-10.
 */
public class ConvertArrayIntToString {

    int [] arrayOfInt;
    String [] arrayOfString;

    ConvertArrayIntToString (int [] arrayOfInt){

        Arrays.sort(arrayOfInt);
        String a=Arrays.toString(arrayOfInt);
        String arrayOfString[]=a.substring(1,a.length()-1).split(", ");

        this.arrayOfInt=arrayOfInt;
        this.arrayOfString=arrayOfString;
    }

    public String [] getArrayOfString() {
        return arrayOfString;
    }
}
