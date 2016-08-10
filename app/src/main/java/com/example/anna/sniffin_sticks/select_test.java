package com.example.anna.sniffin_sticks;

import android.content.Intent;
import android.net.Uri;
import android.os.Environment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Font;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.PdfWriter;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;


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
        select_reset = (Button) findViewById(R.id.select_button_reset);

        // presssing score causes getting values from other activities
        select_button_score.setOnClickListener(new Button.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = getIntent();
                Bundle b = intent.getExtras();
                if (b!=null){
                    testID_total = (String ) b.get("testID_total");
                    testTHR_total = (String) b.get("testTHR_score");
                    testDIS_total = (String) b.get("testDIS_total");

                    TotalScore total_score = new TotalScore(testTHR_total,testDIS_total,testID_total);
                    int score = total_score.totalResult();

                    Toast.makeText(getBaseContext(), "Schwelle: " + total_score.getTestTHR() + "\nDiskriminierung: "
                            + total_score.getTestDIS() + "\nErkennung: " + total_score.getTestID()
                            + "\nErgebnis: " + Integer.toString(score), Toast.LENGTH_LONG).show();
                }
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
                Intent intent = getIntent();
                Bundle b = intent.getExtras();
                if (b!=null){
                    name = (String) b.get("name");
                    surname = (String) b.get("surname");
                    birth = (String) b.get("birth");
                    sex = (String) b.get("sex");
                    researcher = (String) b.get("researcher");
                    date = (String) b.get("date");
                    hour = (String) b.get("hour");
                    testID_total = (String ) b.get("testID_total");
                    testTHR_total = (String) b.get("testTHR_score");
                    int [] testID_points_strings = b.getIntArray("testID_points_string");
                    int [] testID_points = b.getIntArray("testID_points");
                    String [] testID_choices = b.getStringArray("testID_choices");
                    // String [] testID_answers = b.getStringArray("testID_answers");
                }
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


    // method to create a Pdf

    public void createPdf (){
        Document current_document = new Document();

        try {
            String filePath = Environment.getExternalStorageDirectory().getAbsolutePath() + "/PDF Files";
            File dir = new File(filePath);
            if (!dir.exists())
                dir.mkdirs();

            Log.d("Pdf Creator", "Pdf path: " + filePath);

            File file = new File(dir, name + surname + ".pdf");
            FileOutputStream fileOut = new FileOutputStream(file);

            PdfWriter.getInstance(current_document, fileOut);

            current_document.open();

            // creating content
            Paragraph paragraph1 = new Paragraph("It's a test of" + name + surname);
            Font paragraph1_font = new Font();
            paragraph1_font.setSize(16);
            paragraph1_font.setStyle(Font.BOLD);

            paragraph1.setAlignment(Paragraph.ALIGN_CENTER);
            paragraph1.setFont(paragraph1_font);

            current_document.add(paragraph1);

            Toast.makeText(getApplicationContext(), "Pdf created", Toast.LENGTH_SHORT).show();

        } catch (DocumentException de) {
            Log.e("Pdf Creator", "Pdf path: " + de);
        }catch (IOException e){
            Log.e("Pdf Creator", "Pdf path: " + e);
        }finally {
            current_document.close();
        }
    }

    public void viewPdf(){
        Intent intent_doc = new Intent(Intent.ACTION_VIEW);

        String filePath_view = Environment.getExternalStorageDirectory().getAbsolutePath() + "/PDF Files";
        File file_view = new File(filePath_view,name + surname  + ".pdf");

        intent_doc.setDataAndType(Uri.fromFile(file_view),"Pdf in application");
        startActivity(intent_doc);
    }

}
