package com.galiev.sprg.core.loggers;

import com.galiev.sprg.core.beans.Event;
import org.apache.commons.io.FileUtils;
import org.springframework.stereotype.Component;

import java.io.File;
import java.io.IOException;

@Component
public class FileEventLogger implements EventLogger {

    private String fileName;
    private File file;

    public FileEventLogger(String fileName) {
        this.fileName = fileName;
    }

    public void logEvent(Event logEvent) {
        try {
            FileUtils.writeStringToFile(file, logEvent.toString(), true);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void init() throws IOException {
        this.file = new File(fileName);
        if (file.exists() && !file.canWrite()) {
            throw new IllegalArgumentException("Can't write to file" + fileName);
        }
        else if (!file.exists()) {
            try {
                file.createNewFile();
            } catch (Exception e) {
                throw new IllegalArgumentException("Can't create file", e);
            }
        }
    }
}
