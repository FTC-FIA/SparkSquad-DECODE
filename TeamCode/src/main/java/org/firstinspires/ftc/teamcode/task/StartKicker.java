package org.firstinspires.ftc.teamcode.task;

import org.firstinspires.ftc.teamcode.component.mechanism.Intake;
import org.firstinspires.ftc.teamcode.component.mechanism.Kicker;
import org.firstinspires.ftc.teamcode.opmode.RobotBaseOpMode;

public class StartKicker implements Task {

    private final Kicker kicker;
    private double kickerSpeed;

    public StartKicker(RobotBaseOpMode robot, double kickerSpeed) {
        this.kicker = robot.getKicker();
        this.kickerSpeed = kickerSpeed;
    }

    public boolean execute() {
        kicker.setPower(kickerSpeed);
        kicker.forward();
        return false;
    }
}
