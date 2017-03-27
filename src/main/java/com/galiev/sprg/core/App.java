package com.galiev.sprg.core;

import com.galiev.sprg.core.beans.Client;
import com.galiev.sprg.core.loggers.ConsoleEventLogger;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class App {

    private Client client;
    private ConsoleEventLogger eventLogger;

    public App(Client client, ConsoleEventLogger eventLogger) {
        this.client = client;
        this.eventLogger = eventLogger;
    }

    public static void main(String[] args) {
//        App app = new App();
//        app.client = new Client("1", "Rtyu Kjll");
//        app.eventLogger = new ConsoleEventLogger();

//        ApplicationContext ctx = new ClassPathXmlApplicationContext("spring.xml");
//        App app = (App) ctx.getBean("app");
//
//        app.logEvent("Log event 1");
//        app.logEvent("Log event 2");

        ConfigurableApplicationContext ctx = new ClassPathXmlApplicationContext("spring.xml");
        ctx.close();
    }

//    private void logEvent(String msg) {
//        String message = msg.replaceAll(client.getId(), client.getFullName());
//        eventLogger.logEvent(message);
//    }
}
