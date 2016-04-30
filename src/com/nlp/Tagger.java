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

    /**
     * get the pos tag for the following tag
     * @param word
     * @return
     */
    public String getTag(String word) {
        String topTag = "";
        FileReader fileReader;
        try {
            File file = new File("pos_lexicon.txt");
            fileReader = new FileReader(file);
            Scanner scanner = new Scanner(fileReader);
            Integer index = 0;
            String[] token = new String[2];
            Boolean isStringMatched=false;

            //scanning every line
            while (scanner.hasNextLine()) {
               // System.out.println(scanner.nextLine());

                //splitting the line into two fields works and pos
                StringTokenizer stringTokenizer = new StringTokenizer(scanner.nextLine().trim(),",");

                //traversing inside the line
                while (stringTokenizer.hasMoreTokens()) {
                    String tokenPOS=stringTokenizer.nextToken().trim();

                    if(isStringMatched){
                        //change the flag to false
                        isStringMatched=false;
                        if(tokenPOS.trim().contains(" ")){
                            //take all the pos and get the top one
                            StringTokenizer getAllPosTokens=new StringTokenizer(tokenPOS," ");
                            //get the first pos
                            if(getAllPosTokens.hasMoreTokens())
                                topTag=getAllPosTokens.nextToken();
                        }else{
                            System.out.println("got token : "+tokenPOS);
                            topTag=tokenPOS;
                        }
                    }
                    //if the word matches then get the top pos value
                    if (!tokenPOS.equalsIgnoreCase(word)) {
                        //break the loop if the word doesnot match
                        break;
                    }
                    else{
                        //if the  word matches then
                        isStringMatched=true;

                    }
                }
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
        return topTag;
    }






    public static void main(String[] args) {

        System.out.println("Hello "+ new Tagger().getTag("hello"));
    }



    
}
