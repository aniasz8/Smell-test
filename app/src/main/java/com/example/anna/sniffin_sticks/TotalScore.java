package com.example.anna.sniffin_sticks;

import android.provider.Settings;
import android.widget.Toast;

/**
 * Created by Anna on 2016-08-10.
 */
public class TotalScore {

    private int total_score = 0;
    private String testID;
    private String testTHR;
    private String testDIS;
    private int testTHR_int;
    private int testDIS_int;
    private int testID_int;

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

    int totalResult() {
        try {
            testTHR_int = Integer.parseInt(testTHR);
            testDIS_int =  Integer.parseInt(testDIS);
            testID_int = Integer.parseInt(testID);

            total_score=testTHR_int+testDIS_int+testID_int;

        } catch (NumberFormatException nfe) {
            System.out.println("Nicht " + nfe);
        }
        return total_score;
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
