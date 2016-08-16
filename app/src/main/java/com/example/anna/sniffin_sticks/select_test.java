package com.example.anna.sniffin_sticks;

import android.content.Intent;
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
    private Button selecet_view;
    private Button select_export;
    private Button select_reset;

    private String name;
    private String surname;
    private String birth;
    private String sex;
    private String researcher;
    private String date;
    private String hour;
    private String testID_total;
    private String testTHR_total;
    private String testDIS_total;
    private int age;

    String[] testID_points;
    String[] testID_choices;
    String[] testID_answers;

    String[] testDIS_points;
    String[] testDIS_choices;

    String[] testTHR_turningLevels;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_select_test);

        select_test1 = (Button) findViewById(R.id.select_button_test1);
        select_test2 = (Button) findViewById(R.id.select_button_test2);
        select_test3 = (Button) findViewById(R.id.select_button_test3);
        select_button_score = (Button) findViewById(R.id.select_button_score);
        select_export = (Button) findViewById(R.id.select_button_export);
        selecet_view = (Button) findViewById(R.id.select_button_view);
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

                int birth [] = DateParser.parseDate(MainActivity.DATA.getBirth());
                CountAge patientAge = new CountAge();
                int age = patientAge.patientAge(birth[0], birth[1], birth[2]);
                MainActivity.DATA.setAge(age);

                Diagnosis patientDiagnose = new Diagnosis(age, total_score.resultDouble());
                patientDiagnose.diagnose();

                Toast.makeText(getBaseContext(), "Schwelle: " + total_score.getTestTHR() + "\nDiskriminierung: "
                        + total_score.getTestDIS() + "\nErkennung: " + total_score.getTestID()
                        + "\nErgebnis: " + score +"\n" + Integer.toString(age), Toast.LENGTH_LONG).show();
            }
        });

        // pressing button reset erases data and go to the beginning
        select_reset.setOnClickListener(new Button.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), MainActivity.class);
                startActivity(intent);
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

                //other way
                String name = MainActivity.DATA.getName();
                String surname = MainActivity.DATA.getSurname();
                String birth = MainActivity.DATA.getBirth();


                Toast.makeText(getApplicationContext(), name + surname +birth, Toast.LENGTH_SHORT).show();

                //CreationOfPdf pdf = new CreationOfPdf();
                //pdf.createPdf();
                Toast.makeText(getApplicationContext(), "Pdf created", Toast.LENGTH_SHORT).show();
            }
        });


//        selecet_view.setOnClickListener(new Button.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                viewPdf();
//            }
//        });
//    }
//    public void viewPdf(){
//        Intent intent_doc = new Intent(Intent.ACTION_VIEW);
//
//        String filePath_view = Environment.getExternalStorageDirectory().getAbsolutePath() + "/PDF Files";
//        File file_view = new File(filePath_view,name + surname  + ".pdf");
//
//        intent_doc.setDataAndType(Uri.fromFile(file_view),"Pdf in application");
//        startActivity(intent_doc);
//    }
    }
}
