package com.nlp;




import com.nlp.structure.NameValuePair;

import java.io.File;
import java.io.FileReader;
import java.util.*;

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
    public String lingoReplace(String word) {
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
                        //isStringMatched=false;
                        if(tokenPOS.trim().contains(" ")){

                            //take all the pos and get the top one
                            StringTokenizer getAllPosTokens=new StringTokenizer(tokenPOS," ");

                            //get the first pos
                            if(getAllPosTokens.hasMoreTokens())
                                topTag=getAllPosTokens.nextToken();
                        }else{
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
                if(isStringMatched)
                    break;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return topTag;
    }



    public List<NameValuePair> posTag(String word){
        List<NameValuePair> cleanWords=new ArrayList<NameValuePair>();

        word = word.replace("[","");
        word = word.replace("]", "");
        word = word.replace("(", "");
        word = word.replace(")", "");
        word=word.replaceAll("\\?+", "?");
        word=word.replaceAll("!+", "!");
        word=word.replaceAll("/\b(i|I)m\b/", "I am");
        word=word.replaceAll("/\bwe'll\b/i", "we will");
        word=word.replaceAll("/\bhvn't\b/i", "haven't");
        word=word.replaceAll("/\bhvn't\b/i", "haven't");
        word=word.replaceAll("/\bhaven't\b/i", "have not");
        word=word.replaceAll("/\bhasn't\b/i", "has not");
        word=word.replaceAll("/\bI'll\b/i", "I will");
        word=word.replaceAll("/\bI've\b/i", "I have");
        word=word.replaceAll("/\bWho's\b/i", "who is");
        word=word.replaceAll("/\bit's\b/i", "it is");
        word=word.replaceAll("/\blet's\b/i", "let us");
        word=word.replaceAll("/\bgonna\b/i", "going to");
        word=word.replaceAll("/\bwanna\b/i", "want to");
        word=word.replaceAll("/\bgotta\b/i", "got to");
        word=word.replaceAll("/\bn\b/", "and");
        word=word.replaceAll("/\blol\b/", "");
        word=word.replaceAll("((:))|(:P)|(:()|(;))\b", "");
        word=word.replaceAll("\b(,|\"|(|)|.|:|;|\\?|!)", " $1 ");
        word=word.replaceAll("\"/\\s+/\", \" \"", " $1 ");
        word=word.replaceAll("\"/\\s+/\", \" \"", " $1 ");

        word=word.trim();
        StringTokenizer token=new StringTokenizer(word," ");

        Integer index=0;
        while(token.hasMoreTokens()){
            String stringToken=token.nextToken();
            NameValuePair nameValuePair=new NameValuePair();

            //Delingo the word
            stringToken=lingoReplace(stringToken);

            //First defaulting it to noun
            nameValuePair.setName(stringToken);
            nameValuePair.setValue("NN");

            //if the token is 's,it is PRPS
            if(stringToken.equalsIgnoreCase("'s"))
                nameValuePair.setValue("PRPS");

            // If it is an 'd, then it is a past tense verb
            if(stringToken.equalsIgnoreCase("'d"))
                nameValuePair.setValue("VBR");

            // If it is a verb-adverb compound, tag it as VBR
            if(stringToken.equalsIgnoreCase("don't")||stringToken.equalsIgnoreCase("can't")||stringToken.equalsIgnoreCase("doesn't")||stringToken.equalsIgnoreCase("haven't"))
                nameValuePair.setValue("RB");

            // If it is an 't
            if(stringToken.equalsIgnoreCase("'t")){
                nameValuePair.setValue("RB");
                if(index>0){
                    String bigramchar=cleanWords.get(index-1).getName();
                    if(bigramchar.equalsIgnoreCase("won")||bigramchar.equalsIgnoreCase("shan")||bigramchar.equalsIgnoreCase("couldn")||bigramchar.equalsIgnoreCase("wouldn")||bigramchar.equalsIgnoreCase("mustn"))
                        nameValuePair.setValue("RB");

                    else if(bigramchar.equalsIgnoreCase("wasn")||bigramchar.equalsIgnoreCase("didn")||bigramchar.equalsIgnoreCase("weren"))
                        nameValuePair.setValue("VBD");

                    else if(bigramchar.equalsIgnoreCase("isn")||bigramchar.equalsIgnoreCase("hasn")||bigramchar.equalsIgnoreCase("haven")||bigramchar.equalsIgnoreCase("don")||bigramchar.equalsIgnoreCase("aren")||bigramchar.equalsIgnoreCase("ain"))
                        nameValuePair.setValue("VBZ");

                    else if(bigramchar.equalsIgnoreCase("had"))
                        nameValuePair.setValue("VBN");
                }
            }

            // If the word is I, then it is not NN, but PRP
            if(stringToken.equalsIgnoreCase("i"))
                nameValuePair.setValue("PRP");

            // If the token is US (all caps), then it is a NNP
            if(stringToken.equalsIgnoreCase("us"))
                nameValuePair.setValue("NNP");

            // If the token is a double quote, tag it as DBQ
            if(stringToken.equalsIgnoreCase("\"")||stringToken.equalsIgnoreCase(",\"")||stringToken.equalsIgnoreCase(" \""))
                nameValuePair.setValue("DBQ");

            // If the token is a number, then tag it as CD (not covered in Lexicon)
            if(stringToken.matches("/^\\d+$/"))
                nameValuePair.setValue("CD");


            //get the tag from the pos-lexicon data set
            if(nameValuePair.getValue().equalsIgnoreCase("NN"))
                nameValuePair.setValue(this.getTag(nameValuePair.getName()));

            if(index>0) {
                String bigramchar = cleanWords.get(index - 1).getValue();
                if (bigramchar.equalsIgnoreCase("PRPS") ||bigramchar.equalsIgnoreCase("N")){
                    nameValuePair.setValue("NN");
                }

                if(bigramchar.equalsIgnoreCase("VBG") &&(nameValuePair.getValue().equalsIgnoreCase("V")||nameValuePair.getValue().equalsIgnoreCase("MD")) ){
                    cleanWords.get(index-1).setValue("NN");
                }

                // If the previous token was I and if the current token is tagged as NN, change it to VBZ
                if(cleanWords.get(index-1).getName().equalsIgnoreCase("i") && (nameValuePair.getValue().equalsIgnoreCase("N"))){
                    cleanWords.get(index-1).setValue("VBZ");
                }
            }

            if(nameValuePair.getValue().equalsIgnoreCase("VB")){
                String bigramchar = cleanWords.get(index - 1).getValue();
                if(bigramchar.equalsIgnoreCase("DT")){
                    nameValuePair.setValue("NN");
                }
            }

            // Special case: if the tag is CD, change it to NNP
            if(nameValuePair.getValue().equalsIgnoreCase("CD"))
                nameValuePair.setValue("NNP");

            index++;

            cleanWords.add(nameValuePair);
        }
        return cleanWords;
    }




    public static void main(String[] args) {

        System.out.println("Hello "+ new Tagger().getTag("Owned"));
    }



    
}
