package com.example.anna.sniffin_sticks;

import android.app.TimePickerDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TimePicker;
import android.widget.Toast;

import java.util.Calendar;

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
        CorrectDate dateBirth = new CorrectDate(patient_birth);
        patient_birth = dateBirth.getSome_date();

        // correct date
        CorrectDate date = new CorrectDate(patient_date);
        patient_date = date.getSome_date();

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

                if (filledFields()) {

                    Intent intent = new Intent(patient_data.this, select_test.class);

                    intent.putExtra("name", patient_name.getText().toString());
                    intent.putExtra("surname", patient_surname.getText().toString());
                    intent.putExtra("birth", patient_birth.getText().toString());
                    intent.putExtra("researcher", patient_researcher.getText().toString());
                    intent.putExtra("date", patient_date.getText().toString());
                    intent.putExtra("hour", patient_hour.getText().toString());

                    int radioButtonID = radioGroup.getCheckedRadioButtonId();
                    radioButton = (RadioButton) findViewById(radioButtonID);
                    intent.putExtra("sex", radioButton.getText().toString());

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
}

