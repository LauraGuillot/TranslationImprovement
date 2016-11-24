/**
 * ********************************************************************
 * Controller SubmitPlay
 * --------------------------------------------------------------------
 * Last update : 16/06/2016
 * Author : Laura GUILLOT
 *********************************************************************
 */
package org.controller;

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
public class SubmitPlayController {

    @RequestMapping(method = RequestMethod.POST)
    public ModelAndView submit(@RequestParam("idco") String c, @RequestParam("l1") String l1, @RequestParam("l2") String l2) {
        ModelAndView result = new ModelAndView("redirect:submitPlay.htm");
        Encrypt e = new Encrypt();
        c = e.encrypt(c);
        result.addObject("idco", c);
        result.addObject("l1", l1);
        result.addObject("l2", l2);
        return result;
    }

    @RequestMapping(method = RequestMethod.GET)
    public ModelAndView handleSubmit(HttpServletRequest request, HttpServletResponse response, @RequestParam("idco") String c, @RequestParam("l1") String l1, @RequestParam("l2") String l2) {
        ConnectManager m = ConnectManagerImpl.getInstance();

        Encrypt e = new Encrypt();
        c = e.decrypt(c);

        if (m.isConnected(c)) {
            ModelAndView r = new ModelAndView("submitPlay");

            //Updating the connections
            m.udpateMyConnection(c);
            m.updateConnections();

            //Add the parameters
            r.addObject("idco", c);

            PlayerManager mm = PlayerManagerImpl.getInstance();
            Player p = mm.getByConnectID(c);
            r.addObject("player", p);
            r.addObject("l1", l1);
            r.addObject("l2", l2);

            return r;
        } else {
            ModelAndView result = new ModelAndView("home");
            result.addObject("isSign", false);
            return result;
        }
    }
}
