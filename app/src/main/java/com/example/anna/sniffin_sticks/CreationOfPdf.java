package com.example.anna.sniffin_sticks;

import android.os.Environment;
import android.util.Log;

import com.itextpdf.text.Anchor;
import com.itextpdf.text.Chapter;
import com.itextpdf.text.Chunk;
import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Element;
import com.itextpdf.text.Font;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.Section;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

/**
 * Created by Anna on 2016-08-10.
 */
public class CreationOfPdf {

    private Font font_title = new Font(Font.FontFamily.TIMES_ROMAN,20, Font.BOLD);
    private Font font_dataUnderline = new Font(Font.FontFamily.TIMES_ROMAN, 16, Font.UNDERLINE);
    private Font font_data = new Font(Font.FontFamily.TIMES_ROMAN, 16);

    final private String title = "RIECHTEST: Sniffin' Sticks ";
    final private String title_patient = "Patient(in)";
    final private String title_result = "Ergebnis";
    final private String title_data = "1. Daten";


    public void createPdf (){
        Document current_document = new Document();

        try {
            // DIRECTORY and file name

            String filePath = Environment.getExternalStorageDirectory().getAbsolutePath() + "/PDF Files";
            File dir = new File(filePath);
            if (!dir.exists())
                dir.mkdirs();

            Log.d("Pdf Creator", "Pdf path: " + filePath);

            String name = MainActivity.DATA.getName();
            String surname = MainActivity.DATA.getSurname();

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
            String name = MainActivity.DATA.getName();
            String surname = MainActivity.DATA.getSurname();
            String sex = MainActivity.DATA.getSex();
            String researcher = MainActivity.DATA.getResearcher();
            String date = MainActivity.DATA.getDate();
            String hour = MainActivity.DATA.getHour();

            int birth [] = DateParser.parseDate(MainActivity.DATA.getBirth());
            CountAge patientAge = new CountAge();
            int ageInt = patientAge.patientAge(birth[0], birth[1], birth[2]);
            String age = Integer.toString(ageInt);


            String testTHR_total = MainActivity.DATA.getTestTHR_total();
            String testTHR_levels[] = MainActivity.DATA.getTestTHR_levels();
            String testTHR_turningLevels[] =MainActivity.DATA.getTestTHR_turningLevels();
            String testTHR_answers [] = MainActivity.DATA.getTestTHR_answers();

            String testDIS_total = MainActivity.DATA.getTestDIS_total();
            String testDIS_points [] = MainActivity.DATA.getTestDIS_points();
            String testDIS_choices [] = MainActivity.DATA.getTestDIS_choices();

            String testID_total = MainActivity.DATA.getTestID_total();


            String score = MainActivity.DATA.getScore();
            Chunk diagnosis = new Chunk (MainActivity.DATA.getDiagnosis(),font_dataUnderline);

            //First part
            Paragraph par_data_title = new Paragraph(title_data,font_title);
            document.add(par_data_title);

            Paragraph par_data = new Paragraph("Datum: " + date + "     Uhrzeit: "
                    + hour +"     Untersucher: " + researcher, font_data);
            par_data.setAlignment(Element.ALIGN_CENTER);
            document.add(par_data);

            Paragraph par_patient_title = new Paragraph(title_patient, font_dataUnderline);
            document.add(par_patient_title);

            Paragraph par_patient = new Paragraph("Vorname: " + name + "     Name: " +surname+
                    "     Geschlecht: " +sex + "      Alter: " + age, font_data);
            par_patient.setAlignment(Element.ALIGN_CENTER);
            document.add(par_patient);

            Paragraph par_result_title = new Paragraph(title_result, font_dataUnderline);
            document.add(par_result_title);

            Paragraph par_results = new Paragraph("Schwelle: " + testTHR_total+ "     Diskriminierung: "+ testDIS_total
                    + "     Erkennung: " + testID_total, font_data);
            par_results.setAlignment(Element.ALIGN_CENTER);
            document.add(par_results);

            Paragraph par_total = new Paragraph("SDI-Wert: "+ score + "   ", font_data);
            par_total.add(diagnosis);
            par_total.setAlignment(Element.ALIGN_CENTER);
            document.add(par_total);


            // part 2 - threshold
            Paragraph test1 = new Paragraph("2. Schwelle",font_title);
            document.add(test1);

            // part 3 - discrimination
            Paragraph test2 = new Paragraph("3. Diskriminierung",font_title);
            document.add(test2);

            // part 4 - identification
            Paragraph test3 = new Paragraph("4. Erkennung",font_title);
            document.add(test3);
            Paragraph tableID = new Paragraph();
            createtableID(tableID);
            document.add(tableID);


        } catch (DocumentException e) {
            e.printStackTrace();
        }
    }
    public void createtableID (Paragraph paragraph) throws DocumentException {

        String tab_answers[][]= MainActivity.tab_answers;
        String testID_points [] = MainActivity.DATA.getTestID_points();
        String testID_choices [] = MainActivity.DATA.getTestDIS_choices();


        PdfPTable table = new PdfPTable(5);
        table.setWidthPercentage(62);
        float [] columnWidth = {1f,4f,4f,4f,4f};
        table.setWidths(columnWidth);

        for (int i=0; i<16; i++) {
            for (int j=0;j<5;j++) {
                if (j == 0)
                    table.addCell(Integer.toString(i));
                else
                    table.addCell(tab_answers[i][j-1]);
            }
        }
        paragraph.add(table);

        PdfPTable tableScore = new PdfPTable(2);
        tableScore.setWidthPercentage(20);

        for (int i=0; i<16; i++) {
            for (int j = 0; j < 2; j++) {
                if (j==0)
                    tableScore.addCell(testID_points[i]);
                else
                    tableScore.addCell(testID_choices[i]);
            }
        }
        paragraph.add(tableScore);
    }
}
