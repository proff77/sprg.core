package com.galiev.sprg.core.loggers;

import com.galiev.sprg.core.beans.Event;
import org.springframework.stereotype.Component;

@Component
public class ConsoleEventLogger implements EventLogger {

    public void logEvent(Event logEvent) {
        System.out.println(logEvent.toString());
    }
}
