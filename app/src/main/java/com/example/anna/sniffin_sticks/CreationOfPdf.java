package com.example.anna.sniffin_sticks;

import android.os.Environment;
import android.util.Log;

import com.itextpdf.text.BaseColor;
import com.itextpdf.text.Chunk;
import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Element;
import com.itextpdf.text.Font;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.Phrase;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;


/**
 * Created by Anna on 2016-08-10.
 */
public class CreationOfPdf {

    private Font font_title = new Font(Font.FontFamily.TIMES_ROMAN,14, Font.BOLD);
    private Font font_dataUnderline = new Font(Font.FontFamily.TIMES_ROMAN, 10, Font.UNDERLINE);
    private Font font_data = new Font(Font.FontFamily.TIMES_ROMAN, 10);
    private Font font_dataBold = new Font(Font.FontFamily.TIMES_ROMAN, 10, Font.BOLD);
    private Font font_dataBoldUnder = new Font(Font.FontFamily.TIMES_ROMAN, 10, Font.BOLD|Font.UNDERLINE);

    final private String title = "RIECHTEST: Sniffin' Sticks ";
    final private String title_patient = "Patient(in)";
    final private String title_result = "Ergebnis";
    final private String title_data = "1. Daten";

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
            String testDIS_total = MainActivity.DATA.getTestDIS_total();
            String testID_total = MainActivity.DATA.getTestID_total();

            String score = MainActivity.DATA.getScore();


            //First part

            Paragraph par_data_title = new Paragraph(title_data,font_title);
            document.add(par_data_title);

            Paragraph data = new Paragraph("Datum: " + date + "     Uhrzeit: "
                    + hour +"     Untersucher: " + researcher, font_data);
            document.add(data);

            Paragraph par_patient_title = new Paragraph();
            Chunk patientTitle = new Chunk(title_patient, font_dataUnderline);
            Chunk patient = new Chunk("        Vorname: " + name + "     Name: " +surname+
                    "     Geschlecht: " +sex + "      Alter: " + age, font_data);
            par_patient_title.add(patientTitle);
            par_patient_title.add(patient);
            document.add(par_patient_title);


            Paragraph par_result_title = new Paragraph();
            Chunk result_title = new Chunk(title_result, font_dataUnderline);
            Chunk par_results = new Chunk("           Schwelle: " + testTHR_total+ "     Diskriminierung: "+ testDIS_total
                    + "     Erkennung: " + testID_total, font_data);
            par_result_title.add(result_title);
            par_result_title.add(par_results);
            document.add(par_result_title);

            Paragraph par_total = new Paragraph("SDI-Wert: "+ score + "   ", font_data);
            Chunk diagnosis = new Chunk (MainActivity.DATA.getDiagnosis(),font_dataUnderline);
            par_total.add(diagnosis);
            par_total.setAlignment(Element.ALIGN_CENTER);
            document.add(par_total);


            // part 2 - threshold
            Paragraph test1 = new Paragraph("2. Schwelle (" + testTHR_total + ")",font_title);
            document.add(test1);
            Paragraph tableTHR = new Paragraph();
            createtableTHR(tableTHR);
            document.add(tableTHR);

            // part 3 - discrimination
            Paragraph test2 = new Paragraph("3. Diskriminierung (" + testDIS_total + ")" ,font_title);
            document.add(test2);
            Paragraph tableDIS = new Paragraph();
            createtableDIS(tableDIS);
            document.add(tableDIS);

            // part 4 - identification
            Paragraph test3 = new Paragraph("4. Erkennung (" + testID_total + ")",font_title);
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

