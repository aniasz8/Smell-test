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

    private static String testDIS_total;

    private static String testTHR_total;

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

    public static String getTestDIS_total() {
        return testDIS_total;
    }

    public static void setTestDIS_total(String testDIS_total) {
        DataStorage.testDIS_total = testDIS_total;
    }

    public static String getTestTHR_total() {
        return testTHR_total;
    }

    public static void setTestTHR_total(String testTHR_total) {
        DataStorage.testTHR_total = testTHR_total;
    }
}
