/**
 * ********************************************************************
 * Controller Deco
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

@Controller
public class DecoController {

    @RequestMapping(method = RequestMethod.POST)
    public ModelAndView deco(@RequestParam("connectID") String c) {
        ModelAndView r = new ModelAndView("redirect:home.htm");
        r.addObject("connectID", c);
        return r;
    }

    @RequestMapping(method = RequestMethod.GET)
    public ModelAndView handleDeco(HttpServletRequest request, HttpServletResponse response, @RequestParam("connectID") String c) {
        ConnectManager m = ConnectManagerImpl.getInstance();
        m.deco(c);
        ModelAndView r = new ModelAndView("home");
        r.addObject("isSign", false);
        return r;
    }
}
