package com.example.anna.sniffin_sticks;

import android.text.Editable;
import android.text.TextWatcher;
import android.widget.EditText;

import java.util.Calendar;

/**
 * Created by Anna on 2016-08-09.
 */
public class CorrectDate {

    private EditText some_date;
    private int year;
    private int mon;
    private int day;

    public CorrectDate(final EditText some_date){

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
            }
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            }
            @Override
            public void afterTextChanged(Editable s) {
            }
        };

        some_date.addTextChangedListener(tw);
        this.some_date=some_date;
    }

    public EditText getSome_date(){
        return some_date;
    }

}
