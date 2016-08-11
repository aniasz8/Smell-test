package com.example.anna.sniffin_sticks;

import android.os.Environment;
import android.util.Log;

import com.itextpdf.text.Anchor;
import com.itextpdf.text.Chapter;
import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Font;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.Section;
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

    private Font font_title = new Font(Font.FontFamily.TIMES_ROMAN,20, Font.BOLD);
    private Font font_dataUnderline = new Font(Font.FontFamily.TIMES_ROMAN, 16, Font.UNDERLINE);
    private Font font_data = new Font(Font.FontFamily.TIMES_ROMAN, 16);


    private String title = "RIECHTEST: Sniffin' sticks ";
    private String title_patient = "Patient(in)";
    private String title_result = "Ergebnis";
    private String title_data = "1. Daten";



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
            addData(current_document);


        } catch (DocumentException de) {
            Log.e("Pdf Creator", "Pdf path: " + de);
        }catch (IOException e){
            Log.e("Pdf Creator", "Pdf path: " + e);
        }finally {
            current_document.close();
        }
    }

    public void addTitle (Document document) {
        try {
            Paragraph par_title = new Paragraph(title, font_title);
            Font paragraph1_font = new Font();
            paragraph1_font.setSize(20);

            par_title.setAlignment(Paragraph.ALIGN_CENTER);
            par_title.setFont(paragraph1_font);

            document.add(par_title);

        }catch(DocumentException de){
            Log.e("Pdf Creator", "Pdf path: " + de);
        }
    }

    public void addData (Document document)  {

        try {
            //First part
            Paragraph par_data_title = new Paragraph(title_data,font_title);
            document.add(par_data_title);
            Paragraph par_data = new Paragraph("Datum: ", font_data);
            document.add(par_data);
            Paragraph par_patient_title = new Paragraph(title_patient, font_dataUnderline);
            document.add(par_patient_title);



            //First part
            Chapter chapter = new Chapter(new Paragraph(title_data, font_title), 1);

            Paragraph subData_patient = new Paragraph("Patient(in)", font_dataUnderline);
            Section section = chapter.addSection(subData_patient);
            section.add(new Paragraph("Name: " + name, font_data));

            subData_patient = new Paragraph("Ergebnis", font_dataUnderline);
            section = chapter.addSection(subData_patient);
            section.add(new Paragraph("Schwelle: ", font_data));

            document.add(chapter);


            // Test 1 part
            chapter = new Chapter(new Paragraph("Schwelle", font_title), 2);

            document.add(chapter);

            //Test 2 Part
            chapter = new Chapter(new Paragraph("Diskriminierung",font_title),3);

            document.add(chapter);

            //Test 3 part
            chapter = new Chapter(new Paragraph("Erkennung",font_title), 4);

            document.add(chapter);

        } catch (DocumentException e) {
            e.printStackTrace();
        }


    }
}
