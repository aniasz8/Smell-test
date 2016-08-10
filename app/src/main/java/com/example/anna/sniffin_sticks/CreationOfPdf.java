package com.example.anna.sniffin_sticks;

import android.content.Intent;
import android.net.Uri;
import android.os.Environment;
import android.util.Log;
import android.widget.Toast;

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
    private String testID_total;
    private String testTHR_total;
    private String testDIS_total;



    public CreationOfPdf (String name, String surname){
        this.name=name;
        this.surname=surname;

    }
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


        } catch (DocumentException de) {
            Log.e("Pdf Creator", "Pdf path: " + de);
        }catch (IOException e){
            Log.e("Pdf Creator", "Pdf path: " + e);
        }finally {
            current_document.close();
        }
    }
}
