package com.example.anna.sniffin_sticks;

import java.util.Objects;

/**
 * Created by Anna on 2016-08-02.
 */
public class Question {

    private String good;
    private String choice;

    public Question (String choice, String good) {
        this.choice=choice;
        this.good=good;
    }

    int result() {
        boolean result = Objects.equals(good,choice);
        int point = (result) ? 1: 0;
        return point;
    }

}
