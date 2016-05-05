package com.nlp;

import com.nlp.structure.NameValuePair;
import com.nlp.utils.POSUtils;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.StringTokenizer;

public class Main {

    public static void main(String[] args) {
	// write your code here
        String sentence="Rami Eid is studying at Stony Brook University in New York";
         sentence="Game of Thrones is the most appritiated series in  The IMDB";
        /*System.out.println(sentence);
        List<NameValuePair> nameValuePairList=new Tagger().posTag(sentence);
        for(NameValuePair nameValuePair:nameValuePairList){
            System.out.println(" name :  "+nameValuePair.getName()+" ; Value : "+nameValuePair.getValue());
        }*/

        StringTokenizer stringTokenizer=new StringTokenizer(sentence);
        ArrayList<String> list=new ArrayList<String>();
        while(stringTokenizer.hasMoreTokens()){
            String token=stringTokenizer.nextToken();
           // System.out.println(token);
            list.add(token);
        }
        POSUtils posUtils=new POSUtils();
        HashMap<String,Integer> map=posUtils.freqToken(list);
        for(String key:map.keySet()){
            System.out.println(" Words : "+key+" | occurence : "+map.get(key));
        }
    }
}
