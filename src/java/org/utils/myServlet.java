/**
 * ********************************************************************
 * Class myServlet
 * Servlet to translate in real time a meeting
 * --------------------------------------------------------------------
 * Last update : 16/06/2016
 * Author : Laura GUILLOT
 *********************************************************************
 */
package org.utils;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet(name = "myServlet", urlPatterns = {"/myServlet"})
public class myServlet extends HttpServlet {

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String l1 = request.getParameter("l1");
        String l2 = request.getParameter("l2");
        String s = request.getParameter("s");
        ScriptTranslator st = new ScriptTranslator(l1, l2, s);
        String tr = st.translate();
        response.setContentType("text/html; charset=UTF-8");
        response.getWriter().write(tr);
    }

}
