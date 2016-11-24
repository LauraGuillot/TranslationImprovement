/**
 * ********************************************************************
 * Controller TopicAdd
 * --------------------------------------------------------------------
 * Last update : 16/06/2016
 * Author : Laura GUILLOT
 *********************************************************************
 */
package org.controller;

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

@Controller
public class TopicAddController {

    @RequestMapping(method = RequestMethod.POST)

    public ModelAndView addItem(@RequestParam("playerName") String name, @RequestParam("topics") String t) {
        ModelAndView result = new ModelAndView("redirect:home4.htm");

        Encrypt e = new Encrypt();
        name = e.encrypt(name);
        t = e.encrypt(t);

        result.addObject("playerName", name);
        result.addObject("topics", t);
        return result;
    }

    @RequestMapping(method = RequestMethod.GET)
    public ModelAndView handleHome(HttpServletRequest request, HttpServletResponse response, @RequestParam("playerName") String name, @RequestParam("topics") String t) {
        ModelAndView returnedValue;
        Encrypt e = new Encrypt();
        name = e.decrypt(name);
        t = e.decrypt(t);

        if (!name.equals("")) {
            PlayerManager m = PlayerManagerImpl.getInstance();

            //Add the player
            int id = m.add(name);
            Player pl = m.getByPlayerId(id);

            //Add his topics
            TopicManager mm = TopicManagerImpl.getInstance();

            StringTokenizer st = new StringTokenizer(t, ",");
            while (st.hasMoreTokens()) {
                String tid = st.nextToken();
                mm.addTopic(Integer.parseInt((String) tid), id);
            }

            //Add trophies
            TrophyManager tm = TrophyManagerImpl.getInstance();
            tm.addNewTrophies(id);

            //AddMissions
            MissionManager mim = MissionManagerImpl.getInstance();
            mim.get3New(id);

            //Return the view
            String idco = m.connect(pl);
            returnedValue = new ModelAndView("home");
            returnedValue.addObject("player", pl);
            returnedValue.addObject("idco", idco);
            returnedValue.addObject("isSign", true);

        } else {
            returnedValue = new ModelAndView("home");
            returnedValue.addObject("isSign", false);
        }
        return returnedValue;
    }
}
