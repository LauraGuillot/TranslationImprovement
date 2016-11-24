/**
 * ********************************************************************
 * Class Document
 * --------------------------------------------------------------------
 * Last update : 16/06/2016
 * Author : Laura GUILLOT
 *********************************************************************
 */
package topicLabelling.texttiling;

import java.util.ArrayList;

public class Document {

    private ArrayList<String> token;
    private ArrayList<String> words;
    private ArrayList<String> seenWords;
    private ArrayList<String> boundaries;
    private ArrayList<String> sentenceBoundaries;
    private Utils u;

    public ArrayList<String> getToken() {
        return token;
    }

    public void setToken(ArrayList<String> token) {
        this.token = token;
    }

    public ArrayList<String> getWords() {
        return words;
    }

    public void setWords(ArrayList<String> words) {
        this.words = words;
    }

    public ArrayList<String> getSeenWords() {
        return seenWords;
    }

    public void setSeenWords(ArrayList<String> seenWords) {
        this.seenWords = seenWords;
    }

    public ArrayList<String> getBoundaries() {
        return boundaries;
    }

    public void setBoundaries(ArrayList<String> boundaries) {
        this.boundaries = boundaries;
    }

    public ArrayList<String> getSentenceBoundaries() {
        return sentenceBoundaries;
    }

    public void setSentenceBoundaries(ArrayList<String> sentenceBoundaries) {
        this.sentenceBoundaries = sentenceBoundaries;
    }

    public Utils getU() {
        return u;
    }

    public void setU(Utils u) {
        this.u = u;
    }

    public Document(String doc) {
        u = new Utils();
        u.init(doc);

        token = u.getToken();
        words = u.getWords(token);
        boundaries = new ArrayList<String>();
        sentenceBoundaries = new ArrayList<String>();
        seenWords = new ArrayList<String>();
    }

}
