/**
 * ********************************************************************
 * Class Utils
 * --------------------------------------------------------------------
 * Last update : 16/06/2016
 * Author : Laura GUILLOT
 *********************************************************************
 */
package topicLabelling.texttiling;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

/**
 *
 * @author Laura
 */
public class Utils {

    private ArrayList<String> stop;
    private String punct;
    private String doc;
    private ArrayList<String> sentences;

    public ArrayList<String> getStop() {
        return stop;
    }

    public void setStop(ArrayList<String> stop) {
        this.stop = stop;
    }

    public String getPunct() {
        return punct;
    }

    public void setPunct(String punct) {
        this.punct = punct;
    }

    public String getDoc() {
        return doc;
    }

    public void setDoc(String doc) {
        this.doc = doc;
    }

    public ArrayList<String> getSentences() {
        return sentences;
    }

    public void setSentences(ArrayList<String> sentences) {
        this.sentences = sentences;
    }

    public Utils() {
        stop = new ArrayList<String>();
        sentences = new ArrayList<String>();
        punct = "";
        doc = "";
    }

    /**
     * *******************************************************************************
     * CHARGEMENT ET INITIALISATION
     * *******************************************************************************
     */
    /**
     * Chargement des stop words a partir du fichier texte
     */
    public void loadStop() {

        String fichier = "txt/SmartStopListEn";

        //lecture du fichier texte	
        try {
            InputStream ips = new FileInputStream(fichier);
            InputStreamReader ipsr = new InputStreamReader(ips);
            BufferedReader br = new BufferedReader(ipsr);
            String ligne;
            while ((ligne = br.readLine()) != null) {

                stop.add(" " + ligne + " ");
            }
            br.close();
        } catch (Exception e) {
            System.out.println(e.toString());
        }finally{
            
        }
    }

    /**
     * Chargement des ponctuations a partir du fichier texte
     */
    public void loadPunct() {

        String fichier = "txt/RakePunctDefaultStopList";

        //lecture du fichier texte	
        try {
            InputStream ips = new FileInputStream(fichier);
            InputStreamReader ipsr = new InputStreamReader(ips);
            BufferedReader br = new BufferedReader(ipsr);
            String ligne;
            while ((ligne = br.readLine()) != null) {

                punct = punct + ligne;
            }

            br.close();
        } catch (Exception e) {
            System.out.println(e.toString());
        }
    }

    /**
     * Initialisation
     * @param doc
     */
    public void init(String doc) {
        loadPunct();
        loadStop();
        this.doc = doc;
        splitPoint();
    }

    /**
     * *******************************************************************************
     * DECOUPAGE SELON LES POINTS
     * *******************************************************************************
     */
    /**
     * Decoupage du document selon les points
     *
     */
    public void splitPoint() {

        StringTokenizer st = new StringTokenizer(doc, ".");

        while (st.hasMoreTokens()) {
            String s = st.nextToken();
            s = " " + s + " ";
            s = s.toLowerCase();

            sentences.add(s);
        }

    }

    /**
     * ***********************************************************************
     * OBTENTION DES TOKEN
     * ***********************************************************************
     */
    /**
     * Decoupage des phrases selon la ponctuation
     *
     * @param i
     * @return
     */
    public ArrayList<String> splitSentence(int i) {

        ArrayList<String> token = new ArrayList<String>();

        StringTokenizer st = new StringTokenizer(sentences.get(i), punct);
        while (st.hasMoreTokens()) {
            String s = st.nextToken();
            s = " " + s + " ";
            s = s.toLowerCase();

            token.add(s);

        }
        return token;
    }

    /**
     * Decoupage d'une phrase selon un mot
     *
     * @param token
     * @param w
     * @return
     */
    public ArrayList<String> splitTokenWithWord(String token, String w) {
        ArrayList<String> t = new ArrayList<String>();
        String[] parts = token.split(w);
        for (String s : parts) {
            if (!s.equals("") && !s.equals(" ")) {
                if (s.charAt(0) != ' ') {
                    s = " " + s;
                }
                if (s.charAt(s.length() - 1) != ' ') {
                    s = s + " ";
                }
                t.add(s);
            }
        }
        return t;
    }

