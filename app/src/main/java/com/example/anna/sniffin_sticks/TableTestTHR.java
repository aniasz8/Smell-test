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

        this.turningLevels=turningLevels;
        this.points=points;
        this.levels=levels;
    }

    String [][] createScheme (){

        int levelsInd = 0;

        // our case depends on a number of a column
        for (int j = 0; j < 8; j++) {
            switch (j) {

                //for even columns
                case 0:
                case 2:
                case 4:
                case 6:
                    for (int i = scheme.length; i >= 0; i--) {

                        if ((levels[levelsInd]).equals(i)) {

                            //check if there was a good or a wrong answer
                            //if good we have to chech whether the previous point was also good
                            if (points[levelsInd].equals("1")) {
                                if (points[levelsInd-1].equals("1")) {
                                    scheme[Integer.parseInt(levels[levelsInd]) - 1][j] = "11x";
                                    levelsInd++;

                                    // TURNING POINT
                                    break;
                                }
                                else {
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

                // for odd columns
                case 1:
                case 3:
                case 5:
                    for (int i = 0; i <= scheme.length; i++) {
                        if ((levels[levelsInd]).equals(i)) {

                            //check if there was a good or a wrong answer
                            //if good we have to chech whether the next point also good
                            if (points[levelsInd].equals("1")) {

                                //check if previous level was the same
                                if(levels[levelsInd]==levels[levelsInd-1]){

                                    scheme[Integer.parseInt(levels[levelsInd]) - 1][j] = "11";
                                    levelsInd++;
                                }
                                else{
                                    scheme[Integer.parseInt(levels[levelsInd]) - 1][j] = points[levelsInd];
                                    levelsInd++;
                                    i--;
                                }
                            }

                            else{
                                scheme[Integer.parseInt(levels[levelsInd]) - 1][j] = "0x";
                                levelsInd++;

                                // TURNING POINT
                                break;
                            }
                        }
                    }
                    break;
            }
        }

         return scheme;
    }

}

