package com.company;

public class Parse {
    //Omvandlar en bokstav till respektive rad-index
    // a-j blir 0-9
    public int letterToIndex(char letter){
        int value = letter - 97; // 97 = ASCII värdet av 'a'
        return value;
    }

    // Omvandlar en char (som är en siffra) till en integer
    public int numberToIndex(char num){
        int value = Character.getNumericValue(num);
        return value;
    }

    // Omvandlar integer siffra till respektive bokstav
    // 0-9 blir a-j
    public String intToString(int num){
        String s = String.valueOf((char)(num + 97)); // 97 = ASCII värdet av 'a'
        return s;
    }

}
