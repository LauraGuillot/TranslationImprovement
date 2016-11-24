/**
 * ********************************************************************
 * Controller EvalPlay2
 * --------------------------------------------------------------------
 * Last update : 16/06/2016
 * Author : Laura GUILLOT
 *********************************************************************
 */
package org.controller;

import java.util.ArrayList;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.manager.*;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import org.tables.*;
import org.utils.Encrypt;

@Controller
public class EvalPlay2Controller {

    @RequestMapping(method = RequestMethod.POST)
    public ModelAndView eval(@RequestParam("idco") String c, @RequestParam("n") int n, @RequestParam("l1") String l1, @RequestParam("l2") String l2, @RequestParam("vote") int v, @RequestParam("sentenceID") int sentID) {
        ModelAndView r = new ModelAndView("redirect:evalPlay2.htm");
        Encrypt e = new Encrypt();
        c = e.encrypt(c);
        String n1 = e.encrypt(n + "");

        r.addObject("idco", c);
        r.addObject("n1", n1);
        r.addObject("l1", l1);
        r.addObject("l2", l2);
        r.addObject("vote", v);
        r.addObject("sentenceID", sentID);

        return r;
    }

    @RequestMapping(method = RequestMethod.GET)
    public ModelAndView handleEval(HttpServletRequest request, HttpServletResponse response, @RequestParam("idco") String c, @RequestParam("n1") String n1, @RequestParam("l1") String l1, @RequestParam("l2") String l2, @RequestParam("vote") int v, @RequestParam("sentenceID") int sentID) {

        ConnectManager m = ConnectManagerImpl.getInstance();
        Encrypt e = new Encrypt();
        c = e.decrypt(c);
        int n = Integer.parseInt(e.decrypt(n1));

        //FIRST SENTENCE
        if (n == 0) {
            if (m.isConnected(c)) {

                PlayerManager mm = PlayerManagerImpl.getInstance();
                Player p = mm.getByConnectID(c);

                //Updating the connections
                m.udpateMyConnection(c);
                m.updateConnections();

                // Getting a sentence
                Boolean available;
                Sentence s;
                ArrayList<Topic> topics = new ArrayList<Topic>();
                ArrayList<Translation> translations = new ArrayList<Translation>();
                SentenceManager sm = SentenceManagerImpl.getInstance();
                ArrayList<Integer> w = new ArrayList<Integer>();
                s = sm.getSentence(p, l1, l2);

                if (s != null) {
                    available = true;
                    TopicManager tm = TopicManagerImpl.getInstance();
                    topics = tm.getTopics(s);
                    translations = sm.getTranslationsSentence(s);
                    w = sm.getWeightSentence(topics, s);
                } else {
                    available = false;
                    s = null;
                }
                ModelAndView r;
                if (available) {
                    r = new ModelAndView("evalPlay2");
                    //Add the parameters
                    r.addObject("weight", w);
                    r.addObject("l1", l1);
                    r.addObject("l2", l2);
                    r.addObject("n", n + 1);
                    r.addObject("available", available);
                    r.addObject("sentence", s);
                    r.addObject("translations", translations);
                    r.addObject("idco", c);
                    r.addObject("player", p);
                    r.addObject("topics", topics);
                } else {
                    r = new ModelAndView("evalPlay1");
                    r.addObject("n", 0);
                    r.addObject("idco", c);
                    r.addObject("player", p);

                }
                return r;
            } else {
                ModelAndView result = new ModelAndView("home");
                result.addObject("isSign", false);
                return result;
            }
            //THEN
        } else {

            PlayerManager mm = PlayerManagerImpl.getInstance();
            Player p = mm.getByConnectID(c);

            //Updating the connections
            m.udpateMyConnection(c);
            m.updateConnections();

            //Register the vote
            VoteManager mmm = VoteManagerImpl.getInstance();
            boolean refresh = mmm.isRegistered(p.getPlayerID(), sentID);
            System.out.println(refresh);
            mmm.registerVote(p.getPlayerID(), sentID, v);

            // Getting a sentence
            Boolean available;
            Sentence s;
            ArrayList<Topic> topics = new ArrayList<Topic>();
            ArrayList<Translation> translations = new ArrayList<Translation>();
            SentenceManager sm = SentenceManagerImpl.getInstance();
            ArrayList<Integer> w = new ArrayList<Integer>();
            s = sm.getSentence(p, l1, l2);

            if (s != null) {
                available = true;
                TopicManager tm = TopicManagerImpl.getInstance();
                topics = tm.getTopics(s);
                translations = sm.getTranslationsSentence(s);
                w = sm.getWeightSentence(topics, s);
            } else {
                available = false;
                s = null;
            }

            //Test Gagnant
            boolean win = mmm.isEqualToMajority(sentID, v);

            if (win) {
                if (available) {
                    ModelAndView r = new ModelAndView("evalPlay2");

                    //Add the parameters
                    r.addObject("weight", w);
                    r.addObject("l1", l1);
                    r.addObject("l2", l2);
                    r.addObject("n", n + 1);
                    r.addObject("available", available);
                    r.addObject("sentence", s);
                    r.addObject("translations", translations);
                    r.addObject("idco", c);
                    r.addObject("player", p);
                    r.addObject("topics", topics);

                    return r;
                } else {

                    MissionManager mim = MissionManagerImpl.getInstance();
                    ArrayList<String> winm = new ArrayList<String>();

                    if (!refresh) {
                        //Update the scores
                        mm.addPoints(p, n);

                        //Check the approvements
                        mmm.updateScoresAndTranslations();

                        //Update missions
                        winm = mim.updateMissionGlobal(p.getPlayerID(), n, 0, n, n >= 20);

                        //Update trophies
                        TrophyManager tm = TrophyManagerImpl.getInstance();
                        tm.updateTrophies(p.getPlayerID(), n, 0);
                    }

                    ArrayList<String> missions = mim.getMissionTxt(p.getPlayerID());

                    ModelAndView r = new ModelAndView("evalPlay2End");

                    //Add the parameters
                    r.addObject("n", n);
                    r.addObject("status", 0);
                    r.addObject("idco", c);
                    r.addObject("player", p);
                    r.addObject("missions", missions);
                    r.addObject("wonMissions", winm);

                    return r;
                }
            } else {
                ModelAndView r = new ModelAndView("evalPlay2End");

                MissionManager mim = MissionManagerImpl.getInstance();
                ArrayList<String> winm = new ArrayList<String>();

                if (!refresh) {
                    //Update the scores
                    mm.addPoints(p, n);

                    //Check the approvements
                    mmm.updateScoresAndTranslations();

                    //Update missions
                    winm = mim.updateMissionGlobal(p.getPlayerID(), n, 0, n, n >= 20);

                    //Update trophies
                    TrophyManager tm = TrophyManagerImpl.getInstance();
                    tm.updateTrophies(p.getPlayerID(), n, 0);
                }

                ArrayList<String> missions = mim.getMissionTxt(p.getPlayerID());

                //Add the parameters
                r.addObject("n", n);
                r.addObject("status", 1);
                r.addObject("idco", c);
                r.addObject("player", p);
                r.addObject("missions", missions);
                r.addObject("wonMissions", winm);

                return r;
            }
        }

    }
}
