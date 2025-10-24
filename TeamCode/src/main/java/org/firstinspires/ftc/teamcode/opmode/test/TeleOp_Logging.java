package org.firstinspires.ftc.teamcode.opmode.test;

import com.qualcomm.robotcore.eventloop.opmode.Disabled;
import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.util.ElapsedTime;

import org.firstinspires.ftc.teamcode.component.util.SparkLogger;

import java.io.Console;
import java.util.Locale;
import java.util.logging.ConsoleHandler;
import java.util.logging.Level;
import java.util.logging.Logger;

@TeleOp(name="TeleOp_Logging", group="Test")
@Disabled
public class TeleOp_Logging extends OpMode {

    private final SparkLogger logger = SparkLogger.getLogger();
    private final ElapsedTime runtime = new ElapsedTime();

    @Override
    public void init() {}

    @Override
    public void start() {
        runtime.reset();
    }

    @Override
    public void loop() {
        logger.log("Elapsed Time:" + runtime.milliseconds());
    }
}
