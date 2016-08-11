package com.example.anna.sniffin_sticks;

/**
 * Created by Anna on 2016-08-10.
 */
public class DataStorage {

    private static String name;
    private static String surname;
    private static String testID_total;
    private static String [] testID_points;
    private static String [] testID_choices;

    public DataStorage (String name, String surname){
        this.name = name;
        this.surname = surname;
    }

    public static String getName() {
        return name;
    }

    public static void setName(String name) {
        DataStorage.name = name;
    }

    public static String getSurname() {
        return surname;
    }

    public static void setSurname(String surname) {
        DataStorage.surname = surname;
    }

    public static String[] getTestID_choices() {
        return testID_choices;
    }

    public static String getTestID_total() {
        return testID_total;
    }

    public static void setTestID_total(String testID_total) {
        DataStorage.testID_total = testID_total;
    }

    public static String[] getTestID_points() {
        return testID_points;
    }

    public static void setTestID_points(String[] testID_points) {
        DataStorage.testID_points = testID_points;
    }

    public static void setTestID_choices(String[] testID_choices) {
        DataStorage.testID_choices = testID_choices;
    }
}
