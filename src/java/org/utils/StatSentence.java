/**
 * ********************************************************************
 * Class StatSentence
 * Storage of data concerning approved sentences
 * --------------------------------------------------------------------
 * Last update : 16/06/2016
 * Author : Laura GUILLOT
 *********************************************************************
 */
package org.utils;

/**
 *
 * @author Laura
 */
public class StatSentence {

    /**
     * Language of the sentence
     */
    private String l1;
    /**
     * Language of the translation
     */
    private String l2;
    /**
     * Sentence
     */
    private String body;
    /**
     * Name of the player who has proposed the translation
     */
    private String player;
    /**
     * Translation
     */
    private String tr;

    
    public StatSentence(String l1, String l2, String body, String player, String tr) {
        this.l1 = l1;
        this.l2 = l2;
        this.body = body;
        this.player = player;
        this.tr = tr;
    }

    public String getL1() {
        return l1;
    }

    public void setL1(String l1) {
        this.l1 = l1;
    }

    public String getL2() {
        return l2;
    }

    public void setL2(String l2) {
        this.l2 = l2;
    }

    public String getBody() {
        return body;
    }

    public void setBody(String body) {
        this.body = body;
    }

    public String getPlayer() {
        return player;
    }

    public void setPlayer(String player) {
        this.player = player;
    }

    public String getTr() {
        return tr;
    }

    public void setTr(String tr) {
        this.tr = tr;
    }

}
