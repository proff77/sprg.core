package com.galiev.sprg.core;

import com.galiev.sprg.core.beans.Client;
import com.galiev.sprg.core.beans.Event;
import com.galiev.sprg.core.loggers.CacheFileEventLogger;
import com.galiev.sprg.core.loggers.ConsoleEventLogger;
import com.galiev.sprg.core.loggers.EventLogger;
import com.galiev.sprg.core.loggers.EventType;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class App {

    private Client client;
    private EventLogger eventLogger;
    private EventType eventType;

    public App(Client client, EventLogger eventLogger) {
        this.client = client;
        this.eventLogger = eventLogger;
    }

    public static void main(String[] args) {

        ConfigurableApplicationContext ctx = new ClassPathXmlApplicationContext("spring.xml");
        App app = (App) ctx.getBean("app");

        Event event = ctx.getBean(Event.class);
        app.logEvent(event, "Some event for 1");

        event = ctx.getBean(Event.class);
        app.logEvent(event, "Some event for 2");

        ctx.close();
    }

    private void logEvent(Event event, String msg, EventType eventType) {
        String message = msg.replaceAll(client.getId(), client.getFullName());
        event.setMsg(message);
        if (eventType == null) {
            new CacheFileEventLogger()
        }
        eventLogger.logEvent(event);
    }
}
