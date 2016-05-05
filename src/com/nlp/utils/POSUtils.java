package com.nlp.utils;

import java.util.ArrayList;
import java.util.HashMap;

/**
 * Created by omoto on 4/5/16.
 */
public class POSUtils {
    String sentenceRegaxEx="/(?<=^|\\.|\\?|!).*?(?=(?<!\\b[A-Z])(?<!Mr|Ms|Dr|sq|Sq|no|No|hr|st|St|Sr|Jr|Rs)(?<!Mrs|min|max|ltd|Ltd|inc|Inc)(?<!Prof)((\\.\"* |\\? |! |\\n)|$))/s";

    //sort array based on occurence and return the array in desc order
    public HashMap<String, Integer> freqToken(ArrayList<String> listOfWords){
        HashMap<String,Integer>  wordFreq=new HashMap<String,Integer>();

        for(String token:listOfWords){
            token=token.toUpperCase();
            //traversing each token
            if(wordFreq.keySet().contains(token)){
                //get the hash key object and increase the count by 1
                Integer freq=wordFreq.get(token);
                wordFreq.remove(token);
                wordFreq.put(token,freq+1);
            }
            else{
                //otherwise put as frequence 1
                wordFreq.put(token,1);
            }

        }
        //return the hashmap
        return wordFreq;
    }

    //clean POS tagged sentence to get actual sentence
    public String cleanText(String sentence){

        sentence.replace("_,_,_",", ");
        sentence.replaceAll("/_(NN[A-Z]*|JJ[A-Z]*|VB[A-Z]*|RB[A-Z]*|IN|TO|PRPS*|CC|CD|DT|EX|FW|MD|WDT|WPS*|WRB|UH|DBQ|SYM|\\.|!)_*/"," ");
        sentence.replace(" 's","'s");
        sentence.replaceAll("/\\s\\._\\._/",".");
        sentence.replaceAll("/_:_/","");
        sentence.replace(" -","-");
        sentence.replace("_(_","");
        sentence.replace("_)_","");
        sentence.replace("_;_","");
        sentence.trim();
        return sentence;
    }
}
