package com.example.anna.sniffin_sticks;

import android.os.Environment;
import android.util.Log;

import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Font;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.PdfWriter;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

/**
 * Created by Anna on 2016-08-10.
 */
public class CreationOfPdf {

    private String name;
    private String surname;
    private String birth;
    private String sex;
    private String researcher;
    private String date;
    private String hour;
    private String testTHR_total;
    private String testDIS_total;
    private String testID_total;

    private String title = "RIECHTEST: Sniffin' sticks ";
    private String title_patient = "Patient(in)";
    private String title_result = "Ergebnis";
    private String title_data = "Daten";



    public CreationOfPdf (String name, String surname, String birth, String sex, String researcher,
                          String date, String hour, String testTHR_total, String testDIS_total, String testID_total){
        this.name=name;
        this.surname=surname;
        this.birth = birth;
        this.sex = sex;
        this.researcher=researcher;
        this.date = date;
        this.hour = hour;
        this.testTHR_total = testTHR_total;
        this.testDIS_total = testDIS_total;
        this.testID_total = testID_total;

    }
    public void createPdf (){
        Document current_document = new Document();

        try {
            // DIRECTORY and file name

            String filePath = Environment.getExternalStorageDirectory().getAbsolutePath() + "/PDF Files";
            File dir = new File(filePath);
            if (!dir.exists())
                dir.mkdirs();

            Log.d("Pdf Creator", "Pdf path: " + filePath);

            File file = new File(dir, name + surname + ".pdf");
            FileOutputStream fileOut = new FileOutputStream(file);

            PdfWriter.getInstance(current_document, fileOut);

            current_document.open();
            addTitle(current_document);


        } catch (DocumentException de) {
            Log.e("Pdf Creator", "Pdf path: " + de);
        }catch (IOException e){
            Log.e("Pdf Creator", "Pdf path: " + e);
        }finally {
            current_document.close();
        }
    }

    public void addTitle (Document document){
        try {
            Paragraph paragraph1 = new Paragraph(title);
            Font paragraph1_font = new Font();
            paragraph1_font.setSize(20);
            paragraph1_font.setStyle(Font.BOLD);

            paragraph1.setAlignment(Paragraph.ALIGN_CENTER);
            paragraph1.setFont(paragraph1_font);

            document.add(paragraph1);

        }catch(DocumentException de){
            Log.e("Pdf Creator", "Pdf path: " + de);
        }
    }
}
