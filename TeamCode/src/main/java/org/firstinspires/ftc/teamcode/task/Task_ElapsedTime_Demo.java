package org.firstinspires.ftc.teamcode.task;

import com.qualcomm.robotcore.util.ElapsedTime;

public class Task_ElapsedTime_Demo implements Task {

    private final double DEFAULT_DURATION = 1.0f; // in seconds

    private double duration;
    private boolean isInitialized = false;
    private ElapsedTime elapsedTime = new ElapsedTime();

    public Task_ElapsedTime_Demo(double dur) {
        duration = dur;
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

        // are we done?
        if (elapsedTime.seconds() > duration) {
            return true;
        } else {
            return false;
        }
        // simplified: return (elapsedTime.seconds() > duration);
    }
}
