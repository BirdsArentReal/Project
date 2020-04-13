package com.example.project;

import java.util.ArrayList;

public class SavableString {
    String temp;
    static String bs = "123SWR55TFE097MK";

    SavableString() {
        this("");
    }

    public SavableString(String str) {
        temp = str;
    }

    public String getTemp() {
        return temp;
    }

    public void setTemp(String temp) {
        this.temp = temp;
    }

    public static SavableString convertToSavableString(ArrayList<String> strLst) {
        String str = "";
        for (String i : strLst) {
            str += i + bs;
        }

        return new SavableString(str);
    }

    public static ArrayList<String> convertToArrayList(SavableString savableString) {
        ArrayList<String> strings = new ArrayList<>();
        String[] strLst = savableString.getTemp().split(bs);
        for (String i : strLst) {
            if (!i.equals("")) {
                strings.add(i);
            }
        }
        return strings;
    }
}
