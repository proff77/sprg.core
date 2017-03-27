package com.galiev.sprg.core.loggers;

import java.util.List;

public class CacheFileEventLogger extends FileEventLogger {

    private int cashSize;
    private List<Event> cache;

    public CacheFileEventLogger(int cashSize) {
        this.cashSize = cashSize;
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
