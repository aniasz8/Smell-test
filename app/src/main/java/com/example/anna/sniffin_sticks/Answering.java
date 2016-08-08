package com.example.anna.sniffin_sticks;

import java.util.ArrayList;

/**
 * Created by Anna on 2016-08-08.
 */
public class Answering {

    private int point;
    private int level;
    private int index;
    private int change;
    ArrayList<Integer> listTHR_answers = new ArrayList<>();
    ArrayList<Integer> listTHR_levels = new ArrayList<>();
    int [] listTHR_turningLevels = new int [8];

    public Answering(int point, int change, int level, int index, ArrayList<Integer> listTHR_answers,
                     ArrayList<Integer> listTHR_levels, int [] listTHR_turningLevels  ) {


        // if we are after second answering
        if (index >= 1) {
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
                                // if there is 10 - it means we should increase change and add level to table
                                listTHR_turningLevels[change] = level;
                                level = level - 1;
                                change = change + 1;
                                break;

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
                        switch (point) {
                            case 1:
                                if (listTHR_levels.get(index) == listTHR_levels.get(index - 1)) {

                                    // if they were = going to higher level
                                    level = level + 1;

                                    //if there is 110 = increase change
                                    if (listTHR_answers.get(index - 2) == 0) {

                                        listTHR_turningLevels[change] = level - 1;
                                        change = change + 1;
                                    }
                                }
                                break;

                            case 0:
                                listTHR_turningLevels[change] = level;
                                level = level - 1;
                                change = change + 1;
                                break;
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
                    break;
                case 0:
                    level = level - 2;
                    break;
            }
        }
        this.listTHR_answers = listTHR_answers;
        this.listTHR_turningLevels = listTHR_turningLevels;
        this.listTHR_levels = listTHR_levels;
        this.point = point;
        this.change = change;
        this.level = level;
        this.index = index;
}

    public int getLevel () {
        return level;}

    public int getIndex() {
        return index;
    }

    public int getChange() {
        return change;
    }

    public int getPoint() {
        return point;
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
