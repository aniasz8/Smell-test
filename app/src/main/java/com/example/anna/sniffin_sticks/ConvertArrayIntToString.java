package com.example.anna.sniffin_sticks;

import java.util.List;

/**
 * Created by Anna on 2016-08-10.
 */
public class ConvertArrayIntToString {


    public static String [] convertArray(int [] array) {
        String [] converted = new String[array.length];

        for(int i = 0; i < array.length; i++){
            converted[i] = String.valueOf(array[i]);
        }

        return converted;
    }

    public static String[] convertList(List<Integer> list){
        String [] converted = new String[list.size()];

        for(int i=0; i < list.size(); i++){
            converted[i] = String.valueOf(list.get(i));
        }

        return converted;
    }
}
