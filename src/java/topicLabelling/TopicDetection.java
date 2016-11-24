/**
 * ********************************************************************
 * Class TopicDetection
 * --------------------------------------------------------------------
 * Last update : 16/06/2016
 * Author : Laura GUILLOT
 *********************************************************************
 */
package topicLabelling;

import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.eclipse.jdt.internal.compiler.batch.Main;
import topicLabelling.lda.models.GibbsSamplingLDA;
import topicLabelling.lda.models.Score;
import topicLabelling.texttiling.Tiling;

/**
 *
 * @author Laura
 */
public class TopicDetection {

    /**
     * Document
     */
    private String doc;
    /**
     * Categories
     */
    private ArrayList<String> foundCat;

    /*SETTERS AND GETTERS*/
    public String getDoc() {
        return doc;
    }

    public void setDoc(String doc) {
        this.doc = doc;
    }

    public ArrayList<String> getFoundCat() {
        return foundCat;
    }

    public void setFoundCat(ArrayList<String> foundCat) {
        this.foundCat = foundCat;
    }

    /**
     * Constructor
     *
     * @param script Document
     */
    public TopicDetection(String script) {
        foundCat = new ArrayList<String>();
        doc = script;
    }

    /**
     * Topic detection algorithm : 1) Text Tilling 2) LDA 3) Topic Labelling
     *
     * @return List of scores (that is to say a list of pair (topic,weight))
     */
    public ArrayList<Score> detect() {

        System.out.println("********************** TILING ************************");
        Tiling t = new Tiling(doc);
        t.mainFunction();

        int cpt = 1;
        for (String s : t.getDoc().getBoundaries()) {
            System.out.println("***************************");
            System.out.println("Part " + cpt);
            System.out.println(s);
            cpt++;
        }

        System.out.println("********************** TOPIC DETECTION ************************");

        ArrayList<Score> sc = new ArrayList<Score>();
        for (String s : t.getDoc().getBoundaries()) {
            try {
                GibbsSamplingLDA lda;
                lda = new GibbsSamplingLDA(s, 1, 0.1, 0.01, 2000, 10, "testLDA");
                sc = lda.inference(sc);
                System.out.println("*****************************");
            } catch (Exception ex) {
                Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex);
            }
        }

        System.out.println("********************** TOPIC LABELLING ************************");
        ArrayList<Score> score = topicLabelling.labelling.labels.Process.label(sc);

        for (Score sco : score) {
            System.out.println(sco.getWord() + "**" + sco.getSc());
            this.foundCat.add(sco.getWord());
        }
        return score;
    }

}
