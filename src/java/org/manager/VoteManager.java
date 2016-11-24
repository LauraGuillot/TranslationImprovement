/**
 * ********************************************************************
 * Interface VoteManager
 * --------------------------------------------------------------------
 * Last update : 16/06/2016
 * Author : Laura GUILLOT
 *********************************************************************
 */
package org.manager;

/**
 *
 * @author Laura
 */
public interface VoteManager {
    
    public void registerVote (int playerID, int sentenceID, int vote);
    public int nbVoteByPlayer (int playerID);
    public int nbVoteApprovedByPlayer(int playerID);
    public double nbWeightedVotesAgainst (int sentenceID, int vote);
    public double nbWeightedVotesFor(int sentenceID, int vote);
    public int nbVotesForSentence(int sentenceID);
    public double relevancy(int sentenceID,int  vote);
    public double calculConfidenceScore(int playerID);
    public void updateConfidenceScore(int playerID);
    public void approveTranslation (int sentenceID, int vote);
    public void updateScoresAndTranslations();
    public boolean isRegistered(int playerID, int sentenceID);
    public boolean isEqualToMajority(int sentenceID, int vote);
}