    public void createtableTHR (Paragraph paragraph) throws DocumentException {

        String testTHR_turningLevels [] = MainActivity.DATA.getTestTHR_turningLevels();
        String testTHR_levels [] = MainActivity.DATA.getTestTHR_levels();
        String testTHR_points [] = MainActivity.DATA.getTestTHR_answers();
        String[][] schemeTab;

        PdfPTable mainTHR = new PdfPTable(2);
        mainTHR.setWidthPercentage(50.0f);
        mainTHR.setSpacingBefore(10f);
        mainTHR.setSpacingAfter(10f);
        float [] columns = {1f,8f};
        mainTHR.setWidths(columns);

        // first table
        PdfPCell cell1 = new PdfPCell();
        cell1.setBorder(PdfPCell.NO_BORDER);

        PdfPTable tabNum = new PdfPTable(1);
        tabNum.setWidthPercentage(100.0f);

        for (int i=0; i<17; i++) {
            if (i==16){
                PdfPCell tp = new PdfPCell(new Phrase("TP",font_dataBold));
                tp.setBorder(PdfPCell.NO_BORDER);
                tabNum.addCell(tp);
            }
            else {
                PdfPCell pCell = new PdfPCell(new Phrase(Integer.toString(i + 1), font_data));
                tabNum.addCell(pCell);
            }
        }

        cell1.addElement(tabNum);
        mainTHR.addCell(cell1);



        //second table
        PdfPCell cell2 = new PdfPCell();
        cell2.setBorder(PdfPCell.NO_BORDER);

        PdfPTable tableTHR = new PdfPTable(7);
        tableTHR.setWidthPercentage(100.0f);


        TableTestTHR scheme = new TableTestTHR(testTHR_levels,testTHR_turningLevels,testTHR_points);
        schemeTab = scheme.createScheme();

        for (int i=0; i<17; i++) {
            for (int j = 0; j < 7; j++) {

                // row with the turning levels
                if (i==16){

                    // in case of not doing the THR test
                    if (testTHR_turningLevels[j]==null){
                        PdfPCell nothing = new PdfPCell(new Phrase("-"));
                        nothing.setBorder(PdfPCell.NO_BORDER);
                        nothing.setFixedHeight(14f);
                        tableTHR.addCell(nothing);
                    }else {
                        PdfPCell turnLeveles = new PdfPCell(new Phrase((testTHR_turningLevels[j]), font_dataBold));
                        turnLeveles.setBorder(PdfPCell.NO_BORDER);
                        tableTHR.addCell(turnLeveles);
                    }
                }
                else {

                    if (schemeTab[i][j] == null) {
                        PdfPCell nothing = new PdfPCell(new Phrase(""));
                        nothing.setFixedHeight(14f);
                        tableTHR.addCell(nothing);
                    }

                    if (schemeTab[i][j] == "11") {
                        PdfPCell x = new PdfPCell(new Phrase("xx", font_data));
                        tableTHR.addCell(x);
                    }

                    if (schemeTab[i][j] == "0") {
                        PdfPCell zero = new PdfPCell(new Phrase("o", font_data));
                        tableTHR.addCell(zero);
                    }

                    if (schemeTab[i][j] == "111") {
                        PdfPCell oneTurn = new PdfPCell(new Phrase("xx!", font_dataBold));
                        oneTurn.setBackgroundColor(new BaseColor(red, green, blue));
                        tableTHR.addCell(oneTurn);
                    }

                    if (schemeTab[i][j] == "00") {
                        PdfPCell zeroTurn = new PdfPCell(new Phrase("o!", font_dataBold));
                        zeroTurn.setBackgroundColor(new BaseColor(red, green, blue));
                        tableTHR.addCell(zeroTurn);
                    }
                }
            }
        }


        cell2.addElement(tableTHR);
        mainTHR.addCell(cell2);

        paragraph.add(mainTHR);

    }





    //test2
    public void createtableDIS (Paragraph paragraph) throws DocumentException {

        String testDIS_points [] = MainActivity.DATA.getTestDIS_points();
        String testDIS_choices [] = MainActivity.DATA.getTestDIS_choices();


        //table
        PdfPTable tableAnsDIS = new PdfPTable(16);
        tableAnsDIS.setWidthPercentage(100.0f);
        tableAnsDIS.setSpacingBefore(10f);
        tableAnsDIS.setSpacingAfter(10f);


        for (int i=0; i<5; i++) {
            for (int j = 0; j < 16; j++) {
                if (i == 0) {
                    PdfPCell num = new PdfPCell(new Phrase(Integer.toString(j + 1), font_data));
                    num.setVerticalAlignment(Element.ALIGN_CENTER);
                    num.setHorizontalAlignment(Element.ALIGN_MIDDLE);
                    tableAnsDIS.addCell(num);
                }
                if (i == 1) {
                    if (testDIS_choices[j].equals("Blau")) {
                        PdfPCell blueCH = new PdfPCell(new Phrase("Blau", font_data));
                        blueCH.setBackgroundColor(new BaseColor(red, green, blue));
                        tableAnsDIS.addCell(blueCH);
                    } else {
                        PdfPCell blue = new PdfPCell(new Phrase("Blau", font_data));
                        tableAnsDIS.addCell(blue);
                    }
                }
                if (i == 2) {
                    if (testDIS_choices[j].equals("Grün")) {
                        PdfPCell greenGood = new PdfPCell(new Phrase("Grün", font_dataBold));
                        greenGood.setBackgroundColor(new BaseColor(red, green, blue));
                        tableAnsDIS.addCell(greenGood);
                    } else {
                        PdfPCell green = new PdfPCell(new Phrase("Grün", font_dataBold));
                        tableAnsDIS.addCell(green);
                    }
                }
                if (i == 3) {
                    if (testDIS_choices[j].equals("Rot")) {
                        PdfPCell redCH = new PdfPCell(new Phrase("Rot", font_data));
                        redCH.setBackgroundColor(new BaseColor(red, green, blue));
                        tableAnsDIS.addCell(redCH);
                    } else {
                        PdfPCell red = new PdfPCell(new Phrase("Rot", font_data));
                        tableAnsDIS.addCell(red);
                    }
                }if (i==4){
                    PdfPCell pCell = new PdfPCell(new Phrase(testDIS_points[j],font_data));
                    pCell.setBorder(PdfPCell.NO_BORDER);
                    tableAnsDIS.addCell(pCell);
                }
            }
        }

        paragraph.add(tableAnsDIS);
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
