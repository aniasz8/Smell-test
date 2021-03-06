package com.example.anna.sniffin_sticks;

import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;


import java.util.ArrayList;

// Copyright (C) <2016> <Anna Szagdaj with the help of Jakub Licznerski>
public class test_threshold extends AppCompatActivity {

    private int level =15;
    private int change =0;
    private int index =0;
    private RadioGroup radioGroup;
    private RadioButton radioButton;
    private Button testTHR_ok;
    private String patient_answer;
    private String yes = "Ja";
    private String testTHR_finalResult;

    ArrayList<Integer> listTHR_answers = new ArrayList<>();
    ArrayList<Integer> listTHR_levels = new ArrayList<>();
    int [] listTHR_turningLevels = new int [8];

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_test_threshold);

        radioGroup =(RadioGroup) findViewById(R.id.testTHR_radioGroup);
        testTHR_ok = (Button) findViewById(R.id.testTHR_button_ok);

        testTHR_ok.setOnClickListener(new Button.OnClickListener() {

            @Override
            public void onClick(View v) {

                if (filledFields()) {

                    // getting the patient's answer
                    int radioButtonID = radioGroup.getCheckedRadioButtonId();
                    radioButton = (RadioButton) findViewById(radioButtonID);
                    patient_answer = radioButton.getText().toString();

                    //  checking if it is a good answer; point = 1 -> answer = yes
                    Question result = new Question(yes, patient_answer);
                    int point = result.result();
                    listTHR_answers.add(index, point);
                    listTHR_levels.add(index, level);


                    //answering
                    Answering current_answering = new Answering(point, change, level, index, listTHR_answers,
                            listTHR_levels, listTHR_turningLevels);
                    index = current_answering.getIndex();
                    level = current_answering.getLevel();
                    index = current_answering.getIndex();
                    change = current_answering.getChange();
                    listTHR_answers = current_answering.getListTHR_answers();
                    listTHR_levels = current_answering.getListTHR_levels();
                    listTHR_turningLevels = current_answering.getListTHR_turningLevels();

                    // check if the test is over
                    // 1) we always answered wrongly  2) change ==7, then we calculate the final score
                    if (level < 1) {
                        int testTHR_score = 1;

                        Intent intent = new Intent(test_threshold.this, select_test.class);
                        MainActivity.DATA.setTestTHR_total(Integer.toString(testTHR_score));

                        startActivity(intent);
                        finish();
                    }
                    if (change == 7) {
                        TestTHR_score testTHR_score_string = new TestTHR_score(listTHR_turningLevels);
                        testTHR_finalResult = testTHR_score_string.testTHR_StringResult();

                        MainActivity.DATA.setTestTHR_answers(ConvertArrayIntToString.convertList(listTHR_answers));
                        MainActivity.DATA.setTestTHR_levels(ConvertArrayIntToString.convertList(listTHR_levels));
                        MainActivity.DATA.setTestTHR_turningLevels(ConvertArrayIntToString.convertArray(listTHR_turningLevels));
                        MainActivity.DATA.setTestTHR_total(testTHR_finalResult);

                        Intent intent = new Intent(test_threshold.this, select_test.class);
                        startActivity(intent);
                    }

                    //setting the new title and uncheck answers
                    radioGroup.clearCheck();

                    if (((TextView) findViewById(R.id.testTHR_title)) != null) {
                        ((TextView) findViewById(R.id.testTHR_title)).setText("Level" + String.valueOf(level));
                    }
                    index++;
                }else
                    AlertDialog();
            }
        });
    }


    private Boolean filledFields(){

        if(radioGroup.getCheckedRadioButtonId()<=0 )
            return false;
        return true;
    }

    private void AlertDialog(){

        AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(test_threshold.this);
        alertDialogBuilder.setMessage("Wählen Sie ein Antwort.").setCancelable(false)
                .setPositiveButton("OK", new DialogInterface.OnClickListener(){

                    public void onClick(DialogInterface dialog, int id){
                        dialog.cancel();
                    }
                });
        AlertDialog alert = alertDialogBuilder.create();
        alert.show();
    }
}
