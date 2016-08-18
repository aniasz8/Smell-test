package com.example.anna.sniffin_sticks;

import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Arrays;

public class test_identification extends AppCompatActivity {

    private Button testID_ok;
    private int counter = 0;
    private RadioGroup radioGroup;
    private RadioButton radioButton;
    private String patient_choice;
    private int testID_total;
    private String [][] tab_answers;
    private String [] tab_good;


    int tab_points [] = new int [16];
    String tab_testID_choices []= new String [16];
    String tab_points_string [] = new String[16];


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_test_identification);

        testID_ok = (Button) findViewById(R.id.testID_ok);
        radioGroup =(RadioGroup) findViewById(R.id.testID_radiogroup);

        tab_answers= MainActivity.tab_answers;
        tab_good = MainActivity.tab_good;

        //setting initial values

        ((TextView) findViewById(R.id.testID_counter)).setText(String.valueOf(counter+1));

        if (( findViewById(R.id.testID_choice1)) != null) {
            ((Button) findViewById(R.id.testID_choice1)).setText(tab_answers[counter][0]);
        }
        if (( findViewById(R.id.testID_choice2)) != null) {
            ((Button) findViewById(R.id.testID_choice2)).setText(tab_answers[counter][1]);
        }
        if (((Button) findViewById(R.id.testID_choice3)) != null) {
            ((Button) findViewById(R.id.testID_choice3)).setText(tab_answers[counter][2]);
        }
        if (((Button) findViewById(R.id.testID_choice4)) != null) {
            ((Button) findViewById(R.id.testID_choice4)).setText(tab_answers[counter][3]);
        }


        //action caused by pressing ok

        testID_ok.setOnClickListener(new Button.OnClickListener() {
            @Override
            public void onClick(View v) {

                //getting text from selected radio button

                if (filledFields()) {
                    int radioButtonID = radioGroup.getCheckedRadioButtonId();
                    radioButton = (RadioButton) findViewById(radioButtonID);
                    patient_choice = radioButton.getText().toString();


                    // using Question () to evaluate the result and put it to the tables (of int and strings)

                    Question result = new Question(patient_choice, tab_good[counter]);
                    int point = result.result();
                    tab_points[counter] = point;
                    tab_points_string[counter] = Integer.toString(point);
                    tab_testID_choices[counter] = patient_choice;

                    // changing the value of counter = consequences

                    if (counter == 15) {
                        for (int i : tab_points)
                            testID_total += i;

                        MainActivity.DATA.setTestID_choices(tab_testID_choices);
                        MainActivity.DATA.setTestID_points(tab_points_string);
                        MainActivity.DATA.setTestID_total(Integer.toString(testID_total));

                        Intent intent = new Intent(test_identification.this, select_test.class);
                        startActivity(intent);


                    } else {
                        counter++;

                        ((TextView) findViewById(R.id.testID_counter)).setText(String.valueOf(counter + 1));

                        if (((Button) findViewById(R.id.testID_choice1)) != null) {
                            ((Button) findViewById(R.id.testID_choice1)).setText(tab_answers[counter][0]);
                        }
                        if (((Button) findViewById(R.id.testID_choice2)) != null) {
                            ((Button) findViewById(R.id.testID_choice2)).setText(tab_answers[counter][1]);
                        }
                        if (((Button) findViewById(R.id.testID_choice3)) != null) {
                            ((Button) findViewById(R.id.testID_choice3)).setText(tab_answers[counter][2]);
                        }
                        if (((Button) findViewById(R.id.testID_choice4)) != null) {
                            ((Button) findViewById(R.id.testID_choice4)).setText(tab_answers[counter][3]);
                        }
                    }
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

        AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(test_identification.this);
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



