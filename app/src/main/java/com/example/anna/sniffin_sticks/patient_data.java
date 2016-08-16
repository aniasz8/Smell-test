package com.example.anna.sniffin_sticks;

import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TimePicker;
import android.widget.Toast;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Locale;

public class patient_data extends AppCompatActivity implements View.OnFocusChangeListener{

    private Button ok;
    private EditText patient_name;
    private EditText patient_surname;
    private EditText patient_birth;
    private EditText patient_researcher;
    private EditText patient_date;
    private EditText patient_hour;
    private RadioGroup radioGroup;
    private RadioButton radioButton;

    private int year;
    private int mon;
    private int day;

    private String current = "";
    private String ddmmyyyy = "DDMMYYYY";
    private Calendar cal = Calendar.getInstance();

    //final Calendar myCalendar = Calendar.getInstance();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_patient_data);

        ok = (Button) findViewById(R.id.data_ok);
        patient_name=(EditText) findViewById(R.id.data_name);
        patient_surname=(EditText) findViewById(R.id.data_surname);
        patient_birth = (EditText) findViewById(R.id.data_birth);
        patient_researcher = (EditText) findViewById(R.id.data_researcher);
        patient_date = (EditText) findViewById(R.id.data_date);
        patient_hour =(EditText) findViewById(R.id.data_hour);
        radioGroup =(RadioGroup) findViewById(R.id.data_RadioGroup);

        // correct date of birth
        patient_birth = correctDate(patient_birth); // 29/06/1995
//        MainActivity.DATA.setBirth_year();
//        MainActivity.DATA.setBirth_month();
//        MainActivity.DATA.setBirth_day();

        // correct date
        patient_date = correctDate(patient_date);

        //correct hour
        patient_hour.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                Calendar mcurrentTime = Calendar.getInstance();
                int hour = mcurrentTime.get(Calendar.HOUR_OF_DAY);
                int minute = mcurrentTime.get(Calendar.MINUTE);
                TimePickerDialog mTimePicker;
                mTimePicker = new TimePickerDialog(patient_data.this, new TimePickerDialog.OnTimeSetListener() {
                    @Override
                    public void onTimeSet(TimePicker timePicker, int selectedHour, int selectedMinute) {
                        String selHour = String.format("%02d",selectedHour);
                        String selMin = String.format("%02d",selectedMinute);
                        patient_hour.setText(selHour + ":" + selMin);
                    }
                }, hour, minute, true);//Yes 24 hour time
                mTimePicker.setTitle("Select Time");
                mTimePicker.show();
            }
        });

        // clear EditText (name, surname,  researcher) when clicked
        patient_name.setOnFocusChangeListener(this);
        patient_surname.setOnFocusChangeListener(this);
        patient_researcher.setOnFocusChangeListener(this);


        // after clicking a ok button
        ok.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (filledFields()) {

                    int radioButtonID = radioGroup.getCheckedRadioButtonId();
                    radioButton = (RadioButton) findViewById(radioButtonID);

                    MainActivity.DATA = new DataStorage(patient_name.getText().toString(),
                            patient_surname.getText().toString(), patient_birth.getText().toString(),
                            radioButton.getText().toString(), patient_researcher.getText().toString(),
                            patient_date.getText().toString(),patient_hour.getText().toString());

                    Intent intent = new Intent(patient_data.this, select_test.class);
                    startActivity(intent);

                }else{
                    AlertDialog();
                }
            }
        });
    }


    // extra methods

    @Override
    public void onFocusChange(View v, boolean hasFocus) {
        if(v.equals(patient_name) && hasFocus)
                patient_name.setText("");

        if(v.equals(patient_surname) && hasFocus)
            patient_surname.setText("");

        if(v.equals(patient_researcher) && hasFocus)
            patient_researcher.setText("");
    }

    // check if sex is chosen
    private Boolean filledFields(){
        if(radioGroup.getCheckedRadioButtonId()<=0){
            return false;
        }
        return true;
    }

    // if something not filled then an alert dialog
    private void AlertDialog(){

        AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(patient_data.this);
        alertDialogBuilder.setMessage("Wahlen ein Geschlecht").setCancelable(false)
                .setPositiveButton("OK", new DialogInterface.OnClickListener(){

            public void onClick(DialogInterface dialog, int id){
                dialog.cancel();
            }
        });
        AlertDialog alert = alertDialogBuilder.create();
        alert.show();
    }

    public EditText correctDate (final EditText some_date) {

        TextWatcher tw = new TextWatcher() {

            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if (!s.toString().equals(current)) {
                    String clean = s.toString().replaceAll("[^\\d.]", "");
                    String cleanC = current.replaceAll("[^\\d.]", "");


                    int cl = clean.length();
                    int sel = cl;
                    for (int i = 2; i <= cl && i < 6; i += 2) {
                        sel++;
                    }
                    if (clean.equals(cleanC)) sel--;

                    if (clean.length() < 8) {
                        clean = clean + ddmmyyyy.substring(clean.length());
                    } else {
                        day = Integer.parseInt(clean.substring(0, 2));
                        mon = Integer.parseInt(clean.substring(2, 4));
                        year = Integer.parseInt(clean.substring(4, 8));

                        if (mon > 12) mon = 12;
                        cal.set(Calendar.MONTH, mon - 1);
                        year = (year < 1900) ? 1900 : (year > 2100) ? 2100 : year;
                        cal.set(Calendar.YEAR, year);

                        day = (day > cal.getActualMaximum(Calendar.DATE)) ? cal.getActualMaximum(Calendar.DATE) : day;
                        clean = String.format("%02d%02d%02d", day, mon, year);
                    }

                    clean = String.format("%s/%s/%s", clean.substring(0, 2),
                            clean.substring(2, 4),
                            clean.substring(4, 8));

                    sel = sel < 0 ? 0 : sel;
                    current = clean;
                    some_date.setText(current);
                    some_date.setSelection(sel < current.length() ? sel : current.length());
                }
                year= cal.get(Calendar.YEAR);
                mon = cal.get(Calendar.MONTH);
                day = cal.get(Calendar.DAY_OF_MONTH);
            }
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            }
            @Override
            public void afterTextChanged(Editable s) {
            }
        };
        some_date.addTextChangedListener(tw);

        return some_date;
    }
}

