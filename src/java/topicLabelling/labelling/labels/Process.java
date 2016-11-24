/**
 * ********************************************************************
 * Class Process
 * --------------------------------------------------------------------
 * Last update : 16/06/2016
 * Author : Laura GUILLOT
 *********************************************************************
 */
package topicLabelling.labelling.labels;

import java.util.ArrayList;
import topicLabelling.labelling.manager.manager;
import topicLabelling.labelling.tables.Relation;
import topicLabelling.lda.models.*;

/**
 *
 * @author Laura
 */
public class Process {

    /**
     * Keywords of a document with their weights
     */
    private ArrayList<Score> words;

    /**
     * Categories of the keywords with their weights
     */
    private ArrayList<Score> cat;

    /*GETTER AND SETTERS*/
    public ArrayList<Score> getWords() {
        return words;
    }

    public void setWords(ArrayList<Score> words) {
        this.words = words;
    }

    public ArrayList<Score> getCat() {
        return cat;
    }

    public void setCat(ArrayList<Score> cat) {
        this.cat = cat;
    }

    /**
     * Constructor
     *
     * @param words List of weighted words
     */
    public Process(ArrayList<Score> words) {
        this.words = words;
        this.cat = new ArrayList<Score>();
    }

    /**
     * Cleaning of the word list (removing the duplications)
     */
    public void clearList() {
        ArrayList<Score> nsc = new ArrayList<Score>();

        for (Score s : words) {

            if (isIn(nsc, s.getWord()) == -1) {
                nsc.add(s);
            } else {
                nsc.get(isIn(nsc, s.getWord())).setSc(nsc.get(isIn(nsc, s.getWord())).getSc() + s.getSc());
            }
        }
        words = nsc;
    }

    /**
     * Get the index of a String in a list of score
     *
     * @param sc List of Score
     * @param s String
     * @return Index of s in sc
     */
    public int isIn(ArrayList<Score> sc, String s) {
        int index = -1;
        for (int i = 0; i < sc.size(); i++) {
            if (sc.get(i).getWord().equals(s)) {
                index = i;
            }
        }
        return index;
    }

    /**
     * Get all the categories associated to the words
     */
    public void retrieveCat() {
        manager m = manager.getInstance();

        for (Score s : words) {
            ArrayList<Relation> l = m.getRelations(s.getWord());
            addCat(l);
        }
    }

    /**
     * Add categories to list
     *
     * @param l List of relation
     */
    public void addCat(ArrayList<Relation> l) {
        for (Relation li : l) {
            if (isIn(cat, li.getCategory().getCat()) == -1) {
                cat.add(new Score(li.getCategory().getCat(), li.getOccurence() * this.getScoreWord(li.getWord().getW())));
            } else {
                cat.get(isIn(cat, li.getCategory().getCat())).setSc(li.getOccurence() * this.getScoreWord(li.getWord().getW()) + cat.get(isIn(cat, li.getCategory().getCat())).getSc());
            }
        }
    }

    /**
     * Get the weight of a word
     *
     * @param w Word
     * @return Weight
     */
    public double getScoreWord(String w) {
        double d = 0;
        for (Score sc : words) {
            if (sc.getWord().equals(w)) {
                d = sc.getSc();
            }
        }
        return d;
    }

    /**
     * Retrieve the maximum of a list of scores
     *
     * @param t List of scores
     * @return Max
     */
    public static int getMax(ArrayList<Score> t) {
        int k = 0;

        for (int i = 0; i < t.size(); i++) {
            if (t.get(i).getSc() > t.get(k).getSc()) {
                k = i;
            }
        }
        return (k);
    }

    /**
     * Insertion sort
     * @param t1 List of Scores
     * @param t2 List wof Scores
     * @return List of scores
     */
    public static ArrayList<Score> tri(ArrayList<Score> t1, ArrayList<Score> t2) {

        if (!t1.isEmpty()) {
            int i = getMax(t1);
            Score sc = t1.get(i);
            t2.add(sc);
            t1.remove(i);
            return tri(t1, t2);
        } else {
            return t2;
        }
    }

    /**
     * Global method : cleaning, getting the categories, ranking the categories
     *
     * @param sc List of scores (to initialize words)
     * @return List of score (weighted categories)
     */
    public static ArrayList<Score> label(ArrayList<Score> sc) {
        Process p = new Process(sc);
        p.clearList();
        p.retrieveCat();
        p.cat = tri(p.cat, new ArrayList<Score>());
        p.words = tri(p.words, new ArrayList<Score>());

        System.out.println("***WORDS***");
        for (Score c : p.words) {
            System.out.println(c.getWord() + "**" + c.getSc());
        }

        System.out.println();

        return p.cat;
    }
}
