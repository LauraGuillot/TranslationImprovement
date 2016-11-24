/**
 * ********************************************************************
 * Controller EvalPlay1End
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
import org.tables.Player;
import org.utils.Encrypt;

@Controller
public class EvalPlay1EndController {

    @RequestMapping(method = RequestMethod.POST)
    public ModelAndView eval(@RequestParam("idco") String c, @RequestParam("sid1") int sid1, @RequestParam("vote1") String vote1, @RequestParam("sid2") int sid2, @RequestParam("vote2") String vote2, @RequestParam("sid3") int sid3, @RequestParam("vote3") String vote3, @RequestParam("sid4") int sid4, @RequestParam("vote4") String vote4, @RequestParam("sid5") int sid5, @RequestParam("vote5") String vote5, @RequestParam("sid6") int sid6, @RequestParam("vote6") String vote6, @RequestParam("sid7") int sid7, @RequestParam("vote7") String vote7, @RequestParam("sid8") int sid8, @RequestParam("vote8") String vote8, @RequestParam("sid9") int sid9, @RequestParam("vote9") String vote9, @RequestParam("sid10") int sid10, @RequestParam("vote10") String vote10) {
        ModelAndView r = new ModelAndView("redirect:evalPlay1End.htm");

        Encrypt e = new Encrypt();
        c = e.encrypt(c);

        r.addObject("idco", c);
        r.addObject("sid1", sid1);
        r.addObject("sid2", sid2);
        r.addObject("sid3", sid3);
        r.addObject("sid4", sid4);
        r.addObject("sid5", sid5);
        r.addObject("sid6", sid6);
        r.addObject("sid7", sid7);
        r.addObject("sid8", sid8);
        r.addObject("sid9", sid9);
        r.addObject("sid10", sid10);
        r.addObject("vote1", vote1);
        r.addObject("vote2", vote2);
        r.addObject("vote3", vote3);
        r.addObject("vote4", vote4);
        r.addObject("vote5", vote5);
        r.addObject("vote6", vote6);
        r.addObject("vote7", vote7);
        r.addObject("vote8", vote8);
        r.addObject("vote9", vote9);
        r.addObject("vote10", vote10);
        return r;
    }

    @RequestMapping(method = RequestMethod.GET)
    public ModelAndView handleEval(HttpServletRequest request, HttpServletResponse response, @RequestParam("idco") String c, @RequestParam("sid1") int sid1, @RequestParam("vote1") String vote1, @RequestParam("sid2") int sid2, @RequestParam("vote2") String vote2, @RequestParam("sid3") int sid3, @RequestParam("vote3") String vote3, @RequestParam("sid4") int sid4, @RequestParam("vote4") String vote4, @RequestParam("sid5") int sid5, @RequestParam("vote5") String vote5, @RequestParam("sid6") int sid6, @RequestParam("vote6") String vote6, @RequestParam("sid7") int sid7, @RequestParam("vote7") String vote7, @RequestParam("sid8") int sid8, @RequestParam("vote8") String vote8, @RequestParam("sid9") int sid9, @RequestParam("vote9") String vote9, @RequestParam("sid10") int sid10, @RequestParam("vote10") String vote10) {

        ConnectManager m = ConnectManagerImpl.getInstance();
        PlayerManager mm = PlayerManagerImpl.getInstance();
        VoteManager mmm = VoteManagerImpl.getInstance();
        SentenceManager sm = SentenceManagerImpl.getInstance();

        Encrypt e = new Encrypt();
        c = e.decrypt(c);

        //If the connection is valid
        if (m.isConnected(c)) {
            ModelAndView r = new ModelAndView("evalPlay1End");

            Player p = mm.getByConnectID(c);

            //Updating the connections
            m.udpateMyConnection(c);
            m.updateConnections();

            //Register the votes
            int equalMajority = 0;
            int nbVote = 0;
            boolean refresh = false;

            //VOTE 1
            if (estUnEntier(vote1)) {
                if (!mmm.isRegistered(p.getPlayerID(), sid1)) {
                    mmm.registerVote(p.getPlayerID(), sid1, Integer.parseInt(vote1));
                } else {
                    refresh = true;
                }
                nbVote++;
                if (mmm.isEqualToMajority(sid1, Integer.parseInt(vote1))) {
                    equalMajority++;
                }
            } else {
                refresh = refresh || !sm.registerTranslation(sid1, vote1, p.getPlayerID());
            }

            //VOTE 2 
            if (estUnEntier(vote2)) {
                if (!mmm.isRegistered(p.getPlayerID(), sid2)) {
                    mmm.registerVote(p.getPlayerID(), sid2, Integer.parseInt(vote2));
                } else {
                    refresh = true;
                }
                nbVote++;
                if (mmm.isEqualToMajority(sid1, Integer.parseInt(vote2))) {
                    equalMajority++;
                }
            } else {
                refresh = refresh || sm.registerTranslation(sid2, vote2, p.getPlayerID());
            }

            //VOTE 3 
            if (estUnEntier(vote3)) {
                if (!mmm.isRegistered(p.getPlayerID(), sid3)) {
                    mmm.registerVote(p.getPlayerID(), sid3, Integer.parseInt(vote3));
                } else {
                    refresh = true;
                }
                nbVote++;

                if (mmm.isEqualToMajority(sid1, Integer.parseInt(vote3))) {
                    equalMajority++;
                }
            } else {
                refresh = refresh || sm.registerTranslation(sid3, vote3, p.getPlayerID());
            }

            //VOTE 4 
            if (estUnEntier(vote4)) {
                if (!mmm.isRegistered(p.getPlayerID(), sid4)) {
                    mmm.registerVote(p.getPlayerID(), sid4, Integer.parseInt(vote4));
                    nbVote++;
                    if (mmm.isEqualToMajority(sid1, Integer.parseInt(vote4))) {
                        equalMajority++;

                    }
                }
            } else {
                refresh = refresh || sm.registerTranslation(sid4, vote4, p.getPlayerID());
            }

            //VOTE 5 
            if (estUnEntier(vote5)) {
                if (!mmm.isRegistered(p.getPlayerID(), sid5)) {
                    mmm.registerVote(p.getPlayerID(), sid5, Integer.parseInt(vote5));
                } else {
                    refresh = true;
                }
                nbVote++;
                if (mmm.isEqualToMajority(sid1, Integer.parseInt(vote5))) {
                    equalMajority++;
                }
            } else {
                refresh = refresh || sm.registerTranslation(sid5, vote5, p.getPlayerID());
            }

            //VOTE6
            if (estUnEntier(vote6)) {
                if (!mmm.isRegistered(p.getPlayerID(), sid6)) {
                    mmm.registerVote(p.getPlayerID(), sid6, Integer.parseInt(vote6));
                } else {
                    refresh = true;
                }
                nbVote++;
                if (mmm.isEqualToMajority(sid1, Integer.parseInt(vote6))) {
                    equalMajority++;
                }
            } else {
                refresh = refresh || sm.registerTranslation(sid6, vote6, p.getPlayerID());
            }

            //VOTE 7
            if (estUnEntier(vote7)) {
                if (!mmm.isRegistered(p.getPlayerID(), sid7)) {
                    mmm.registerVote(p.getPlayerID(), sid7, Integer.parseInt(vote7));
                } else {
                    refresh = true;
                }
                nbVote++;
                if (mmm.isEqualToMajority(sid1, Integer.parseInt(vote7))) {
                    equalMajority++;
                }
            } else {
                refresh = refresh || sm.registerTranslation(sid7, vote7, p.getPlayerID());
            }

            //VOTE 8 
            if (estUnEntier(vote8)) {
                if (!mmm.isRegistered(p.getPlayerID(), sid8)) {
                    mmm.registerVote(p.getPlayerID(), sid8, Integer.parseInt(vote8));
                } else {
                    refresh = true;
                }
                nbVote++;
                if (mmm.isEqualToMajority(sid1, Integer.parseInt(vote8))) {
                    equalMajority++;
                }
            } else {
                refresh = refresh || sm.registerTranslation(sid8, vote8, p.getPlayerID());
            }

            //VOTE 9 
            if (estUnEntier(vote9)) {
                if (!mmm.isRegistered(p.getPlayerID(), sid9)) {
                    mmm.registerVote(p.getPlayerID(), sid9, Integer.parseInt(vote9));
                } else {
                    refresh = true;
                }
                nbVote++;
                if (mmm.isEqualToMajority(sid1, Integer.parseInt(vote9))) {
                    equalMajority++;
                }
            } else {
                refresh = refresh || sm.registerTranslation(sid9, vote9, p.getPlayerID());
            }

            //VOTE 10 
            if (estUnEntier(vote10)) {
                if (!mmm.isRegistered(p.getPlayerID(), sid10)) {
                    mmm.registerVote(p.getPlayerID(), sid10, Integer.parseInt(vote10));
                } else {
                    refresh = true;
                }
                nbVote++;
                if (mmm.isEqualToMajority(sid1, Integer.parseInt(vote10))) {
                    equalMajority++;
                }
            } else {
                refresh = refresh || sm.registerTranslation(sid10, vote10, p.getPlayerID());
            }

            MissionManager mim = MissionManagerImpl.getInstance();
            ArrayList<String> win;
            ArrayList<String> missions;

            if (!refresh) {
                //Update the scores
                mm.addPoints(p, 10);

                //Check the approvements
                mmm.updateScoresAndTranslations();

                //Update missions
                win = mim.updateMissionGlobal(p.getPlayerID(), nbVote, 10 - nbVote, 10, false);
                missions = mim.getMissionTxt(p.getPlayerID());

                //Update trophies
                TrophyManager tm = TrophyManagerImpl.getInstance();
                tm.updateTrophies(p.getPlayerID(), nbVote, 10 - nbVote);
            } else {
                win = new ArrayList<String>();
                missions = mim.getMissionTxt(p.getPlayerID());
            }

            //Add the parameters
            //ConnectID
            r.addObject("idco", c);
            //Player
            r.addObject("player", p);
            //Stats
            r.addObject("majo", equalMajority);
            r.addObject("nbVote", nbVote);
            //Missions
            r.addObject("missions", missions);
            r.addObject("wonMissions", win);

            return r;
        } else {
            ModelAndView result = new ModelAndView("home");
            result.addObject("isSign", false);
            return result;
        }
    }

    public static boolean estUnEntier(String chaine) {
        try {
            Integer.parseInt(chaine);
        } catch (NumberFormatException e) {
            return false;
        }
        return true;
    }
}
