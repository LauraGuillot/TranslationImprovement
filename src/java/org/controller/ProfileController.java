/**
 * ********************************************************************
 * Controller Profile
 * --------------------------------------------------------------------
 * Last update : 16/06/2016
 * Author : Laura GUILLOT
 *********************************************************************
 */
package org.controller;

import java.io.UnsupportedEncodingException;
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
public class ProfileController {

    @RequestMapping(method = RequestMethod.POST)
    public ModelAndView profile(@RequestParam("idco") String c) throws Exception {
        ModelAndView result = new ModelAndView("redirect:profile.htm");
        Encrypt e = new Encrypt();
        c = e.encrypt(c);
        result.addObject("idco", c);

        return result;
    }

    @RequestMapping(method = RequestMethod.GET)
    public ModelAndView handleProfile(HttpServletRequest request, HttpServletResponse response, @RequestParam("idco") String co) throws UnsupportedEncodingException {
        ConnectManager m = ConnectManagerImpl.getInstance();
        Encrypt e = new Encrypt();
        String c = e.decrypt(co);

        if (m.isConnected(c)) {
            ModelAndView r = new ModelAndView("profile");

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

            //Rank
            r.addObject("rank", mm.getRank(p));

            //Trophies
            TrophyManager tm = TrophyManagerImpl.getInstance();
            r.addObject("trophies", tm.getMyTrophies(p.getPlayerID()));

            //Missions
            MissionManager mim = MissionManagerImpl.getInstance();
            ArrayList<String> missions = mim.getMissionTxt(p.getPlayerID());
            r.addObject("missions", missions);

            return r;
        } else {
            ModelAndView result = new ModelAndView("home");
            result.addObject("isSign", false);
            return result;
        }
    }
}
