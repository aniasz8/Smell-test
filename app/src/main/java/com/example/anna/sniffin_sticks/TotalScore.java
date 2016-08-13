package com.example.anna.sniffin_sticks;

import android.provider.Settings;
import android.widget.Toast;

import java.text.DecimalFormatSymbols;
import java.text.NumberFormat;

/**
 * Created by Anna on 2016-08-10.
 */
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

            // problem ze juz liczby po przecinku????????????????????
            total_score=testTHR_d+testDIS_d+testID_d;

            NumberFormat nf = NumberFormat.getInstance();
            nf.setMaximumFractionDigits(2);
            nf.setMinimumFractionDigits(2);

            DecimalFormatSymbols diffrentSymbol = new DecimalFormatSymbols();

            total_score_string=nf.format(total_score);

        } catch (NumberFormatException nfe) {
            System.out.println("Nicht " + nfe);
        }
        return total_score_string;
    }

    public String getTestTHR (){
        return testTHR;
    };
    public String getTestDIS (){
        return testDIS;
    };
    public String getTestID (){
        return testID;
    }
}
