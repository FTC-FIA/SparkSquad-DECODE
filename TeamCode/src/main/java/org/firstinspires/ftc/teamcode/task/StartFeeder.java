package org.firstinspires.ftc.teamcode.task;

import org.firstinspires.ftc.teamcode.R;
import org.firstinspires.ftc.teamcode.component.mechanism.Feeder;
import org.firstinspires.ftc.teamcode.component.mechanism.Intake;
import org.firstinspires.ftc.teamcode.opmode.RobotBaseOpMode;

public class StartFeeder implements Task {


    private final Feeder feeder;
    private static final double DEFAULT_FEEDER_POWER = 0.5;

    public StartFeeder(RobotBaseOpMode robot, double feederPower) {
        this.feeder = robot.getFeeder();
        //this.feeder.setPower(feederPower);
    }

    public StartFeeder(RobotBaseOpMode robot) {
        this(robot, DEFAULT_FEEDER_POWER);
    }


    public boolean execute() {
        feeder.start();
        return false;
    }
}
