package com.example.anna.sniffin_sticks;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Arrays;

public class test_discrimination extends AppCompatActivity {

    private RadioGroup radioGroup;
    private RadioButton radioButton;
    private Button testDIS_ok;
    private int counter = 0;
    private String patient_choice;
    private int testDIS_total;

    int tab_DISpoints [] = new int [16];
    String tab_testDIS_choices []= new String [16];
    String tabDIS_points_string [] = new String[16];


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_test_discrimination);

        testDIS_ok = (Button) findViewById(R.id.testDIS_ok);
        radioGroup =(RadioGroup) findViewById(R.id.testDIS_radiogroup);

        ((TextView) findViewById(R.id.testDIS_counter)).setText(String.valueOf(counter+1));


        testDIS_ok.setOnClickListener(new Button.OnClickListener() {
            @Override
            public void onClick(View v) {

                //getting text from selected radio button

                int radioButtonID = radioGroup.getCheckedRadioButtonId();
                radioButton = (RadioButton) findViewById(radioButtonID);
                patient_choice = radioButton.getText().toString();


                // using Question () to evaluate the result and put it to the tables (of int and strings)

                Question result = new Question(patient_choice, "Green");
                int point = result.result();
                tab_DISpoints[counter]=point;
                tab_testDIS_choices[counter]=patient_choice;
                tabDIS_points_string [counter]=Integer.toString(point);

                if (counter == 15) {
                    for (int i : tab_DISpoints)
                        testDIS_total += i;

                    Intent intent = new Intent(test_discrimination.this, select_test.class);
                    intent.putExtra("testDIS_total", Integer.toString(testDIS_total));
                    intent.putExtra("testDIS_points", tabDIS_points_string);
                    intent.putExtra("testDIS_choices", tab_testDIS_choices);
                    startActivity(intent);

                } else {
                    counter++;
                    ((TextView) findViewById(R.id.testDIS_counter)).setText(String.valueOf(counter+1));
                }
            }
        });
    }
}
