/**
 * ********************************************************************
 * Controller MyTopic
 * --------------------------------------------------------------------
 * Last update : 16/06/2016
 * Author : Laura GUILLOT
 *********************************************************************
 */
package org.controller;

import java.util.ArrayList;
import java.util.List;
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
public class MyTopicController {

    @RequestMapping(method = RequestMethod.POST)
    public ModelAndView profile(@RequestParam("idco") String c) {
        ModelAndView result = new ModelAndView("redirect:myTopics.htm");
        Encrypt e = new Encrypt();
        c = e.encrypt(c);
        result.addObject("idco", c);
        return result;
    }

    @RequestMapping(method = RequestMethod.GET)
    public ModelAndView handleProfile(HttpServletRequest request, HttpServletResponse response, @RequestParam("idco") String c) {
        ConnectManager m = ConnectManagerImpl.getInstance();
        Encrypt e = new Encrypt();
        c = e.decrypt(c);
        if (m.isConnected(c)) {
            ModelAndView r = new ModelAndView("myTopics");

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

            //All the Topics
            TopicManager tp = TopicManagerImpl.getInstance();
            List topics = tp.getOthersTopics(p.getPlayerID());
            r.addObject("topics", topics);

            //Topics of the player
            ArrayList<Topic> myTopics = tp.myTopics(p.getPlayerID());
            r.addObject("myTopics", myTopics);

            return r;
        } else {
            ModelAndView result = new ModelAndView("home");
            result.addObject("isSign", false);
            return result;
        }
    }
}
