package com.galiev.sprg.core;

import com.galiev.sprg.core.beans.Client;
import com.galiev.sprg.core.beans.Event;
import com.galiev.sprg.core.loggers.EventLogger;
import com.galiev.sprg.core.beans.EventType;
import com.galiev.sprg.core.spring.AppConfig;
import com.galiev.sprg.core.spring.LoggerConfig;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.Map;

@Service
public class App {

    @Autowired
    private Client client;

    @Value("#{ T(com.galiev.sprg.core.beans.Event).isDay() ? fileEventLogger : consoleEventLogger }")
    private EventLogger defaultLogger;

    @Resource(name = "loggerMap")
    private Map<EventType, EventLogger> loggers;

    public App() {
    }

    public App(Client client, EventLogger eventLogger, Map<EventType, EventLogger> loggers) {
        super();
        this.client = client;
        this.defaultLogger = eventLogger;
        this.loggers = loggers;
    }

    public static void main(String[] args) {

        AnnotationConfigApplicationContext ctx = new AnnotationConfigApplicationContext();
        ctx.register(AppConfig.class, LoggerConfig.class);
        ctx.scan("com.galiev.sprg.core");
        ctx.refresh();

        App app = (App) ctx.getBean("app");

        Client client = ctx.getBean(Client.class);
        System.out.println("Client says: " + client.getGreeting());

        Event event = ctx.getBean(Event.class);
        app.logEvent(EventType.ERROR, event, "Some event for 1");

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