    public ArrayList<String> splitArrayWithWord(ArrayList<String> a, int i) {
        ArrayList<ArrayList<String>> t = new ArrayList<ArrayList<String>>();

        for (int k = 0; k < a.size(); k++) {
            ArrayList<String> temp = splitTokenWithWord(a.get(k), stop.get(i));
            t.add(temp);
        }
        ArrayList<String> tab = fusion(t);

        return tab;
    }

    /**
     * Transformation d'un tableau de tableau de chaine en tableau de chaine;
     *
     * @param a
     * @return
     */
    public ArrayList<String> fusion(ArrayList<ArrayList<String>> a) {

        ArrayList<String> t = new ArrayList<String>();

        for (int k = 0; k < a.size(); k++) {
            for (int i = 0; i < a.get(k).size(); i++) {
                t.add(a.get(k).get(i));
            }
        }

        return t;
    }

    /**
     * Decoupage du document selon la liste des mots stop
     *
     * @param t
     * @param i
     * @return
     */
    public ArrayList<String> splitStop(ArrayList<String> t, int i) {
        ArrayList<String> r ;

        if (i == stop.size()) {
            r = t;

        } else {
            r = this.splitStop(this.splitArrayWithWord(t, i), i + 1);
        }

        return r;
    }

    /**
     * Enlever les espaces en debut et en fin d'une chaine
     *
     * @param s
     * @return
     */
    public String cleanSpace(String s) {
        String r;
        String temp = s;
        if (temp.length() != 0 && temp.charAt(0) == ' ') {
            temp = temp.substring(1);
            if (temp.length() != 0 && temp.charAt(temp.length() - 1) == ' ') {
                r = cleanSpace(temp.substring(0, temp.length() - 1));
            } else {
                r = cleanSpace(temp);
            }
        } else if (temp.length() != 0 && temp.charAt(temp.length() - 1) == ' ') {
            r = cleanSpace(temp.substring(0, temp.length() - 1));
        } else {
            r = temp;
        }
        return r;
    }

    /**
     * Obtenir tous les token d'un document
     *
     * @return
     */
    public ArrayList<String> getToken() {

        ArrayList<String> token = new ArrayList<String>();

        for (int i = 0; i < sentences.size(); i++) {
            ArrayList<String> t = this.splitStop(this.splitSentence(i), 0);
            String sent = "";
            for (String t1 : t) {
                String s = this.cleanSpace(t1);
                if (!s.equals("") && !estUnEntier(s)) {
                    sent = sent + s + " ";
                }
            }
            token.add(sent);
        }
        return token;
    }

    /**
     * *********************************************************************
     * Obtention des mots
     * *********************************************************************
     */
    /**
     * Obtenir tous les mots du document
     *
     * @param tok
     * @return
     */
    public ArrayList<String> getWords(ArrayList<String> tok) {
        ArrayList<String> words = new ArrayList<String>();

        for (String t : tok) {

            StringTokenizer st = new StringTokenizer(t, " ");

            while (st.hasMoreTokens()) {
                String s = st.nextToken();
                s = s.toLowerCase();
                if (!isIN(words, s)) {
                    words.add(s);
                }

            }
        }

        return words;
    }

    public static boolean isIN(ArrayList<String> str, String s) {
        boolean ok = false;
        for (int i = 0; i < str.size(); i++) {
            if (str.get(i).equals(s)) {
                ok = true;
            }
        }
        return ok;
    }

    public boolean estUnEntier(String chaine) {
        try {
            Integer.parseInt(chaine);

            return true;
        } catch (NumberFormatException e) {

            return false;
        }
    }
}
