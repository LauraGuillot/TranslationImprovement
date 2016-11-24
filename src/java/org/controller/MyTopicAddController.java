/**
 * ********************************************************************
 * Controller MyTopicAdd
 * --------------------------------------------------------------------
 * Last update : 16/06/2016
 * Author : Laura GUILLOT
 *********************************************************************
 */
package org.controller;

import java.util.ArrayList;
import java.util.List;
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
public class MyTopicAddController {

    @RequestMapping(method = RequestMethod.POST)

    public ModelAndView addItem(@RequestParam("idco") String c, @RequestParam("topics") String t) {
        ModelAndView result = new ModelAndView("redirect:myTopics2.htm");

        Encrypt e = new Encrypt();
        c = e.encrypt(c);
        t = e.encrypt(t);

        result.addObject("idco", c);
        result.addObject("topics", t);
        return result;
    }

    @RequestMapping(method = RequestMethod.GET)
    public ModelAndView handleHome(HttpServletRequest request, HttpServletResponse response, @RequestParam("idco") String c, @RequestParam("topics") String t) {
        ConnectManager m = ConnectManagerImpl.getInstance();

        Encrypt e = new Encrypt();
        c = e.decrypt(c);
        t = e.decrypt(t);

        if (m.isConnected(c)) {
            ModelAndView r = new ModelAndView("myTopics");

            //Updating the connections
            m.udpateMyConnection(c);
            m.updateConnections();

            PlayerManager mm = PlayerManagerImpl.getInstance();
            Player p = mm.getByConnectID(c);

            //Add his topics
            TopicManager mmn = TopicManagerImpl.getInstance();

            StringTokenizer st = new StringTokenizer(t, ",");
            while (st.hasMoreTokens()) {
                String tid = st.nextToken();
                mmn.addTopic(Integer.parseInt((String) tid), p.getPlayerID());
            }

            //Add the parameters
            //ConnectID
            r.addObject("idco", c);

            //Player
            r.addObject("player", p);

            //All the Topics
            TopicManager tp = TopicManagerImpl.getInstance();
            List topics = tp.getTopics();
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
