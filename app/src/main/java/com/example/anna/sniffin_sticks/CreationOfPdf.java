package com.example.anna.sniffin_sticks;

import android.graphics.Color;
import android.graphics.Paint;
import android.os.Environment;
import android.util.Log;

import com.itextpdf.text.Anchor;
import com.itextpdf.text.BaseColor;
import com.itextpdf.text.Chapter;
import com.itextpdf.text.Chunk;
import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Element;
import com.itextpdf.text.Font;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.Phrase;
import com.itextpdf.text.Section;
import com.itextpdf.text.html.WebColors;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

import static android.graphics.Color.GRAY;
import static android.graphics.Color.GREEN;

/**
 * Created by Anna on 2016-08-10.
 */
public class CreationOfPdf {

    private Font font_title = new Font(Font.FontFamily.TIMES_ROMAN,14, Font.BOLD);
    private Font font_dataUnderline = new Font(Font.FontFamily.TIMES_ROMAN, 11, Font.UNDERLINE);
    private Font font_data = new Font(Font.FontFamily.TIMES_ROMAN, 11);
    private Font font_dataBold = new Font(Font.FontFamily.TIMES_ROMAN, 11, Font.BOLD);
    private Font font_dataBoldUnder = new Font(Font.FontFamily.TIMES_ROMAN, 11, Font.BOLD|Font.UNDERLINE);

    final private String title = "RIECHTEST: Sniffin' Sticks ";
    final private String title_patient = "Patient(in)";
    final private String title_result = "Ergebnis";
    final private String title_data = "1. Daten";

    public static String mColor = "#e9e8e4";
    private int red = 217;
    private int blue = 219;
    private int green = 219;



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

    // title
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

    //content
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
            Paragraph tableDIS = new Paragraph();
            createtableDIS(tableDIS);
            document.add(tableDIS);

