/**
 * ********************************************************************
 * Controller Eval
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
public class EvalController {

    @RequestMapping(method = RequestMethod.POST)
    public ModelAndView eval(@RequestParam("idco") String c) {
        ModelAndView r = new ModelAndView("redirect:eval.htm");
        Encrypt e = new Encrypt();
        c = e.encrypt(c);
        r.addObject("idco", c);
        return r;
    }

    @RequestMapping(method = RequestMethod.GET)
    public ModelAndView handleEval(HttpServletRequest request, HttpServletResponse response, @RequestParam("idco") String c) {

        ConnectManager m = ConnectManagerImpl.getInstance();
        Encrypt e = new Encrypt();
        c = e.decrypt(c);
        //If the connection is valid
        if (m.isConnected(c)) {
            ModelAndView r = new ModelAndView("eval");

            //Update the connections
            m.udpateMyConnection(c);
            m.updateConnections();

            //Add the parameters
            //ConnectID
            r.addObject("idco", c);
            //Player
            PlayerManager mm = PlayerManagerImpl.getInstance();
            Player p = mm.getByConnectID(c);
            r.addObject("player", p);

            return r;
        } else {
            ModelAndView result = new ModelAndView("home");
            result.addObject("isSign", false);
            return result;
        }
    }
}
