package org.example.Configuration;

import org.example.App;

import java.io.File;
import java.io.IOException;
import java.util.logging.FileHandler;
import java.util.logging.Logger;
import java.util.logging.SimpleFormatter;

public class Log {

    public static Logger logger = null;
    public static FileHandler fh;
    String fileName = "log_file.txt";


    public Log() throws IOException {

        if(logger == null) {
            File f = new File(this.fileName);

            if (!f.exists()) {
                f.createNewFile();
            }

            fh = new FileHandler(fileName, true);
            logger = Logger.getLogger(String.valueOf(App.class));
            logger.addHandler(fh);
            SimpleFormatter sf = new SimpleFormatter();
            fh.setFormatter(sf);
        }
    }

    public static Logger getInstance() throws IOException {
        if(logger == null)
        {
            new Log();
        }
        return logger;
    }
}