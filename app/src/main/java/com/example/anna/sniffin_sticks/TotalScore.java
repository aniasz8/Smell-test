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

    public TotalScore(String testTHR, String testDIS, String testID) {
        this.testTHR = testTHR;
        this.testDIS = testDIS;
        this.testID = testID;
    }

    int totalResult() {
        try {
            total_score = (Integer.parseInt(testTHR) + Integer.parseInt(testDIS) + Integer.parseInt(testID));
        } catch (NumberFormatException nfe) {
            System.out.println("Nicht " + nfe);
        }
        return total_score;
    }
}
