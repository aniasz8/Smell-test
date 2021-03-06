package com.example.anna.sniffin_sticks;

import java.util.Locale;

/**
 * Created by Anna on 2016-08-08.
 */
// Copyright (C) <2016> <Anna Szagdaj with the help of Jakub Licznerski>
public class TestTHR_score {

    private double testTHR_result=0;
    private String testTHR_result_string;
    int [] listTHR_turningLevels = new int [8];

    public TestTHR_score(int listTHR_turningLevels[]) {
        this.listTHR_turningLevels=listTHR_turningLevels;
    }

    String testTHR_StringResult (){
        for (int i = 3; i < 7; i++) {
            testTHR_result = testTHR_result + ((double)listTHR_turningLevels[i]);
        }
        testTHR_result =  testTHR_result/4;
        testTHR_result_string = String.format(Locale.US,"%.2f",testTHR_result);

        return testTHR_result_string;
    }
}
