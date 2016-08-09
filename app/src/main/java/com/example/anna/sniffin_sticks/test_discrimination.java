package com.example.anna.sniffin_sticks;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.RadioGroup;

public class test_discrimination extends AppCompatActivity {

    private RadioGroup radioGroup;
    private RadioButton radioButton;
    private Button testDIS_ok;
    private int counter = 0;
    private String patient_choice;
    private int testDIS_total;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_test_discrimination);

        testDIS_ok = (Button) findViewById(R.id.testDIS_ok); 


        testDIS_ok.setOnClickListener(new Button.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(test_discrimination.this, select_test.class);
                startActivity(intent);
            }
        });
    }
}
