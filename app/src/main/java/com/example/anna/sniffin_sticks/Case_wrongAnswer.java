package com.example.anna.sniffin_sticks;

import java.util.ArrayList;

/**
 * Created by Anna on 2016-08-08.
 */
public class Case_wrongAnswer {

    private int point;
    private int level;
    private int index;
    private int change;
    ArrayList<Integer> listTHR_answers = new ArrayList<>();
    ArrayList<Integer> listTHR_levels = new ArrayList<>();
    int [] listTHR_turningLevels = new int [8];

    public Case_wrongAnswer (int point, int change, int level,int index, ArrayList<Integer> listTHR_answers,
                             ArrayList<Integer> listTHR_levels,int [] listTHR_turningLevels  ){
        this.point=point;
        this.level = level;
        this.index=index;
        this.change=change;
        this.listTHR_answers =listTHR_answers;
        this.listTHR_levels = listTHR_levels;
        this.listTHR_turningLevels = listTHR_turningLevels;
    }

}
