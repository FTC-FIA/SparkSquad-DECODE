package org.firstinspires.ftc.teamcode.task;

import com.qualcomm.robotcore.util.ElapsedTime;

import org.firstinspires.ftc.robotcore.external.Telemetry;

public class Task_Wait implements Task {

    private final double DEFAULT_DURATION = 1.0f; // in seconds

    private double duration;
    private boolean isInitialized = false;
    private ElapsedTime elapsedTime = new ElapsedTime();
    private Telemetry telemetry;

    public Task_Wait(double durInSeconds, Telemetry telemetry) {
        duration = durInSeconds;
        this.telemetry = telemetry;
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
