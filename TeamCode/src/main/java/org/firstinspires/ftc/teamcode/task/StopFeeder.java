package org.firstinspires.ftc.teamcode.task;

import org.firstinspires.ftc.teamcode.component.mechanism.Feeder;
import org.firstinspires.ftc.teamcode.opmode.RobotBaseOpMode;

public class StopFeeder implements Task {


    private final Feeder feeder;

    public StopFeeder(RobotBaseOpMode robot) {
        this.feeder = robot.getFeeder();
    }

    public boolean execute() {
        feeder.stop();
        return false;
    }
}
