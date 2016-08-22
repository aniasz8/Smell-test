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
    private String testTHR;
    private String testDIS;
    private String testID;

    Diagnosis (double score, String testTHR, String testDIS, String testID){
        this.score = score;
        this.testTHR = testTHR;
        this.testID = testID;
        this.testDIS = testDIS;
    }

    public String diagnose (){

        // option with at least one test not done
        if (testTHR==null){
            if(testDIS==null){

                if (testID==null){
                    // THR=null, DIS=null, ID=null
                    testTHR="-";
                    testDIS="-";
                    testID="-";
                    diagnosis="-";
                }
                else{
                    //THR=null, DIS=null, ID=value
                    testTHR="-";
                    testDIS="-";
                    diagnosis=diagnoseID();
                }

            }
            else{
                if (testID==null){
                    //THR=null, DIS=value, ID=null
                    testTHR="-";
                    testID="-";
                    diagnosis=diagnoseDIS();
                }
                else{
                    //THR=null, DIS=value, ID=value
                    testTHR="-";
                    diagnosis="-";
                }
            }
        }

        // option with at least one test done
        else{
            if (testDIS==null){

                if(testID==null){
                    //THR=value, DIS=null, ID=null
                    testDIS="-";
                    testID="-";
                    diagnosis=diagnoseTHR();
                }
                else{
                    //THR=value, DIS=null, ID=value
                    testDIS="-";
                    diagnosis="-";
                }
            }
            else{
                if(testID==null){
                    //THR=value, DIS=value, ID=null
                    testID="-";
                    diagnosis="-";
                }
                else{
                    //THR=value, DIS=value, ID=value
                    diagnosis=diagnoseAll();
                }
            }
        }


        return diagnosis;
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

    public String getTestTHR() {
        return testTHR;
    }

    public String getTestDIS() {
        return testDIS;
    }

    public String getTestID() {
        return testID;
    }
}
