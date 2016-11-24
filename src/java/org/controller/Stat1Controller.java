/**
 * ********************************************************************
 * Controller Stat1
 * --------------------------------------------------------------------
 * Last update : 16/06/2016
 * Author : Laura GUILLOT
 *********************************************************************
 */
package org.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.manager.*;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import org.tables.Player;
import org.utils.Encrypt;

public class Stat1Controller {

    /**
     * @param c - ConnectID
     * @return View "Stat1"
     */
    @RequestMapping(method = RequestMethod.POST)
    public ModelAndView stat1(@RequestParam("idco") String c) {

        ModelAndView result = new ModelAndView("redirect:stat1.htm");
        Encrypt e = new Encrypt();
        c = e.encrypt(c);
        result.addObject("idco", c);
        return result;
    }

    /**
     * @param request Servlet request
     * @param response Servlet response
     * @param c Connect id
     * @return View "stat1"
     */
    @RequestMapping(method = RequestMethod.GET)
    public ModelAndView handlestat1(HttpServletRequest request, HttpServletResponse response, @RequestParam("idco") String c) {
        ConnectManager m = ConnectManagerImpl.getInstance();
        StatManager stm = StatManagerImpl.getInstance();
Encrypt e = new Encrypt();
        c = e.decrypt(c);
        if (m.isConnected(c)) {
            ModelAndView r = new ModelAndView("stat1");

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

            //Boolean : isSign = true to say that the connection is ok
            r.addObject("isSign", true);

            //Stat
            r.addObject("nbPlayers", stm.nbPlayers());
            r.addObject("nbApprovedSent", stm.NbValidatedSent());
            r.addObject("nbInprogressSent", stm.nbInProgressSent());
            r.addObject("nbVotes", stm.nbvotes());
            r.addObject("nbSub", stm.nbSub());

            return r;
        } else {
            ModelAndView result = new ModelAndView("home");
            result.addObject("isSign", false);
            return result;
        }
    }
}
