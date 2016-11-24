/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package topicLabelling.lda.models;

/**
 *
 * @author Laura
 */
public class Score {
    
    private String word;
    private double sc;

    public String getWord() {
        return word;
    }

    public void setWord(String word) {
        this.word = word;
    }

    public double getSc() {
        return sc;
    }

    public void setSc(double sc) {
        this.sc = sc;
    }

    public Score() {
    }

    public Score(String word, double sc) {
        this.word = word;
        this.sc = sc;
    }
    
    
}
