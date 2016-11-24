/**
 * ********************************************************************
 * Controller PlayerAdd
 * --------------------------------------------------------------------
 * Last update : 16/06/2016
 * Author : Laura GUILLOT
 *********************************************************************
 */
package org.controller;

import java.util.List;
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
public class PlayerAddController {

    @RequestMapping(method = RequestMethod.POST)
    public ModelAndView connectPlayer(@RequestParam("playerName") String name) {
        ModelAndView result = new ModelAndView("redirect:home2.htm");
        Encrypt e = new Encrypt();
        name = e.encrypt(name);
        result.addObject("playerName", name);
        return result;
    }

    @RequestMapping(method = RequestMethod.GET)
    public ModelAndView handleHome(HttpServletRequest request, HttpServletResponse response, @RequestParam("playerName") String name) {
        ModelAndView returnedValue;

        Encrypt e = new Encrypt();
        name = e.decrypt(name);

        if (!name.equals("")) {
            PlayerManager m = PlayerManagerImpl.getInstance();

            //If the player is in the database
            if (m.isIN(name)) {

                //We connect him
                int id = m.add(name);
                Player pl = m.getByPlayerId(id);
                String idco = m.connect(pl);

                //Add the parameters to the view
                returnedValue = new ModelAndView("home");
                //Player
                returnedValue.addObject("player", pl);
                //ConnectID
                returnedValue.addObject("idco", idco);
                //Boolean : isSign = true to say that the player is well connected
                returnedValue.addObject("isSign", true);

            } else {

                //If the player is not in the database (first connection)
                //We redirect him toward a page where he could chose some topics
                TopicManager mm = TopicManagerImpl.getInstance();
                List topics = mm.getTopics();

                //Add the parameters to the view
                returnedValue = new ModelAndView("signTopic");
                //Name of the player
                returnedValue.addObject("playerName", name);
                //List of all the topics
                returnedValue.addObject("topics", topics);

            }
        } else {
            returnedValue = new ModelAndView("home");
            returnedValue.addObject("isSign", false);
        }
        return returnedValue;
    }
}
