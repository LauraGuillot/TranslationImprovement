/**
 * ********************************************************************
 * Controller Player
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
import org.springframework.web.servlet.ModelAndView;

@Controller
public class PlayerController {

    /**
     * Méthode handleHello - Accès : GET - Vue renvoyée : hello avec un User en
     * paramètre
     *
     * @param request ServletRequest
     * @param response ResponseRequest
     * @return
     */
    @RequestMapping(method = RequestMethod.GET)
    public ModelAndView handlePlayer(HttpServletRequest request, HttpServletResponse response) {
        ModelAndView result = new ModelAndView("home");
        result.addObject("isSign", false);
        return result;
    }
}
