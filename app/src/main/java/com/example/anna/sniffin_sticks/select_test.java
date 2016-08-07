package com.example.anna.sniffin_sticks;

import android.content.Intent;
import android.graphics.pdf.PdfDocument;
import android.os.Environment;
import android.print.PrintAttributes;
import android.print.pdf.PrintedPdfDocument;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.StreamCorruptedException;


public class select_test extends AppCompatActivity {

    private Button test1;
    private Button test3;
    private Button score;
    private Button export;
    private int number;
   // private TextView view_name;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_select_test);

        test1 = (Button) findViewById(R.id.select_button_test1);
        test3 = (Button) findViewById(R.id.select_button_test3);
        score =(Button) findViewById(R.id.select_button_score);
        export = (Button) findViewById(R.id.select_button_export);

        // presssing score causes getting values from other activities

        score.setOnClickListener(new Button.OnClickListener() {
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

        export.setOnClickListener(new Button.OnClickListener() {
            @Override
            public void onClick(View v) {

                File FolderName = new File(Environment.getExternalStorageDirectory(),"NewFolder");
                File FileMade = new File(FolderName, "File_test" + number +".pdf");
                if (!FolderName.exists()) {
                    FolderName.mkdirs();
                }

                try {
                    FileMade.createNewFile();
                } catch (IOException e) {
                    e.printStackTrace();
                }

               PrintAttributes printAttributes = new PrintAttributes.Builder().setColorMode(PrintAttributes.COLOR_MODE_MONOCHROME)
                       .setMediaSize(PrintAttributes.MediaSize.ISO_A4)
                       .setMinMargins(new PrintAttributes.Margins(15,15,15,15)).build();

                PrintedPdfDocument Doc= new PrintedPdfDocument(getApplicationContext(), printAttributes);

                int docX,docY;
                int viewX, viewY;
                float scaleX, scaleY;

                docY = Doc.getPageHeight();
                docX = Doc.getPageWidth();

                View our_view = findViewById(R.id.pdf_layout);
                viewX = our_view.getWidth();
                viewY = our_view.getHeight();

                scaleX = (float) docX/viewX;
                scaleY = (float) docY/viewY;

                PdfDocument.Page our_page = Doc.startPage(0);

                Doc.finishPage(our_page);

                try {
                    Doc.writeTo(new FileOutputStream("File_test"));
                } catch (FileNotFoundException e){
                    e.printStackTrace();
                } catch (IOException e){
                    e.printStackTrace();
                }

                Doc.close();

                number++;
            }
        });

        test1.setOnClickListener(new Button.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), test_threshold.class);
                startActivity(intent);
            }
        });


        test3.setOnClickListener(new Button.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), test_identification.class);
                startActivity(intent);
            }
        });


    }
}