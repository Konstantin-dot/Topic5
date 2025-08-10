package com.example.topic4;

import javax.servlet.annotation.WebListener;
import javax.servlet.http.*;
import java.time.LocalDateTime;
import java.util.logging.*;

@WebListener
public class UserActivityListener implements HttpSessionListener, HttpSessionAttributeListener {

    private Logger logger = Logger.getLogger(UserActivityListener.class.getName());

    public UserActivityListener() {
        try {
            FileHandler fileHandler = new FileHandler("user-activity-log.txt", true);
            fileHandler.setFormatter(new SimpleFormatter());
            logger.addHandler(fileHandler);
            logger.setUseParentHandlers(false);
        } catch (Exception e) {
            logger.warning("Ошибка при создании логгера: " + e.getMessage());
        }
    }

    @Override
    public void sessionCreated(HttpSessionEvent se) {
        logger.info("Сессия создана в: " + LocalDateTime.now());
    }

    @Override
    public void sessionDestroyed(HttpSessionEvent se) {
        logger.info("Сессия завершена в: " + LocalDateTime.now());
    }

    @Override
    public void attributeAdded(HttpSessionBindingEvent event) {
        if ("username".equals(event.getName())) {
            logger.info("Пользователь вошёл: " + event.getValue() + " в " + LocalDateTime.now());
        }
    }

    @Override
    public void attributeRemoved(HttpSessionBindingEvent event) {
        if ("username".equals(event.getName())) {
            logger.info("Пользователь вышел: " + event.getValue() + " в " + LocalDateTime.now());
        }
    }

    @Override
    public void attributeReplaced(HttpSessionBindingEvent event) {
    }
}
