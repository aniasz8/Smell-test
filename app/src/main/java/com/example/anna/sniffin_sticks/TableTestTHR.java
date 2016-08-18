package com.example.anna.sniffin_sticks;

/**
 * Created by Anna on 2016-08-18.
 */
public class TableTestTHR {

    String [] levels;
    String [] turningLevels;
    String [] points;
    String [][] scheme = new String[16][turningLevels.length-1];

    TableTestTHR(String []levels, String [] turningLevels, String[] points) {

        // nie trzeba petli z wierzami bo levels[] to okreslaja
        int levelsInd = 0;

        for (int j = 0; j < 8; j++) {
            if (j%2==0) {
                for (int i = scheme.length; i >= 0; i--) {
                    if ((levels[levelsInd]).equals(i)) {
                        scheme[Integer.parseInt(levels[levelsInd]) - 1][j] = levels[levelsInd];
                        levelsInd++;
                    }
                }
            }else{
                for (int i = 0; i <scheme.length; i++) {
                    if ((levels[levelsInd]).equals(i)) {
                        scheme[Integer.parseInt(levels[levelsInd]) - 1][j] = levels[levelsInd];
                        levelsInd++;
                    }
                }
            }
        }
    }
}
