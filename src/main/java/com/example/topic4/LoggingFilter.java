package com.example.topic4;

import javax.servlet.*;
import javax.servlet.Filter;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.time.LocalDateTime;
import java.util.logging.*;

@WebFilter("/*")
public class LoggingFilter implements Filter {

    private Logger logger = Logger.getLogger(LoggingFilter.class.getName());

    @Override
    public void init(FilterConfig filterConfig) {
        try {
            FileHandler fileHandler = new FileHandler("request-log.txt", true);
            fileHandler.setFormatter(new SimpleFormatter());
            logger.addHandler(fileHandler);
            logger.setUseParentHandlers(false);
        } catch (IOException e) {
            logger.warning("Не удалось создать лог-файл: " + e.getMessage());
        }
    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
            throws IOException, ServletException {
        HttpServletRequest req = (HttpServletRequest) request;
        String url = req.getRequestURI();
        String ip = request.getRemoteAddr();
        String time = LocalDateTime.now().toString();

        logger.info("Запрос от IP: " + ip + " | URL: " + url + " | Время: " + time);

        chain.doFilter(request, response);
    }

    @Override
    public void destroy() {
    }
}
