package com.galiev.sprg.core.loggers;

import com.galiev.sprg.core.beans.Event;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class CacheFileEventLogger extends FileEventLogger {

    private int cashSize;
    private List<Event> cache;

    public CacheFileEventLogger(String fileName, int cashSize) {
        super(fileName);
        this.cashSize = cashSize;
        this.cache = new ArrayList<Event>(cashSize);
    }

    private void writeEventsFromCache() {
        for (Event event : cache) {
            super.logEvent(event);
        }
    }

    public void logEvent(Event event) {
        cache.add(event);

        if (cache.size() == cashSize) {
            writeEventsFromCache();
            cache.clear();
        }
    }

    public void destroy() {
        if (!cache.isEmpty()) {
            writeEventsFromCache();
        }
    }
}
