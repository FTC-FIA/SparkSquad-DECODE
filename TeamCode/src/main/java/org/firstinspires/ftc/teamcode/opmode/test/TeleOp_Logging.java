package org.firstinspires.ftc.teamcode.opmode.test;

import com.qualcomm.robotcore.eventloop.opmode.Disabled;
import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.util.ElapsedTime;

import org.firstinspires.ftc.teamcode.util.SparkLogger;

@TeleOp(name="Logging Test", group="Test")
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
