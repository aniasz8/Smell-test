package com.example.anna.sniffin_sticks;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

// Copyright (C) <2016> <Anna Szagdaj with the help of Jakub Licznerski>
//This program is free software: you can redistribute it and/or modify it under the terms of the GNU Affero General Public License
// as published by the Free Software Foundation, either version 3 of the License, or (at your option) any later version.
//This program is distributed in the hope that it will be useful, but WITHOUT ANY WARRANTY; without even
// the implied warranty of MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.
// See the GNU Affero General Public License for more details.
//You should have received a copy of the GNU Affero General Public License along with this program.
// If not, see <http://www.gnu.org/licenses/>.

public class MainActivity extends AppCompatActivity {

    public static DataStorage DATA;

    public static final String tab_answers[][] = {
            {"Orange", "Blackberry", "Strawberry", "Pineapple"},
            {"Smoke", "Leather", "Glue", "Grass"},
            {"Honey", "Vanilla", "Cinnamon", "Chocolate"},
            {"Chives", "Onion", "Wood", "Peppermint"},
            {"Coconut", "Cherry", "Walnut", "Banana",},
            {"Peach", "Apple", "Lemon", "Grapefruit"},
            {"Gummibears", "Licorice", "Chewing gum", "Cookies"},
            {"Turpentine", "Rubber", "Menthol", "Mustard"},
            {"Garlic", "Onion", "Sauerkraut", "Carrot"},
            {"Cigarette", "Coffee", "Wine", "Candle smoke"},
            {"Melon", "Peach", "Apple", "Orange"},
            {"Mustard", "Pepper", "Cinnamon", "Cloves"},
            {"Pear", "Plum", "Peach", "Pineapple"},
            {"Chamomile", "Raspberry", "Rose", "Cherry"},
            {"Rum", "Aniseed", "Honey", "Wood"},
            {"Fish", "Bread", "Cheese", "Ham"},
            {"", "", "", ""}
    };

    public static final String tab_good [] = {"Orange","Leather","Cinnamon","Peppermint","Banana","Lemon","Licorice",
            "Turpentine", "Garlic","Coffee","Apple","Cloves","Pineapple","Rose","Aniseed","Fish",""};

    private Button new_patient;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        new_patient = (Button) findViewById(R.id.main_new);

        new_patient.setOnClickListener(new Button.OnClickListener() {
            @Override
            public void onClick(View v) {
                clear();
                Intent intent = new Intent(getApplicationContext(), patient_data.class);
                startActivity(intent);
            }
        });

    }

    public void clear (){

        MainActivity.DATA.setTestTHR_total(null);
        MainActivity.DATA.setTestTHR_levels(null);
        MainActivity.DATA.setTestTHR_turningLevels(null);
        MainActivity.DATA.setTestTHR_answers(null);

        MainActivity.DATA.setTestDIS_total(null);
        MainActivity.DATA.setTestDIS_choices(null);
        MainActivity.DATA.setTestDIS_points(null);

        MainActivity.DATA.setTestID_total(null);
        MainActivity.DATA.setTestID_choices(null);
        MainActivity.DATA.setTestID_points(null);

        MainActivity.DATA=null;
    }
}
