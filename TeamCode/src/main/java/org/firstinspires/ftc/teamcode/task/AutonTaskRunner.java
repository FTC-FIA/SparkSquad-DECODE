package org.firstinspires.ftc.teamcode.task;

import org.firstinspires.ftc.teamcode.util.SparkLogger;

public class AutonTaskRunner {

    private final SparkLogger logger = SparkLogger.getLogger();
    private final Task[] taskSequence;
    private int currentTaskIndex = 0;

    public AutonTaskRunner(Task[] tasks) {
        taskSequence = tasks;
    }

    public boolean execute() {
        if (currentTaskIndex >= taskSequence.length) {
            return false;
        }

        boolean taskResult = taskSequence[currentTaskIndex].execute();
        if (!taskResult) {
            logger.log("Finished task " + currentTaskIndex);
            currentTaskIndex++; // note: will run one more time when done
        }
        return true;
    }
}
