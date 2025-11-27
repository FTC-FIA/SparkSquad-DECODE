package org.firstinspires.ftc.teamcode.util;

import org.firstinspires.ftc.robotcore.external.Telemetry;
import org.firstinspires.ftc.teamcode.opmode.teleop.TeleOp_AutoVelocity;

import java.util.Locale;
import java.util.logging.ConsoleHandler;
import java.util.logging.Level;
import java.util.logging.Logger;

public class SparkLogger {
    private static SparkLogger instance = null;
    private static final String LOGGER_NAME = "SparkSquadLogger";
    private static Telemetry telemetry;

    private static Logger javaLogger = Logger.getLogger(LOGGER_NAME);
    private static Locale locale = Locale.US
;
    private SparkLogger() {
        //SparkLogger.telemetry = telemetry;
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

//    public Telemetry.Item addData(String key, String value, Object... args) {
//        String message = key + ": " + value;
//        log(message);
//        return telemetry.addData(key, message, args);
//    }
//
//    public boolean update() {
//        return telemetry.update();
//    }
}
