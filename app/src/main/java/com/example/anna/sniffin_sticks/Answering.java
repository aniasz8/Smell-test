package com.example.anna.sniffin_sticks;

import java.util.ArrayList;

/**
 * Created by Anna on 2016-08-08.
 */
// Copyright (C) <2016> <Anna Szagdaj with the help of Jakub Licznerski>
public class Answering {

    private int level;
    private int index;
    private int change;
    ArrayList<Integer> listTHR_answers = new ArrayList<>();
    ArrayList<Integer> listTHR_levels = new ArrayList<>();
    int [] listTHR_turningLevels = new int [8];

    public Answering(int point, int change, int level, int index, ArrayList<Integer> listTHR_answers,
                     ArrayList<Integer> listTHR_levels, int [] listTHR_turningLevels  ) {


        // if we are after second answering
        if (index > 1) {
            switch (point) {
                case 0:
                    // check if previous there was any good answer- if not, decrese twice
                    if (!listTHR_answers.contains(1)) {
                        level = level - 2;
                    }

                    // if it is a wrong answer during the test - decrease just once and sometimes increase change
                    else {
                        //check what was before 0
                        int previous = listTHR_answers.get(index - 1);

                        switch (previous) {
                            case 1:
                                // two possibilites (11)(0) and (11)(10) to be a turning point and increase change
                                //we check if it is 110 if so turning point
                                if (listTHR_answers.get(index - 2) == 1){
                                    listTHR_turningLevels[change] = level;
                                    level = level - 1;
                                    change = change + 1;
                                    break;
                                }
                                //if it is just (x0)(10) just decrease level
                                else{
                                    level=level-1;
                                    break;
                                }
                            case 0:
                                level = level - 1;
                                break;
                        }
                    }
                    break;

                case 1:
                    //good answer = checking if the previous answer was also correct
                    if (listTHR_answers.get(index - 1) == 1) {

                        // checking if good answers were at the same level
                        if (listTHR_levels.get(index) == listTHR_levels.get(index - 1)) {

                            // if they were = going to higher level
                            level = level + 1;

                            //if there is (x0)(11) = increase change
                            if (listTHR_answers.get(index - 2) == 0) {

                                listTHR_turningLevels[change] = level - 1;
                                change = change + 1;
                            }
                        }
                    }
                    // otherwise level stays the same
                    break;
            }
        }
        // or we are just after first answering
        else {

            switch (point) {
                case 1:
                    //in case first 2 answers are good- first turning point
                    if(index==1){
                        if(listTHR_answers.get(index - 1) == 1){
                            listTHR_turningLevels[change] = level;
                            change = change + 1;
                        }
                    }
                    break;
                case 0:
                    //if the first answer is bad then level-2, if second level-1
                    if(index==0)
                        level = level - 2;
                    else {
                        if (listTHR_answers.get(index - 1) == 1)
                            level = level - 1;
                        else
                            level=level-2;
                    }
                    break;
            }
        }

        this.listTHR_answers = listTHR_answers;
        this.listTHR_turningLevels = listTHR_turningLevels;
        this.listTHR_levels = listTHR_levels;
        this.change = change;
        this.level = level;
        this.index = index;
    }

    public int getLevel () {
        return level;
    }

    public int getIndex() {
        return index;
    }

    public int getChange() {
        return change;
    }

    public ArrayList<Integer> getListTHR_answers() {
        return listTHR_answers;
    }

    public int[] getListTHR_turningLevels() {
        return listTHR_turningLevels;
    }

    public ArrayList<Integer> getListTHR_levels() {
        return listTHR_levels;
    }
}