            // part 4 - identification
            Paragraph test3 = new Paragraph("4. Erkennung",font_title);
            document.add(test3);
            Paragraph tableID = new Paragraph();
            createtableID(tableID);
            document.add(tableID);
//            Paragraph IDscore = new Paragraph(MainActivity.DATA.getTestID_total());
//            document.add(IDscore);


        } catch (DocumentException e) {
            e.printStackTrace();
        }
    }

    public void createtableDIS (Paragraph paragraph) throws DocumentException {

        String testDIS_points [] = MainActivity.DATA.getTestDIS_points();
        String testDIS_choices [] = MainActivity.DATA.getTestDIS_choices();

        PdfPTable mainDIS = new PdfPTable(2);
        mainDIS.setWidthPercentage(55.0f);
        mainDIS.setSpacingBefore(10f);
        mainDIS.setSpacingAfter(10f);
        float [] columnDIS = {10f,1f};
        mainDIS.setWidths(columnDIS);

        //first table
        PdfPCell cell1 = new PdfPCell();
        cell1.setBorder(PdfPCell.NO_BORDER);

        PdfPTable tableAnsDIS = new PdfPTable(4);
        tableAnsDIS.setWidthPercentage(100.0f);
        float [] columnWidth = {1f,4f,4f,4f};
        tableAnsDIS.setWidths(columnWidth);

        for (int i=0; i<16; i++) {
            for (int j = 0; j < 4; j++) {
                if (j == 0) {
                    PdfPCell num = new PdfPCell(new Phrase(Integer.toString(i+1), font_data));
                    num.setVerticalAlignment(Element.ALIGN_CENTER);
                    tableAnsDIS.addCell(num);
                }

                if (j== 1) {
                    if(testDIS_choices[i].equals("Blue")){
                        PdfPCell blueCH = new PdfPCell(new Phrase("blue",font_data));
                        blueCH.setBackgroundColor(new BaseColor(red,green,blue));
                        tableAnsDIS.addCell(blueCH);
                    }
                    else{
                        PdfPCell blue = new PdfPCell(new Phrase("blue",font_data));
                        tableAnsDIS.addCell(blue);
                    }

                }if (j== 2) {
                    if(testDIS_choices[i].equals("Green")){
                        PdfPCell greenGood = new PdfPCell(new Phrase("green",font_dataBold));
                        greenGood.setBackgroundColor(new BaseColor(red,green,blue));
                        tableAnsDIS.addCell(greenGood);
                    }else{
                        PdfPCell green = new PdfPCell(new Phrase("green",font_dataBold));
                        tableAnsDIS.addCell(green);
                    }
                }
                if (j==3){
                    if (testDIS_choices[i].equals("Red")){
                        PdfPCell redCH = new PdfPCell(new Phrase("red",font_data));
                        redCH.setBackgroundColor(new BaseColor(red,green,blue));
                        tableAnsDIS.addCell(redCH);
                    }else{
                        PdfPCell red = new PdfPCell(new Phrase("red",font_data));
                        tableAnsDIS.addCell(red);
                    }
                }
            }
        }

        cell1.addElement(tableAnsDIS);
        mainDIS.addCell(cell1);

        // second table

        PdfPCell cell2 = new PdfPCell();
        cell2.setBorder(PdfPCell.NO_BORDER);

        PdfPTable tableScoreDIS = new PdfPTable(1);
        tableScoreDIS.setWidthPercentage(100.0f);

        for (int i=0; i<16; i++) {
            PdfPCell pCell = new PdfPCell(new Phrase(testDIS_points[i],font_data));
            pCell.setBorder(PdfPCell.NO_BORDER);
            tableScoreDIS.addCell(pCell);
        }

        cell2.addElement(tableScoreDIS);

        mainDIS.addCell(cell2);
        paragraph.add(mainDIS);
    }


    public void createtableID (Paragraph paragraph) throws DocumentException {


        String tab_answers[][]= MainActivity.tab_answers;
        String tab_good [] = MainActivity.tab_good;
        String testID_points [] = MainActivity.DATA.getTestID_points();
        String testID_choices [] = MainActivity.DATA.getTestID_choices();


        PdfPTable maintable = new PdfPTable(2);
        maintable.setWidthPercentage(80.0f);
        maintable.setSpacingBefore(10f);
        maintable.setSpacingAfter(10f);
        float [] columnInvisible = {10f,1f};
        maintable.setWidths(columnInvisible);

        // first table
        PdfPCell cell1 = new PdfPCell();
        cell1.setBorder(PdfPCell.NO_BORDER);

        PdfPTable tableAns = new PdfPTable(5);
        tableAns.setWidthPercentage(100.0f);
        float [] columnWidth = {1f,4f,4f,4f,4f};
        tableAns.setWidths(columnWidth);

        // grey color indicates patient's answer
        for (int i=0; i<16; i++) {
            for (int j=0;j<5;j++) {
                if (j == 0){
                    PdfPCell num = new PdfPCell(new Phrase(Integer.toString(i+1), font_data));
                    tableAns.addCell(num);
                }
                else {
                    // patient's choices are grey
                    // good answers are bold
                    // good and patient's are both bold and grey
                    if (testID_choices[i].equals(tab_answers[i][j - 1])) {
                        if (tab_good[i].equals(tab_answers[i][j - 1])) {
                            PdfPCell pCell = new PdfPCell(new Phrase(tab_answers[i][j - 1], font_dataBold));
                            pCell.setBackgroundColor(new BaseColor(red,green,blue));
                            tableAns.addCell(pCell);
                        } else {
                            PdfPCell pCell = new PdfPCell(new Phrase(tab_answers[i][j - 1], font_data));
                            pCell.setBackgroundColor(new BaseColor(red,green,blue));
                            tableAns.addCell(pCell);
                        }
                    } else {
                        if (tab_good[i].equals(tab_answers[i][j - 1])) {
                            if (testID_choices[i].equals(tab_answers[i][j - 1])) {
                                PdfPCell pCell = new PdfPCell(new Phrase(tab_answers[i][j - 1], font_data));
                                pCell.setBackgroundColor(new BaseColor(red,green,blue));
                                tableAns.addCell(pCell);
                            } else {
                                PdfPCell pCell = new PdfPCell(new Phrase(tab_answers[i][j - 1], font_dataBold));
                                tableAns.addCell(pCell);
                            }
                        } else {
                            PdfPCell pCell = new PdfPCell(new Phrase(tab_answers[i][j - 1], font_data));
                            tableAns.addCell(pCell);
                        }
                    }
                }
            }
        }
        cell1.addElement(tableAns);
        maintable.addCell(cell1);

        // second table

        PdfPCell cell2 = new PdfPCell();
        cell2.setBorder(PdfPCell.NO_BORDER);

        PdfPTable tableScore = new PdfPTable(1);
        tableScore.setWidthPercentage(100.0f);

        for (int i=0; i<16; i++) {
            PdfPCell pCell = new PdfPCell(new Phrase(testID_points[i],font_data));
            pCell.setBorder(PdfPCell.NO_BORDER);
            tableScore.addCell(pCell);
        }

        cell2.addElement(tableScore);

        maintable.addCell(cell2);
        paragraph.add(maintable);
    }
}
