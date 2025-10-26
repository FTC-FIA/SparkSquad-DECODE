package org.firstinspires.ftc.teamcode.task;

import com.qualcomm.robotcore.robot.Robot;
import com.qualcomm.robotcore.util.ElapsedTime;

import org.firstinspires.ftc.robotcore.external.Telemetry;
import org.firstinspires.ftc.teamcode.opmode.RobotBaseOpMode;

public class Wait implements Task {

    private final double duration;
    private final ElapsedTime elapsedTime = new ElapsedTime();
    private final Telemetry telemetry;
    private boolean isInitialized = false;

    public Wait(
            RobotBaseOpMode robot,
            double durInSeconds) {
        duration = durInSeconds;
        this.telemetry = robot.getTelemetry();
    }

    /**
     * runs task for 'duration' seconds
     * @return true if still running, false when time is reached
     */
    public boolean execute() {

        if (!isInitialized) { // called for the first time
            elapsedTime.reset();
            isInitialized = true;
        }
        telemetry.addData("Elapsed time", elapsedTime.seconds());

        // are we still going? true=yes, false=no
        return (elapsedTime.seconds() < duration);
    }
}
