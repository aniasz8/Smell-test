package com.example.anna.sniffin_sticks;

/**
 * Created by Anna on 2016-08-18.
 */
// Copyright (C) <2016> <Anna Szagdaj with the help of Jakub Licznerski>
public class TableTestTHR {

    String[] levels;
    String[] turningLevels;
    String[] points;
    String[][] scheme = new String[16][7];

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

                        if ((levels[levelsInd]).equals(i+"")) {

                            //check if there was a good or a wrong answer
                            //if good we have to chech whether the previous point was also good - pu 111
                            if (points[levelsInd].equals("1")) {
                                //check if it is a first answer
                                if (levelsInd==0){
                                    scheme[Integer.parseInt(levels[levelsInd]) - 1][j] = "1";
                                    levelsInd++;
                                    i++;
                                }
                                else {

                                    if (points[levelsInd - 1].equals("1")) {
                                        scheme[Integer.parseInt(levels[levelsInd]) - 1][j] = "111";
                                        levelsInd++;

                                        // TURNING POINT
                                        break;

                                    } else {
                                        scheme[Integer.parseInt(levels[levelsInd]) - 1][j] = "1";
                                        levelsInd++;
                                        i++;
                                    }
                                }
                            }
                            // if wrong we search for good ones - put in table 0
                            else {
                                scheme[Integer.parseInt(levels[levelsInd]) - 1][j] = "0";
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
                        if ((levels[levelsInd]).equals(i+"")) {

                            //check if there was a good or a wrong answer
                            //if good we have to chech whether the next point also good
                            if (points[levelsInd].equals("1")) {

                                //check if previous level was the same
                                if(levels[levelsInd]==levels[levelsInd-1]){

                                    scheme[Integer.parseInt(levels[levelsInd]) - 1][j] = "11";
                                    levelsInd++;
                                }
                                else{
                                    scheme[Integer.parseInt(levels[levelsInd]) - 1][j] = "1";
                                    levelsInd++;
                                    i--;
                                }
                            }

                            else{
                                scheme[Integer.parseInt(levels[levelsInd]) - 1][j] = "00";
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

