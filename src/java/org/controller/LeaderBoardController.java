/**
 * ********************************************************************
 * Controller LeaderBoard
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
public class LeaderBoardController {

    @RequestMapping(method = RequestMethod.POST)
    public ModelAndView leaderboard(@RequestParam("idco") String c) {
        ModelAndView result = new ModelAndView("redirect:leaderboard.htm");
        Encrypt e = new Encrypt();
        c = e.encrypt(c);
        result.addObject("idco", c);
        return result;
    }

    @RequestMapping(method = RequestMethod.GET)
    public ModelAndView handleLeader(HttpServletRequest request, HttpServletResponse response, @RequestParam("idco") String c) {
        ConnectManager m = ConnectManagerImpl.getInstance();
        Encrypt e = new Encrypt();
        c = e.decrypt(c);
        //If the connection is valid
        if (m.isConnected(c)) {
            ModelAndView r = new ModelAndView("leaderboard");

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
            //List of the players ranked by points
            r.addObject("t1", mm.leaderboardPoint());
            r.addObject("r1", mm.rankPoint(mm.leaderboardPoint()));
            //List of the players ranked by score
            r.addObject("t2", mm.leaderboardScore());
            r.addObject("r2", mm.rankCS(mm.leaderboardScore()));

            return r;

        } else {
            ModelAndView result = new ModelAndView("home");
            result.addObject("isSign", false);
            return result;
        }
    }
}
