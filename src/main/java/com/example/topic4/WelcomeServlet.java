package com.example.topic4;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
        import java.io.IOException;

@WebServlet("/welcome")
public class WelcomeServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setContentType("text/html;charset=UTF-8");

        // Читаем cookie с именем пользователя
        String usernameFromCookie = null;
        Cookie[] cookies = req.getCookies();
        if (cookies != null) {
            for (Cookie c : cookies) {
                if ("username".equals(c.getName())) {
                    usernameFromCookie = c.getValue();
                    break;
                }
            }
        }

        resp.getWriter().println("<html><body>");
        if (usernameFromCookie != null) {
            resp.getWriter().println("<h1>Welcome, " + usernameFromCookie + "!</h1>");
        } else {
            resp.getWriter().println("<h1>Welcome, guest!</h1>");
        }

        resp.getWriter().println("<form action='userinfo'>");
        resp.getWriter().println("<input type='submit' value='User Info'>");
        resp.getWriter().println("</form>");

        resp.getWriter().println("</body></html>");
    }
}
