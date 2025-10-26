package org.firstinspires.ftc.teamcode.util;

import java.util.Locale;
import java.util.logging.ConsoleHandler;
import java.util.logging.Level;
import java.util.logging.Logger;

public class SparkLogger {
    private static SparkLogger instance = null;
    private static final String LOGGER_NAME = "SparkSquadLogger";

    private static Logger javaLogger = Logger.getLogger(LOGGER_NAME);
    private static Locale locale = Locale.US
;
    private SparkLogger() {
        ConsoleHandler consoleHandler = new ConsoleHandler();
        javaLogger.addHandler(consoleHandler);
        javaLogger.setLevel(Level.ALL);
    }

    public static SparkLogger getLogger() {
        if (instance == null) {
            instance = new SparkLogger();
        }
        return instance;
    }

    public void log (String msg) {
        javaLogger.info(msg);
    }
}
