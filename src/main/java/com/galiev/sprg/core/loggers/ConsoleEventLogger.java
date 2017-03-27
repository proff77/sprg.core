package com.galiev.sprg.core.loggers;

public class ConsoleEventLogger implements EventLogger {

    public void logEvent(Event logEvent) {
        System.out.println(logEvent);
    }
}
