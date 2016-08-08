package com.example.anna.sniffin_sticks;

import android.app.TimePickerDialog;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import java.util.Date;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TimePicker;

import java.text.SimpleDateFormat;
import java.util.Calendar;

public class patient_data extends AppCompatActivity implements View.OnFocusChangeListener{

    private Button ok;
    private String data_sex;
    private EditText patient_name;
    private EditText patient_surname;
    private EditText patient_birth;
    private EditText patient_researcher;
    private EditText patient_date;
    private EditText patient_hour;
    private RadioGroup radioGroup;
    private RadioButton radioButton;

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
        TextWatcher tw = new TextWatcher() {
            private String current = "";
            private String ddmmyyyy = "DDMMYYYY";
            private Calendar cal = Calendar.getInstance();

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
                        int day = Integer.parseInt(clean.substring(0, 2));
                        int mon = Integer.parseInt(clean.substring(2, 4));
                        int year = Integer.parseInt(clean.substring(4, 8));

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
                    patient_birth.setText(current);
                    patient_birth.setSelection(sel < current.length() ? sel : current.length());
                }
            }
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            }
            @Override
            public void afterTextChanged(Editable s) {
            }
        };
        patient_birth.addTextChangedListener(tw);


        // correct date
        TextWatcher TW = new TextWatcher() {
            private String current = "";
            private String ddmmyyyy = "DDMMYYYY";
            private Calendar cal = Calendar.getInstance();

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
                        int day = Integer.parseInt(clean.substring(0, 2));
                        int mon = Integer.parseInt(clean.substring(2, 4));
                        int year = Integer.parseInt(clean.substring(4, 8));

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
                    patient_date.setText(current);
                    patient_date.setSelection(sel < current.length() ? sel : current.length());
                }
            }
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            }
            @Override
            public void afterTextChanged(Editable s) {
            }
        };
        patient_date.addTextChangedListener(TW);

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
                        patient_hour.setText( selectedHour + ":" + selectedMinute);
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

                //passing values to next activity
                Intent intent=new Intent(patient_data.this, select_test.class);

                // declaration of passed values (putExtra(name of value, passed value)
                String name=patient_name.getText().toString();
                intent.putExtra("name", name);

                String surname=patient_surname.getText().toString();
                intent.putExtra("surname", surname);

                String birth = patient_birth.getText().toString();
                intent.putExtra("birth", birth);

                int radioButtonID = radioGroup.getCheckedRadioButtonId();
                radioButton = (RadioButton) findViewById(radioButtonID);
                data_sex = radioButton.getText().toString();
               // !!!!!!!!!!!!!!!!!!!
                // co jesli nie klikniete!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!
                intent.putExtra("sex", data_sex);

                String researcher = patient_researcher.getText().toString();
                intent.putExtra("researcher", researcher);

                String date = patient_date.getText().toString();
                intent.putExtra("date", date);

                String hour = patient_hour.getText().toString();
                intent.putExtra("hour", hour);

                // starting another activity
                startActivity(intent);
            }
        });
    }

    @Override
    public void onFocusChange(View v, boolean hasFocus) {
        if(v.equals(patient_name) && hasFocus)
                patient_name.setText("");

        if(v.equals(patient_surname) && hasFocus)
            patient_surname.setText("");

        if(v.equals(patient_researcher) && hasFocus)
            patient_researcher.setText("");
    }
}
