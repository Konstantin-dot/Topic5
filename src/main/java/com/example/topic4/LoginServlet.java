package com.example.topic4;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

@WebServlet("/login")
public class LoginServlet extends HttpServlet {

    private Map<String, String> users = new HashMap<>();

    @Override
    public void init(ServletConfig config) throws ServletException {
        super.init(config);
        // Инициализация пользователей (логин - пароль)
        users.put("user1", "pass1");
        users.put("user2", "pass2");
        users.put("admin", "pass3");
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        // Домашняя страница
        resp.setContentType("text/html;charset=UTF-8");
        resp.getWriter().println("<html><body>");
        resp.getWriter().println("<h2>Welcome, enter the entrance and password</h2>");
        resp.getWriter().println("<form method='post' action='login'>");
        resp.getWriter().println("Username: <input type='text' name='username'><br>");
        resp.getWriter().println("Password: <input type='password' name='password'><br>");
        resp.getWriter().println("<input type='submit' value='Click'>");
        resp.getWriter().println("</form>");
        resp.getWriter().println("</body></html>");
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        // Получаем логин и пароль из формы
        String username = req.getParameter("username");
        String password = req.getParameter("password");

        // Проверяем учетные данные
        if (username != null && password != null && password.equals(users.get(username))) {
            // Создаем сессию
            HttpSession session = req.getSession(true);
            session.setAttribute("username", username);

            // Создаем cookie с именем пользователя
            Cookie userCookie = new Cookie("username", username);
            userCookie.setMaxAge(60 * 60);
            resp.addCookie(userCookie);

            // Перенаправляем на WelcomeServlet
            resp.sendRedirect("welcome");
        } else {
            resp.setContentType("text/html;charset=UTF-8");
            resp.getWriter().println("<html><body>");
            resp.getWriter().println("<h2>Invalid login or password :(</h2>");
            resp.getWriter().println("<a href='login'>Try again</a>");
            resp.getWriter().println("</body></html>");
        }
    }
}
