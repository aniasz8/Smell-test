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

        // single tests and none
        // THR==null, DIS==null, ID== value // or everything "-"
        if (testTHR==null){
            if (testDIS==null){
                if(testID==null){
                    diagnosis="-";
                    testTHR="-";
                    testDIS="-";
                    testID="-";
                }
                else{
                    diagnosis=diagnoseID();
                    testTHR="-";
                    testDIS="-";
                }
            }
        }

        //THR==value, DIS== null, ID==null
        if (testDIS==null){
            if (testID==null){
                if(testTHR!=null) {
                    diagnosis = diagnoseTHR();
                    testDIS="-";
                    testID="-";
                }
            }
        }

        //THR==null, DIS== value, ID==null
        if (testID==null){
            if (testTHR==null){
                if (testDIS!=null){
                    diagnosis=diagnoseDIS();
                    testTHR="-";
                    testID="-";
                }
            }
        }


        // double tests and all
        // THR= value, DIS= value, ID= null or all
        if (testTHR!=null){
            if (testDIS!=null){
                if (testID!=null){
                    diagnosis=diagnoseAll();
                }
                else{
                    diagnosis="-";
                    testID="-";
                }
            }
        }

        //THR= null, DIS=value, ID=value
        if(testTHR==null){
            if(testDIS!=null){
                if(testID!=null){
                    diagnosis="-";
                    testTHR="-";
                }
            }
        }

        //THR= value, DIS= null, ID= value
        if(testTHR!=null){
            if(testDIS==null){
                if(testID!=null){
                    diagnosis="-";
                    testDIS="-";
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
