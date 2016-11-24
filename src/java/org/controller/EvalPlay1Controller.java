/**
 * ********************************************************************
 * Controller EvalPlay1
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
public class EvalPlay1Controller {

    @RequestMapping(method = RequestMethod.POST)
    public ModelAndView eval(@RequestParam("idco") String c, @RequestParam("l1") String l1, @RequestParam("l2") String l2) {
        ModelAndView r = new ModelAndView("redirect:evalPlay1.htm");
        r.addObject("l1", l1);
        r.addObject("l2", l2);
        Encrypt e = new Encrypt();
        c = e.encrypt(c);
        r.addObject("idco", c);
        return r;
    }

    @RequestMapping(method = RequestMethod.GET)
    public ModelAndView handleEval(HttpServletRequest request, HttpServletResponse response, @RequestParam("idco") String c, @RequestParam("l1") String l1, @RequestParam("l2") String l2) {
        ConnectManager m = ConnectManagerImpl.getInstance();
        Encrypt e = new Encrypt();
        c = e.decrypt(c);

        //If the connection is valid
        if (m.isConnected(c)) {

            ModelAndView r = new ModelAndView("evalPlay1");

            //Updating the connections
            m.udpateMyConnection(c);
            m.updateConnections();

            //Add the parameters
            //ConnectID
            r.addObject("idco", c);

            //Player
            PlayerManager mm = PlayerManagerImpl.getInstance();
            Player p = mm.getByConnectID(c);
            r.addObject("player", p);

            //Sentences
            SentenceManager mmm = SentenceManagerImpl.getInstance();
            ArrayList<Sentence> tab = mmm.genere10(p.getPlayerID(), l1, l2);
            ArrayList<ArrayList<Topic>> top = mmm.getTopics(tab);
            ArrayList<ArrayList<Integer>> w = mmm.getWeight(top, tab);
            ArrayList<ArrayList<Translation>> trans = mmm.getTranslations(tab);
            int n = tab.size();
            r.addObject("n", n);
            r.addObject("sentences", tab);
            r.addObject("topics", top);
            r.addObject("weight", w);
            r.addObject("translations", trans);

            return r;

        } else {
            ModelAndView result = new ModelAndView("home");
            result.addObject("isSign", false);
            return result;
        }
    }
}
