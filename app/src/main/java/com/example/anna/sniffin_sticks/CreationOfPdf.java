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

            Paragraph par_patient = new Paragraph("Vorname: " + "Name: ", font_data);
            document.add(par_patient);

            Paragraph par_result_title = new Paragraph(title_result, font_dataUnderline);
            document.add(par_result_title);

            Paragraph par_results = new Paragraph("Schwelle: " + "Diskriminierung: " + "Ergebnis: ", font_data);
            document.add(par_results);

            Paragraph par_total = new Paragraph("SDI Ergebnis: " + "rozpoznanie", font_data);
            par_total.setAlignment(Paragraph.ALIGN_CENTER);
            document.add(par_total);

            Paragraph test1 = new Paragraph("2. Schwelle",font_title);
            document.add(test1);

            Paragraph test2 = new Paragraph("3. Diskriminierung",font_title);
            document.add(test2);

            Paragraph test3 = new Paragraph("4. Erkennung",font_title);
            document.add(test3);

        } catch (DocumentException e) {
            e.printStackTrace();
        }


    }
}
