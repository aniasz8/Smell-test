package com.example.anna.sniffin_sticks;

/**
 * Created by Anna on 2016-08-10.
 */
public class DataStorage {

    private static String name;
    private static String surname;
    private static String birth;
    private static String sex;
    private static String researcher;
    private static String date;
    private static String hour;

    private static String testTHR_total;
    private static String [] testTHR_turningLevels;
    private static String [] testTHR_answers;
    private static String [] testTHR_levels;

    private static String testDIS_total;
    private static String [] testDIS_points;
    private static String [] testDIS_choices;

    private static String testID_total;
    private static String [] testID_points;
    private static String [] testID_choices;

    private static String score;


    public DataStorage (String name, String surname, String birth, String sex, String researcher, String date, String hour){
        this.name = name;
        this.surname = surname;
        this.birth = birth;
        this.sex=sex;
        this.researcher=researcher;
        this.date=date;
        this.hour= hour;
    }

    //getters for patient_data
    public static String getName() {
        return name;
    }
    public static String getSurname() {
        return surname;
    }
    public static String getBirth() {
        return birth;
    }
    public static String getSex() {
        return sex;
    }
    public static String getResearcher() {
        return researcher;
    }
    public static String getDate() {
        return date;
    }
    public static String getHour() {
        return hour;
    }


    //THR
    public static String getTestTHR_total() {
        return testTHR_total;
    }

    public static void setTestTHR_total(String testTHR_total) {
        DataStorage.testTHR_total = testTHR_total;
    }
    public static String[] getTestTHR_turningLevels() {
        return testTHR_turningLevels;
    }
    public static void setTestTHR_turningLevels(String[] testTHR_turningLevels) {
        DataStorage.testTHR_turningLevels = testTHR_turningLevels;
    }
    public static String[] getTestTHR_answers() {
        return testTHR_answers;
    }
    public static void setTestTHR_answers(String[] testTHR_answers) {
        DataStorage.testTHR_answers = testTHR_answers;
    }
    public static String[] getTestTHR_levels() {
        return testTHR_levels;
    }
    public static void setTestTHR_levels(String[] testTHR_levels) {
        DataStorage.testTHR_levels = testTHR_levels;
    }


    //DIS
    public static String getTestDIS_total() {
        return testDIS_total;
    }
    public static void setTestDIS_total(String testDIS_total) {
        DataStorage.testDIS_total = testDIS_total;
    }
    public static String[] getTestDIS_points() {
        return testDIS_points;
    }
    public static void setTestDIS_points(String[] testDIS_points) {
        DataStorage.testDIS_points = testDIS_points;
    }
    public static String[] getTestDIS_choices() {
        return testDIS_choices;
    }
    public static void setTestDIS_choices(String[] testDIS_choices) {
        DataStorage.testDIS_choices = testDIS_choices;
    }


    // ID
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
    public static String[] getTestID_choices() {
        return testID_choices;
    }
    public static void setTestID_choices(String[] testID_choices) {
        DataStorage.testID_choices = testID_choices;
    }


    public static String getScore() {
        return score;
    }
    public static void setScore(String score) {
        DataStorage.score = score;
    }
}
