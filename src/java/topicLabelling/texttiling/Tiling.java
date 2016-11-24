/**
 * ********************************************************************
 * Class Tilling
 * --------------------------------------------------------------------
 * Last update : 16/06/2016
 * Author : Laura GUILLOT
 *********************************************************************
 */
package topicLabelling.texttiling;

import java.util.ArrayList;
import java.util.StringTokenizer;


public class Tiling {

    private Document doc;
    private int K;
    private ArrayList<Double> score;
    private ArrayList<Double> depthScore;

    public Tiling(String file) {
        doc = new Document(file);

        this.K = 10;
        score = new ArrayList<Double>();
        depthScore = new ArrayList<Double>();

    }

    public Document getDoc() {
        return doc;
    }

    public void setDoc(Document doc) {
        this.doc = doc;
    }

    public int getK() {
        return K;
    }

    public void setK(int K) {
        this.K = K;
    }

    /**
     * Obtenir le block dont le premier element est celui d'indice i dans la
     * liste des token
     *
     * @param i
     * @return
     */
    public ArrayList<String> getBlock(int i) {
        ArrayList<String> b = new ArrayList<String>();

        for (int z = i; z < this.getK() + i - 1; z++) {
            b.add(this.getDoc().getToken().get(z));
        }
        return b;
    }

    /**
     * Obtenir le nombre de mots qui sont apparus pour la premiere fois dans le
     * block b
     *
     * @param b
     * @return
     */
    public int nbNewTerms(ArrayList<String> b) {
        int cpt = 0;

        ArrayList<String> wordsInb = new ArrayList<String>();

        for (String bb : b) {
            StringTokenizer st = new StringTokenizer(bb, " ");

            while (st.hasMoreTokens()) {
                String s = st.nextToken();
                s = s.toLowerCase();
                wordsInb.add(s);
            }
        }

        for (String w : wordsInb) {

            if (!(Utils.isIN(this.getDoc().getSeenWords(), w))) {
                cpt++;
                this.getDoc().getSeenWords().add(w);
            }
        }

        return cpt;
    }

    /**
     * Calcul the score of the gap i
     *
     * @param i
     * @return
     */
    public double calculScore(int i) {
        double sc;

        ArrayList<String> b1 = getBlock(i);
        ArrayList<String> b2 = getBlock(i + 1 + K);

        double n1 = this.nbNewTerms(b1);
        double n2 = this.nbNewTerms(b2);

        sc = (n1 + n2) / (2 * K);

        return sc;
    }

    /**
     * Calculer les scores de tous les blocks
     */
    public void scores() {

        for (int i = 0; i < this.getDoc().getToken().size() - 2 * this.getK(); i++) {
            double sc = calculScore(i);
            this.score.add(sc);

        }
    }

    /**
     * Renvoyer le plus petit score a gauche du gap i
     *
     * @param i
     * @return
     */
    public double getMinScoreLeft(int i) {
        int index = i;

        while ((index >= 0) && (this.score.get(index) < this.score.get(i))) {
            index--;
        }
        return this.score.get(index);
    }

    /**
     * Renvoyer le plus petit score a droite du gap i
     *
     * @param i
     * @return
     */
    public double getMinScoreRight(int i) {
        int index = i;

        while ((index < this.score.size()) && (this.score.get(index) < this.score.get(i))) {
            index++;
        }
        return this.score.get(index);
    }

    /**
     * Calcul des depthScores
     */
    public void calculDepthscores() {
        System.out.println("DEPTHSCORES");
        for (int i = 0; i < score.size(); i++) {
            double d = getMinScoreRight(i) + getMinScoreLeft(i) + 2 * score.get(i);
            depthScore.add(d);
            System.out.println(d);
        }
        smooth();
    }

    /**
     * Smoothing function
     */
    public void smooth() {
        System.out.println("SMOOTH");
        for (int i = 0; i < depthScore.size() - 1; i++) {

            int k = 5;
            int a;
            int b;

            if (i - k > 0) {
                a = i - k;
            } else {
                a = 0;
            }

            if (depthScore.size() > i + 10) {
                b = i + 10;
            } else {
                b = depthScore.size() - 1;
            }

            double sc = 0;

            for (int j = a; j <= b; j++) {
                sc = sc + depthScore.get(j);
            }
            sc = sc / ((double) 2 * k);

            depthScore.set(i, sc);
            System.out.println(sc);
        }
    }

    /**
     * Calculer la moyenne des scores
     *
     * @return
     */
    public double moyenne() {
        double m = 0;
        for (int i = 0; i < depthScore.size(); i++) {
            m = m + depthScore.get(i);
        }
        m = m / depthScore.size();
        //System.out.println(m);
        return m;
    }

    /**
     * Calculer la deviation des scores
     *
     * @return
     */
    public double deviation() {
        double d = 0;
        double m = moyenne();
        for (int i = 0; i < depthScore.size(); i++) {
            d = d + (m - depthScore.get(i)) * (m - depthScore.get(i));
        }
        d = d / depthScore.size();
        d = Math.sqrt(d);
        return d;
    }

