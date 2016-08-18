package com.example.anna.sniffin_sticks;

/**
 * Created by Anna on 2016-08-18.
 */
public class TableTestTHR {

    String[] levels;
    String[] turningLevels;
    String[] points;
    String[][] scheme = new String[16][turningLevels.length - 1];

    TableTestTHR(String[] levels, String[] turningLevels, String[] points) {

        int levelsInd = 0;

        // our case depends on a number of a column
        for (int j = 0; j < 8; j++) {
            switch (j) {

                //for first column
                case 0:
                    for (int i = scheme.length; i >= 0; i--) {

                        if ((levels[levelsInd]).equals(i)) {

                            //check if there was a good or a wrong answer
                            //if good we have to chech whether the next point also good
                            if (points[levelsInd].equals("1")) {
                                if (points[levelsInd-1].equals("1")){
                                    scheme[Integer.parseInt(levels[levelsInd]) - 1][j] = points[levelsInd];
                                    levelsInd++;

                                    // TURNING POINT
                                    break;

                                }else {
                                    scheme[Integer.parseInt(levels[levelsInd]) - 1][j] = points[levelsInd];
                                    levelsInd++;
                                    i++;
                                }
                            }

                            // if wrong we search for good ones
                            else {
                                scheme[Integer.parseInt(levels[levelsInd]) - 1][j] = points[levelsInd];
                                levelsInd++;
                            }
                        }
                    }
                    break;

                // for 2nd, 4th and 6th
                case 1:
                case 3:
                case 5:
                        for (int i = 0; i < scheme.length; i++) {
                            if ((levels[levelsInd]).equals(i)) {
                                scheme[Integer.parseInt(levels[levelsInd]) - 1][j] = points[levelsInd];
                                levelsInd++;
                            }
                        }
                    break;

                // for 3rd, 5th, 7th column
                case 2:
                case 4:
                case 6:

                    break;
            }
        }

    }
}

