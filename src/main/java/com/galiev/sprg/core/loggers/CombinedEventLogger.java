package com.galiev.sprg.core.loggers;

import com.galiev.sprg.core.beans.Event;
import org.springframework.stereotype.Component;

import java.util.Collection;

@Component
public class CombinedEventLogger implements EventLogger {

    private final Collection<EventLogger> loggers;

    public void logEvent(Event event) {
        for (EventLogger eventLogger : loggers) {
            eventLogger.logEvent(event);
        }
    }

    public CombinedEventLogger(Collection<EventLogger> loggers) {
        super();
        this.loggers = loggers;
    }
}
