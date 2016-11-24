/**
 * ********************************************************************
 * Controller Stat2
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

public class Stat2Controller {

    @RequestMapping(method = RequestMethod.POST)
    public ModelAndView stat2(@RequestParam("idco") String c) {
        ModelAndView result = new ModelAndView("redirect:stat2.htm");
        Encrypt e = new Encrypt();
        c = e.encrypt(c);
        result.addObject("idco", c);
        return result;
    }

    @RequestMapping(method = RequestMethod.GET)
    public ModelAndView handlestat2(HttpServletRequest request, HttpServletResponse response, @RequestParam("idco") String c) {
        ConnectManager m = ConnectManagerImpl.getInstance();
        StatManager stm = StatManagerImpl.getInstance();
        Encrypt e = new Encrypt();
        c = e.decrypt(c);
        if (m.isConnected(c)) {
            ModelAndView r = new ModelAndView("stat2");

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
            r.addObject("sent", stm.getApprovedSentences());

            return r;
        } else {
            ModelAndView result = new ModelAndView("home");
            result.addObject("isSign", false);
            return result;
        }

    }
}
