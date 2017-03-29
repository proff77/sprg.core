package com.galiev.sprg.core;

import com.galiev.sprg.core.beans.Client;
import com.galiev.sprg.core.beans.Event;
import com.galiev.sprg.core.loggers.EventLogger;
import com.galiev.sprg.core.beans.EventType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.util.Map;

public class App {

    @Autowired
    private Client client;
    private EventLogger defaultLogger;
    private Map<EventType, EventLogger> loggers;

    public App(Client client, EventLogger eventLogger, Map<EventType, EventLogger> loggers) {
        super();
        this.client = client;
        this.defaultLogger = eventLogger;
        this.loggers = loggers;
    }

    public static void main(String[] args) {

        ConfigurableApplicationContext ctx = new ClassPathXmlApplicationContext("spring.xml");
        App app = (App) ctx.getBean("app");

        Event event = ctx.getBean(Event.class);
        app.logEvent(EventType.ERROR,event, "Some event for 1");

        event = ctx.getBean(Event.class);
        app.logEvent(null, event, "Some event for 2");

        event = ctx.getBean(Event.class);
        app.logEvent(EventType.INFO, event, "Some event for 3");

        ctx.close();
    }

    private void logEvent(EventType type, Event event, String msg) {

        String message = msg.replaceAll(client.getId(), client.getFullName());
        event.setMsg(message);

        EventLogger logger = loggers.get(type);
        if (logger == null) {
            logger = defaultLogger;
        }

        logger.logEvent(event);
    }
}
