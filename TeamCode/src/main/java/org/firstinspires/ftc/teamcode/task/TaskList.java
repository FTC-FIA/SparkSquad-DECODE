package org.firstinspires.ftc.teamcode.task;

import org.firstinspires.ftc.robotcore.external.Telemetry;
import org.firstinspires.ftc.teamcode.opmode.RobotBaseOpMode;
import org.firstinspires.ftc.teamcode.util.SparkLogger;

public abstract class TaskList implements Task {

    private final SparkLogger logger = SparkLogger.getLogger();
    private final Task[] taskSequence;
    private final Telemetry telemetry;
    private int currentTaskIndex = 0;

    public TaskList(RobotBaseOpMode robot, Task[] tasks) {
        this.taskSequence = tasks;
        this.telemetry = robot.getTelemetry();
    }

    public boolean execute() {

        if (currentTaskIndex >= taskSequence.length) {
            return false;
        }

        boolean taskResult = taskSequence[currentTaskIndex].execute();

        if ( telemetry != null ) {
            telemetry.addData( "Executing task", currentTaskIndex );
        }

        if (!taskResult) {
            logger.log("Finished task " + currentTaskIndex);
            currentTaskIndex++; // note: will run one more time when done
        }
        return true;
    }
}
