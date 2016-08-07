package com.example.anna.sniffin_sticks;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;

public class test_identification extends AppCompatActivity {

    private Button testID_ok;
    private int counter = 0;
    private RadioGroup radioGroup;
    private RadioButton radioButton;
    private String patient_choice;
    private int testID_total;

    // tables


    String tab_answers[][] = {
            {"Orange", "Brombeere", "Erdbeere", "Ananas"},
            {"Rauch", "Schuhleder", "Klebstoff", "Gras"},
            {"Honig", "Vanille", "Zimt", "Schokolade"},
            {"Schnittlauch", "Zwiebel", "Fichte", "Pfefferminz"},
            {"Kokos", "Kirsche", "Walnuss", "Banane",},
            {"Pfirsich", "Apfel", "Zitrone", "Grapefruit"},
            {"Gummibär", "Lakritz", "Kaugummi", "Kekse"},
            {"Terpentin", "Gummi", "Menthol", "Senf"},
            {"Knoblauch", "Zwiebel", "Sauerkraut", "Möhren"},
            {"Zigarette", "Kaffee", "Wein", "Kerzenrauch"},
            {"Melone", "Pfirsich", "Apfel", "Orange"},
            {"Senf", "Pfeffer", "Zimt", "Gewürznelke"},
            {"Birne", "Pflaume", "Pfirsich", "Ananas"},
            {"Kamille", "Himbeere", "Rose", "Kirsche"},
            {"Rum", "Anis", "Honig", "Fichte"},
            {"Fisch", "Brot", "Käse", "Schinken"},
            {"", "", "", ""}
    };
    String tab_good [] = {"Orange","Schuhleder","Zimt","Pfefferminz","Banane","Zitrone","Lakritz","Terpentin",
            "Knoblauch","Kaffee","Apfel","Gewürznelke","Ananas","Rose","Anis","Fisch",""};

    int tab_points [] = new int [16];
    String tab_testID_choices []= new String [16];


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_test_identification);

        testID_ok = (Button) findViewById(R.id.testID_ok);
        radioGroup =(RadioGroup) findViewById(R.id.testID_radiogroup);

        //setting initial values

        ((TextView) findViewById(R.id.testID_counter)).setText(String.valueOf(counter+1));

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


        //action caused by pressing ok

        testID_ok.setOnClickListener(new Button.OnClickListener() {
            @Override
            public void onClick(View v) {

                //getting text from selected radio button

                int radioButtonID = radioGroup.getCheckedRadioButtonId();
                radioButton = (RadioButton) findViewById(radioButtonID);
                patient_choice = radioButton.getText().toString();


                // using Question () to evaluate the result and put it to the tables (of int and strings)

                Question result = new Question(patient_choice, tab_good[counter]);
                int point = result.result();
                tab_points[counter]=point;
                tab_testID_choices[counter]=patient_choice;


                // changing the value of counter = consequences

                if (counter == 15) {
                    for (int i : tab_points)
                        testID_total += i;

                    //Toast.makeText(getApplicationContext(), Integer.toString(testID_total), Toast.LENGTH_SHORT).show();

                    Intent intent = new Intent(test_identification.this, select_test.class);
                    intent.putExtra("testID_total", Integer.toString(testID_total));
                    intent.putExtra("testID_points", tab_points);
                    intent.putExtra("testID_choices", tab_testID_choices);
                    //intent.putExtra("testID_answers", tab_answers);
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
            }
        });
    }
}



