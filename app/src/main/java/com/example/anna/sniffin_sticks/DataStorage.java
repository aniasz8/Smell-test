package com.example.anna.sniffin_sticks;

/**
 * Created by Anna on 2016-08-10.
 */
public class DataStorage {

    private String name;
    private String surname;

    DataStorage (String name, String surname) {
        this.name = name;
        this.surname = surname;
    }

    public String getName () {
        return name;
    }

    public  String getSurname (){
        return surname;
    }
}
