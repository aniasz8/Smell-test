package com.example.anna.sniffin_sticks;

import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;


public class select_test extends AppCompatActivity {

    private Button select_test1;
    private Button select_test2;
    private Button select_test3;
    private Button select_button_score;
    private Button select_export;
    private Button select_reset;
    private String message = "Nicht alle Teste sind gemacht";
    private String diagnose;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_select_test);

        select_test1 = (Button) findViewById(R.id.select_button_test1);
        select_test2 = (Button) findViewById(R.id.select_button_test2);
        select_test3 = (Button) findViewById(R.id.select_button_test3);
        select_button_score = (Button) findViewById(R.id.select_button_score);
        select_export = (Button) findViewById(R.id.select_button_export);
        select_reset = (Button) findViewById(R.id.select_button_reset);

        // presssing score causes getting values from other activities
        select_button_score.setOnClickListener(new Button.OnClickListener() {
            @Override
            public void onClick(View v) {

                String testID_total = MainActivity.DATA.getTestID_total();
                String testDIS_total = MainActivity.DATA.getTestDIS_total();
                String testTHR_total = MainActivity.DATA.getTestTHR_total();

                TotalScore total_score = new TotalScore(testTHR_total, testDIS_total, testID_total);
                String score = total_score.totalResult();
                MainActivity.DATA.setScore(score);
                Diagnosis patientDiagnose = new Diagnosis(total_score.resultDouble(),
                        testTHR_total, testDIS_total, testID_total);

                diagnose = patientDiagnose.diagnose();
                MainActivity.DATA.setDiagnosis(diagnose);

                Toast.makeText(getBaseContext(), "Schwelle: " + patientDiagnose.getTestTHR() + "\nDiskriminierung: "
                        + patientDiagnose.getTestDIS() + "\nErkennung: " + patientDiagnose.getTestID()
                        + "\nSDI-Wert: " + score + "\n" + MainActivity.DATA.getDiagnosis(), Toast.LENGTH_LONG).show();
            }
        });

        // pressing button reset erases data and go to the beginning
        select_reset.setOnClickListener(new Button.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertReset();
            }
        });

        //selection of tests
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

//                if (MainActivity.DATA.getTestID_total() == null) {
//                    if (MainActivity.DATA.getTestDIS_total() == null) {
//                        AlertDialogTHR();
//                    }
//                }
//                if (MainActivity.DATA.getTestDIS_total()==null){
//                    if (MainActivity.DATA.getTes)
//                }
//
////                } else {
//
////                }

                CreationOfPdf pdf = new CreationOfPdf();
                pdf.createPdf();
                Toast.makeText(getApplicationContext(), "Pdf ist gemacht.", Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void AlertDialog(){

        android.support.v7.app.AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(select_test.this);
        alertDialogBuilder.setMessage(message).setCancelable(false)
                .setPositiveButton("OK", new DialogInterface.OnClickListener(){

                    public void onClick(DialogInterface dialog, int id){
                        dialog.cancel();
                    }
                });
        AlertDialog alert = alertDialogBuilder.create();
        alert.show();
    }

    private void AlertReset (){
        AlertDialog.Builder builder1 = new AlertDialog.Builder(select_test.this);
        builder1.setMessage("Möchten Sie alle datum löschen?");
        builder1.setCancelable(true);

        builder1.setPositiveButton(
                "Ja",
                new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {

                        clear();
                        Intent intent = new Intent(getApplicationContext(), MainActivity.class);
                        startActivity(intent);
                        dialog.cancel();
                    }
                });

        builder1.setNegativeButton(
                "Nein",
                new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        dialog.cancel();
                    }
                });

        AlertDialog alert11 = builder1.create();
        alert11.show();
    }
    public void clear (){
        MainActivity.DATA.setDiagnosis(null);
        MainActivity.DATA.setScore(null);

        MainActivity.DATA.setTestTHR_total(null);
        MainActivity.DATA.setTestTHR_levels(null);
        MainActivity.DATA.setTestTHR_turningLevels(null);
        MainActivity.DATA.setTestTHR_answers(null);

        MainActivity.DATA.setTestDIS_choices(null);
        MainActivity.DATA.setTestDIS_points(null);
        MainActivity.DATA.setTestDIS_points(null);

        MainActivity.DATA.setTestID_total(null);
        MainActivity.DATA.setTestID_choices(null);
        MainActivity.DATA.setTestID_points(null);

        MainActivity.DATA=null;
    }
}
