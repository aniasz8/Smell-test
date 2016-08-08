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

import java.text.NumberFormat;
import java.util.ArrayList;

public class test_threshold extends AppCompatActivity {

    private int level =15;
    private int change =0;
    private int index =0;
    private RadioGroup radioGroup;
    private RadioButton radioButton;
    private Button testTHR_ok;
    private String patient_answer;
    private String yes = "YES";
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

                // getting the patient's answer

                int radioButtonID = radioGroup.getCheckedRadioButtonId();
                radioButton = (RadioButton) findViewById(radioButtonID);
                patient_answer = radioButton.getText().toString();

                //  checking if it is a good answer; point = 1 -> answer = yes

                Question result = new Question(yes, patient_answer);
                int point = result.result();
                listTHR_answers.add(index, point);
                listTHR_levels.add(index,level);


                // check if the test is over
                // 1) we always answered wrongly
                // 2) change ==7, then we calculate the final score
                if (level == -1) {
                    int testTHR_score = 1;
                    Intent intent = new Intent(test_threshold.this, select_test.class);
                    intent.putExtra("testTHR_score",Integer.toString(testTHR_score));
                    startActivity(intent);
                }
                if (change==7) {
                    TestTHR_score testTHR_score_string = new TestTHR_score(listTHR_turningLevels);
                    testTHR_finalResult = testTHR_score_string.testTHR_StringResult();
                    Intent intent = new Intent(test_threshold.this, select_test.class);
                    intent.putExtra("testTHR_score",testTHR_finalResult);
                    startActivity(intent);
                }

                // then we check if we are after first of second answering
                else {
                    // if we are after second answering
                    if (index>=1) {

                        switch (point) {
                            case 0:
                                // check if previous there was any good answer- if not, decrese twice
                                if (!listTHR_answers.contains(1)){
                                    level = level - 2;
                                }

                                // if it is a wrong answer during the test - decrease just once and sometimes increase change
                                else {
                                    //check what was before 0
                                    int previous = listTHR_answers.get(index - 1);

                                    switch (previous) {
                                        case 1:
                                            // if there is 10 - it means we should increase change and add level to table
                                            listTHR_turningLevels[change]=level;
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
                                    switch (point){
                                        case 1:
                                            if (listTHR_levels.get(index) == listTHR_levels.get(index - 1)) {

                                                // if they were = going to higher level
                                                level = level + 1;

                                                //if there is 110 = increase change
                                                if (listTHR_answers.get(index - 2) == 0) {

                                                    listTHR_turningLevels[change]=level-1;
                                                    change = change + 1;
                                                }
                                            }
                                            break;

                                        case 0:
                                            listTHR_turningLevels[change]=level;
                                            level = level -1;
                                            change = change +1;
                                            break;
                                    }
                                }
                                // otherwise level stays the same
                                break;
                        }
                    }
                    // or we are just after first answering
                    else {
                        switch (point){
                            case 1:
                                break;
                            case 0:
                                level=level-2;
                                break;
                        }
                    }
                }


                //setting the title
                if (((TextView) findViewById(R.id.testTHR_title)) != null) {
                    ((TextView) findViewById(R.id.testTHR_title)).setText("Level" + String.valueOf(level));
                }

                index++;
            }
        });



    }
}
