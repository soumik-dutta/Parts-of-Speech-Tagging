package com.nlp;

import com.nlp.structure.NameValuePair;

import java.util.List;

public class Main {

    public static void main(String[] args) {
	// write your code here
        String sentence="Rami Eid is studying at Stony Brook University in New York";
        System.out.println(sentence);
        List<NameValuePair> nameValuePairList=new Tagger().posTag(sentence);
        for(NameValuePair nameValuePair:nameValuePairList){
            System.out.println(" name :  "+nameValuePair.getName()+" ; Value : "+nameValuePair.getValue());
        }
    }
}
