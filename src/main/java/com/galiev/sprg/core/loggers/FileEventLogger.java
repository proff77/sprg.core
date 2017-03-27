package com.galiev.sprg.core.loggers;

import org.apache.commons.io.FileUtils;

import java.io.File;
import java.io.IOException;

public class FileEventLogger implements EventLogger {

    private String fileName;

    public void logEvent(Event logEvent) {
        try {
            FileUtils.writeStringToFile(new File(fileName), logEvent.getMsg());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
