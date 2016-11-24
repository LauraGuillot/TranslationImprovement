/**
 * ********************************************************************
 * Controller MySubmissions
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
public class MySubmissionsController {

    @RequestMapping(method = RequestMethod.POST)
    public ModelAndView profile(@RequestParam("idco") String c) {
        ModelAndView r = new ModelAndView("redirect:mySubmissions.htm");
        Encrypt e = new Encrypt();
        c = e.encrypt(c);
        r.addObject("idco", c);
        return r;
    }

    @RequestMapping(method = RequestMethod.GET)
    public ModelAndView handleProfile(HttpServletRequest request, HttpServletResponse response, @RequestParam("idco") String c) {
        ConnectManager m = ConnectManagerImpl.getInstance();
        Encrypt e = new Encrypt();
        c = e.decrypt(c);

        if (m.isConnected(c)) {
            ModelAndView r = new ModelAndView("mySubmissions");

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

            //Submissions
            SentenceManager sm = SentenceManagerImpl.getInstance();
            ArrayList<Translation> tr = sm.mySubmissions(p.getPlayerID());
            ArrayList<Sentence> s = sm.mySubmissionsSentences(tr);
            ArrayList<Translation> tr2 = sm.getTranslationsApp(s);
             ArrayList<Double> vu = sm.votesFor(tr);
            ArrayList<Double> vs = sm.votesAgainst(tr);
           

            r.addObject("sub", s);
            r.addObject("vu", vu);
            r.addObject("vs", vs);
            r.addObject("tr", tr);
            r.addObject("tr2", tr2);

            return r;
        } else {
            ModelAndView result = new ModelAndView("home");
            result.addObject("isSign", false);
            return result;
        }
    }
}
