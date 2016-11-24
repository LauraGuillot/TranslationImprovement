/**
 * ********************************************************************
 * Controller SubmitEnd
 * --------------------------------------------------------------------
 * Last update : 16/06/2016
 * Author : Laura GUILLOT
 *********************************************************************
 */
package org.controller;

import java.util.ArrayList;
import java.util.StringTokenizer;
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
import topicLabelling.*;
import org.utils.*;
import topicLabelling.lda.models.Score;

@Controller
public class SubmitEndController {

    @RequestMapping(method = RequestMethod.POST)
    public ModelAndView submit(@RequestParam("idco") String c, @RequestParam("l1") String l1, @RequestParam("l2") String l2, @RequestParam("cpt") int cpt, @RequestParam("sentences") String sentences, @RequestParam("tr1") String tr1, @RequestParam("tr2") String tr2, @RequestParam("script") String script) {
        ModelAndView result = new ModelAndView("redirect:submitEnd.htm");

        Encrypt e = new Encrypt();
        c = e.encrypt(c);
        String cpt1 = e.encrypt(cpt + "");

        result.addObject("idco", c);
        result.addObject("l1", l1);
        result.addObject("l2", l2);
        result.addObject("cpt1", cpt1);
        result.addObject("sentences", sentences);
        result.addObject("tr1", tr1);
        result.addObject("tr2", tr2);
        result.addObject("script", script);
        return result;
    }

    @RequestMapping(method = RequestMethod.GET)
    public ModelAndView handleSubmit(HttpServletRequest request, HttpServletResponse response, @RequestParam("idco") String c, @RequestParam("l1") String l1, @RequestParam("l2") String l2, @RequestParam("cpt1") String cpt1, @RequestParam("sentences") String sentences, @RequestParam("tr1") String tr1, @RequestParam("tr2") String tr2, @RequestParam("script") String script) {
        ConnectManager m = ConnectManagerImpl.getInstance();
        SentenceManager sm = SentenceManagerImpl.getInstance();
        VoteManager mmm = VoteManagerImpl.getInstance();

        Encrypt e = new Encrypt();
        c = e.decrypt(c);
        int cpt = Integer.parseInt(e.decrypt(cpt1));
        if (m.isConnected(c)) {
            ModelAndView r = new ModelAndView("submitEnd");

            PlayerManager mm = PlayerManagerImpl.getInstance();
            Player p = mm.getByConnectID(c);

            //Updating the connections
            m.udpateMyConnection(c);
            m.updateConnections();

            //Retrieve the topics
            ArrayList<String> topics = new ArrayList<String>();
            ArrayList<Integer> weight = new ArrayList<Integer>();

            //Translation into English for topic detection
            ScriptTranslator str = new ScriptTranslator(l1, l2, script);
            str.translateScript();
            script = str.getScript();

            //Topic detection
            if (!script.equals("")) {
                TopicDetection top = new TopicDetection(script);
                ArrayList<Score> topScores = top.detect();

                if (topScores.size() > 6) {
                    for (int i = 0; i < 6; i++) {
                        topics.add(topScores.get(i).getWord());
                        double d = topScores.get(i).getSc();
                        int w = Math.round((float) d);
                        weight.add(w);
                    }
                } else {
                    for (int i = 0; i < topScores.size(); i++) {
                        topics.add(topScores.get(i).getWord());
                        double d = topScores.get(i).getSc();
                        int w = Math.round((float) d);
                        weight.add(w);
                    }
                }
            }

            //Register the submissions
            ArrayList<String> sentencesList = cut(sentences);
            ArrayList<String> tr1List = cut(tr1);
            ArrayList<String> tr2List = cut(tr2);

            Boolean refresh = false;

            for (int i = 0; i < sentencesList.size(); i++) {
                refresh = refresh || sm.isregisterSentence(sentencesList.get(i), p.getPlayerID(), l1, l2);
                sm.registerSentence(sentencesList.get(i), tr1List.get(i), 0, l1, l2, topics, weight, false);
                sm.registerSentence(sentencesList.get(i), tr2List.get(i), p.getPlayerID(), l1, l2, topics, weight, true);
            }

            MissionManager mim = MissionManagerImpl.getInstance();
            ArrayList<String> win = new ArrayList<String>();
            ArrayList<String> missions = mim.getMissionTxt(p.getPlayerID());

            if (!refresh) {
                //Update the score
                mm.addPoints(p, cpt);
                mmm.updateScoresAndTranslations();

                //Update Tropies
                TrophyManager tm = TrophyManagerImpl.getInstance();
                tm.updateTrophies(p.getPlayerID(), 0, cpt);

                //Update Missions
                win = mim.updateMissionGlobal(p.getPlayerID(), 0, cpt, cpt, false);

            }

            //Add the parameters
            r.addObject("idco", c);
            r.addObject("player", p);
            r.addObject("cpt", cpt);
            r.addObject("missions", missions);
            r.addObject("wonMissions", win);

            return r;
        } else {
            ModelAndView result = new ModelAndView("home");
            result.addObject("isSign", false);
            return result;
        }
    }

    public static ArrayList<String> cut(String s) {
        ArrayList<String> l = new ArrayList<String>();

        StringTokenizer st = new StringTokenizer(s, "|");
        while (st.hasMoreTokens()) {
            l.add(st.nextToken());
        }
        return l;
    }
}
