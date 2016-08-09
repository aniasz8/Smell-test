package com.example.anna.sniffin_sticks;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.itextpdf.text.Document;


public class select_test extends AppCompatActivity {

    private Button select_test1;
    private Button select_test2;
    private Button select_test3;
    private Button select_button_score;
    private Button selecet_view;
    private Button select_export;
    //private int number;
   // private TextView view_name;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_select_test);

        select_test1 = (Button) findViewById(R.id.select_button_test1);
        select_test2 = (Button) findViewById(R.id.select_button_test2);
        select_test3 = (Button) findViewById(R.id.select_button_test3);
        select_button_score =(Button) findViewById(R.id.select_button_score);
        select_export = (Button) findViewById(R.id.select_button_export);
        selecet_view = (Button) findViewById(R.id.select_button_view);

        // presssing score causes getting values from other activities

        select_button_score.setOnClickListener(new Button.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = getIntent();
                Bundle b = intent.getExtras();
                if (b!=null){
                    String name = (String) b.get("name");
                    String surname = (String) b.get("surname");
                    String birth = (String) b.get("birth");
                    String sex = (String) b.get("sex");
                    String researcher = (String) b.get("researcher");
                    String date = (String) b.get("date");
                    String hour = (String) b.get("hour");
                    String testID_total = (String ) b.get("testID_total");
                    String testTHR_total = (String) b.get("testTHR_score");
                    int [] testID_points_strings = b.getIntArray("testID_points_string");
                    int [] testID_points = b.getIntArray("testID_points");
                    String [] testID_choices = b.getStringArray("testID_choices");
                    // String [] testID_answers = b.getStringArray("testID_answers");
                    Toast.makeText(getBaseContext(), "test THR: " + testTHR_total, Toast.LENGTH_SHORT).show();
                }
            }
        });
        select_test1.setOnClickListener(new Button.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), test_threshold.class);
                startActivity(intent);
            }
        });

        select_test2.setOnClickListener(new Button.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), test_discrimination.class);
                startActivity(intent);
            }
        });


        select_test3.setOnClickListener(new Button.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), test_identification.class);
                startActivity(intent);
            }
        });

        //generating Pdf

        select_export.setOnClickListener(new Button.OnClickListener() {
            @Override
            public void onClick(View v) {
                createPdf();
            }
        });

        selecet_view.setOnClickListener(new Button.OnClickListener() {
            @Override
            public void onClick(View v) {
                viewPdf();
            }
        });

    }
    public void createPdf (){
        Document current_document = new Document();

    }

    public void viewPdf(){

    }

}
