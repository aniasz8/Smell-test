package com.example.anna.sniffin_sticks;

/**
 * Created by Anna on 2016-08-16.
 */
public class Diagnosis {

    private int age;
    private double score;
    private String diagnosis;
    private String N = "Normosmie";
    private String H = "Hyposmie";
    private String A = "Anosmie";

    Diagnosis (int age, double score){
        this.age = age;
        this.score = score;
    }

    public String diagnose (){

        if (score==0)
            diagnosis = "-";

// tu musi byc po kolei bo zle bierze wartosci
        if (age<16 ) {
            if (score > 25)
                diagnosis = N;
            if (score < 16)
                diagnosis = A;
            else
                diagnosis = H;
        }
        if (age>=16 && age<=35){
            if (score>32)
                diagnosis = N;
            if (score<16)
                diagnosis = A;
            else
                diagnosis = H;
        }
        if (age>=36 && age<=53) {
            if (score > 29)
                diagnosis = N;
            if (score < 16)
                diagnosis = A;
            else
                diagnosis = H;
        }
        else{
            if (score > 28)
                diagnosis = N;
            if (score < 16)
                diagnosis = A;
            else
                diagnosis = H;
        }

        return diagnosis;
    }
}
