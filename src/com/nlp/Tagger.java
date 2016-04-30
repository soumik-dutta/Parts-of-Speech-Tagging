package com.nlp;

import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.lang.reflect.Array;
import java.util.Scanner;
import java.util.StringTokenizer;

/**
 * Created by omoto on 30/4/16.
 */
public class Tagger {

    /**
     * If the word contains sms syntax we will chnage to the standard words
     * otherwise return the word itself
     *
     * @param word
     * @return
     */
    private String lingoReplace(String word) {
        switch (word.toLowerCase()) {
            case "thn":
                return "then";
            case "pls":
                return "please";
            case "gt":
                return "got";
            case "hv":
                return "have";
            case "gr8":
                return "great";
            case "cos":
            case "coz":
            case "becoz":
                return "because";
            case "wid":
                return "with";
            case "fr":
                return "for";
            case "2day":
                return "today";
            // case "hw":
            // return "how";
            case "luv":
                return "love";
            case "ur":
                return "your";
            case "urs":
                return "yours";
            case "nw":
                return "now";
            case "cn":
                return "can";
            case "abt":
                return "about";
            case "tht":
            case "dat":
                return "that";
            case "nuf":
            case "nuff":
            case "enuf":
                return "enough";
            case "rly":
                return "really";
            case "r":
                return "are";
            default:
                return word;
        }
    }

    private String getTag(String word) {
        String topTag = "";
        FileReader fileReader;
        try {
            File file = new File("abc.txt");
            fileReader = new FileReader(file);
            Scanner scanner = new Scanner(fileReader);
            Integer index = 0;
            String[] token = new String[2];

            //scanning every line
            while (scanner.hasNextLine()) {

                //splitting the line into two fields works and pos
                StringTokenizer stringTokenizer = new StringTokenizer(",", scanner.nextLine());

                //traversing inside the line
                while (stringTokenizer.hasMoreTokens()) {

                    //if the word matches then get the top pos value
                    if (!stringTokenizer.nextElement().toString().trim().equalsIgnoreCase(word)) {
                        //break the loop if the word doesnot match
                        break;
                    }

                    //if the  word matches then

                }

                if (index == 100)
                    break;
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public static void main(String[] args) {

    }



    
}
