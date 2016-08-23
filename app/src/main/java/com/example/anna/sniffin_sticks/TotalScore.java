package com.example.anna.sniffin_sticks;

import java.util.Locale;

/**
 * Created by Anna on 2016-08-10.
 */
// Copyright (C) <2016> <Anna Szagdaj with the help of Jakub Licznerski>
public class TotalScore {

    private double total_score = 0;
    private String total_score_string;

    private String testID;
    private String testTHR;
    private String testDIS;

    private double testTHR_d;
    private double testDIS_d;
    private double testID_d;

    public TotalScore(String testTHR, String testDIS, String testID) {

        if (testTHR==null )
            testTHR="0";
        if (testDIS==null)
            testDIS="0";
        if (testID==null)
            testID="0";

        this.testTHR = testTHR;
        this.testDIS = testDIS;
        this.testID = testID;
    }

    String totalResult() {
        try {

            testTHR_d = Double.parseDouble(testTHR);
            testDIS_d =  Double.parseDouble(testDIS);
            testID_d = Double.parseDouble(testID);

            total_score=testTHR_d+testDIS_d+testID_d;
            total_score_string=String.format(Locale.US,"%.2f",total_score);

        } catch (NumberFormatException nfe) {
            System.out.println("Nicht " + nfe);
        }
        return total_score_string;
    }
    double resultDouble(){
        return total_score;
    }
}
