package com.example.anna.sniffin_sticks;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {

    public static DataStorage DATA;

    public static final String tab_answers[][] = {
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

    public static final String tab_good [] = {"Orange","Schuhleder","Zimt","Pfefferminz","Banane","Zitrone","Lakritz",
            "Terpentin", "Knoblauch","Kaffee","Apfel","Gewürznelke","Ananas","Rose","Anis","Fisch",""};

    private Button new_patient;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        new_patient = (Button) findViewById(R.id.main_new);

        new_patient.setOnClickListener(new Button.OnClickListener() {
            @Override
            public void onClick(View v) {
                clear();
                Intent intent = new Intent(getApplicationContext(), patient_data.class);
                startActivity(intent);
            }
        });

    }

    public void clear (){

        MainActivity.DATA.setTestTHR_total(null);
        MainActivity.DATA.setTestTHR_levels(null);
        MainActivity.DATA.setTestTHR_turningLevels(null);
        MainActivity.DATA.setTestTHR_answers(null);

        MainActivity.DATA.setTestDIS_total(null);
        MainActivity.DATA.setTestDIS_choices(null);
        MainActivity.DATA.setTestDIS_points(null);

        MainActivity.DATA.setTestID_total(null);
        MainActivity.DATA.setTestID_choices(null);
        MainActivity.DATA.setTestID_points(null);

        MainActivity.DATA=null;
    }
}
