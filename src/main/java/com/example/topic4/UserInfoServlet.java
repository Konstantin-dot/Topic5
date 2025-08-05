package com.example.topic4;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.io.IOException;

@WebServlet("/userinfo")
public class UserInfoServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setContentType("text/html;charset=UTF-8");

        HttpSession session = req.getSession(false);

        resp.getWriter().println("<html><body>");

        if (session != null && session.getAttribute("username") != null) {
            String username = (String) session.getAttribute("username");
            resp.getWriter().println("<h2>User Information</h2>");
            resp.getWriter().println("<p>Username: " + username + "</p>");
        } else {
            resp.getWriter().println("<h2>No user info available. Please login first.</h2>");
            resp.getWriter().println("<a href='login'>Login</a>");
        }

        resp.getWriter().println("</body></html>");
    }
}