    /**
     * Renvoyer la limite selon laquelle on va decouper le document
     *
     * @return
     */
    public double limit() {
        double max = 0;
        for (int i = 1; i < this.depthScore.size(); i++) {
            double sc = this.depthScore.get(i);
            if (sc > max) {
                max = sc;
            }
        }
        return moyenne() + (max - moyenne()) / 2.0;
        // return Math.abs(moyenne()-deviation());
    }

    /**
     * Recuperation de la chaine de caractere composee des token de i a j
     *
     * @param i
     * @param j
     * @return
     */
    public String cut(int i, int j) {
        String s = "";
        for (int a = i; a < j; a++) {
            s = s + this.getDoc().getToken().get(a) + " ";
        }
        return s;
    }

    /**
     * Recuperation de la chaine de caractere composee des sentences de i a j
     *
     * @param i
     * @param j
     * @return
     */
    public String cut1(int i, int j) {
        String s = "";

        for (int a = i; a < j; a++) {
            s = s + this.getDoc().getU().getSentences().get(a) + " ";

        }

        return s;
    }

    /**
     * Liste des gaps auxquels il faut couper
     *
     * @return
     */
    public ArrayList<Integer> toCutGaps() {
        ArrayList<Integer> l = new ArrayList<Integer>();
        double lim = limit();

        for (int i = 1; i < depthScore.size(); i++) {
            if (depthScore.get(i) > lim) {
                l.add(i);
            }
        }

        ArrayList<Integer> cut = new ArrayList<Integer>();

        int i = 0;

        while (i < l.size()) {

            ArrayList<Integer> l1 = new ArrayList<Integer>();
            boolean isadd = true;

            for (int k = i; k < l.size(); k++) {
                if (isadd && l.get(k) == (l.get(i) + l1.size())) {
                    l1.add(l.get(k));
                } else {
                    isadd = false;
                }
            }

            i = i + l1.size();

            int max = 0;
            for (int j = 0; j < l1.size(); j++) {
                if (depthScore.get(l1.get(j)) > depthScore.get(l1.get(max))) {
                    max = j;
                }
            }

            cut.add(l1.get(max));

        }

        return cut;
    }

    public void bound() {
        ArrayList<Integer> l = toCutGaps();

        //Pas de coupure
        if (l.isEmpty()) {
            String s = this.cut(0, this.getDoc().getToken().size());
            this.getDoc().getBoundaries().add(s);
            String s1 = this.cut1(0, this.getDoc().getToken().size());
            this.getDoc().getSentenceBoundaries().add(s1);
        }

        //1 coupure
        if (l.size() == 1) {
            String s = this.cut(0, l.get(0) + K + 1);
            this.getDoc().getBoundaries().add(s);
            String s1 = this.cut1(0, l.get(0) + K + 1);
            this.getDoc().getSentenceBoundaries().add(s1);

            s = this.cut(l.get(0) + K + 1, this.getDoc().getToken().size());
            this.getDoc().getBoundaries().add(s);
            s1 = this.cut1(l.get(0) + K + 1, this.getDoc().getToken().size());
            this.getDoc().getSentenceBoundaries().add(s1);
        }

        //>1 coupures
        if (l.size() > 1) {
            for (int z = 0; z < l.size(); z++) {
                int a = l.get(z);

                if (z == 0) {
                    if (a != 0) {

                        String s = this.cut(0, a + K + 1);
                        this.getDoc().getBoundaries().add(s);
                        String s1 = this.cut1(0, a + K + 1);
                        this.getDoc().getSentenceBoundaries().add(s1);

                    }
                } else if (z == (l.size() - 1)) {

                    int b = l.get(z - 1);

                    if (a != (b - 1)) {
                        String s = this.cut(b + K + 1, a + K + 1);
                        this.getDoc().getBoundaries().add(s);
                        String s1 = this.cut1(b + K + 1, a + K + 1);
                        this.getDoc().getSentenceBoundaries().add(s1);

                    }

                    String s = this.cut(a + K + 1, this.getDoc().getToken().size());
                    this.getDoc().getBoundaries().add(s);
                    String s1 = this.cut1(a + K + 1, this.getDoc().getToken().size());
                    this.getDoc().getSentenceBoundaries().add(s1);

                } else {

                    int b = l.get(z - 1);

                    if (a != (b - 1)) {
                        String s = this.cut(b + K + 1, a + K + 1);
                        this.getDoc().getBoundaries().add(s);
                        String s1 = this.cut1(b + K + 1, a + K + 1);
                        this.getDoc().getSentenceBoundaries().add(s1);

                    }
                }
            }
        }

    }

    public void mainFunction() {
        scores();
        calculDepthscores();
        bound();

        for (int i = 0; i < this.getDoc().getBoundaries().size(); i++) {
            System.out.println(this.getDoc().getBoundaries().get(i));
            System.out.println("***");
        }

    }

}
