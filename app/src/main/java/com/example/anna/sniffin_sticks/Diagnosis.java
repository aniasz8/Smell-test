package com.example.anna.sniffin_sticks;

/**
 * Created by Anna on 2016-08-16.
 */
public class Diagnosis {

    private double score;
    private String diagnosis;
    private String N = "Normosmie";
    private String H = "Hyposmie";
    private String A = "Anosmie";

    Diagnosis (double score){
        this.score = score;
    }

    public String diagnoseAll (){

        if (score>30.5)
            diagnosis=N;
        if (score<=30.5){
            if(score>=16.25)
                diagnosis=H;
            else
                diagnosis=A;
        }

        return diagnosis;
    }

    public String diagnoseTHR (){

        if (score>6.5)
            diagnosis=N;
        if(score<=6.5){
            if (score==1)
                diagnosis=A;
            else
                diagnosis=H;
        }
        return diagnosis;
    }

    public String diagnoseDIS (){

        if (score>10)
            diagnosis=N;
        if(score<=10){
            if (score>=9)
                diagnosis=A;
            else
                diagnosis=H;
        }
        return diagnosis;
    }

    public String diagnoseID (){

        if (score>11)
            diagnosis=N;
        if(score<=11){
            if (score>=9)
                diagnosis=A;
            else
                diagnosis=H;
        }
        return diagnosis;
    }
}
