/**
 * ********************************************************************
 * Interface SentenceManager
 * --------------------------------------------------------------------
 * Last update : 16/06/2016
 * Author : Laura GUILLOT
 *********************************************************************
 */
package org.manager;

import java.util.ArrayList;
import org.tables.Player;
import org.tables.Sentence;
import org.tables.Topic;
import org.tables.Translation;

public interface SentenceManager {

    public ArrayList<Sentence> genere10(int id, String l1, String l2);

    public ArrayList<Sentence> getSentenceInProcess(String l1, String l2);

    public ArrayList<Sentence> getSentenceNotSubmitted(int playerID, ArrayList<Sentence> s);

    public ArrayList<Sentence> getSentenceNotVoted(int playerID, ArrayList<Sentence> s);

    public ArrayList<Sentence> getSentenceWithTopics(int playerID, ArrayList<Sentence> s);

    public ArrayList<Sentence> getSentenceWithoutTopics(int playerID, ArrayList<Sentence> s);

    public Sentence getSentence(Player player, String l1, String l2);

    public int nbSubmissionsByPlayer(int PlayerID);

    public int nbSubmissionsApprovedByPlayer(int PlayerID);

    public ArrayList<Translation> mySubmissions(int id);

    public ArrayList<Sentence> mySubmissionsSentences(ArrayList<Translation> tr);

    public ArrayList<Double> votesFor(ArrayList<Translation> s);

    public ArrayList<Double> votesAgainst(ArrayList<Translation> s);

    public ArrayList<ArrayList<Topic>> getTopics(ArrayList<Sentence> s);

    public ArrayList<ArrayList<Translation>> getTranslations(ArrayList<Sentence> s);

    public ArrayList<Translation> getTranslationsSentence(Sentence s);

    public boolean registerTranslation(int sentenceID, String tr, int playerID);

    public void registerSentence(String s, String t, int id, String l1, String l2, ArrayList<String> topics, ArrayList<Integer> weight, boolean b);

    public ArrayList<ArrayList<Integer>> getWeight(ArrayList<ArrayList<Topic>> top, ArrayList<Sentence> sent);

    public ArrayList<Integer> getWeightSentence(ArrayList<Topic> topics, Sentence s);

    public boolean isregisterSentence(String s, int id, String l1, String l2);

    public ArrayList<Translation> getTranslationsApp(ArrayList<Sentence> s);
}
