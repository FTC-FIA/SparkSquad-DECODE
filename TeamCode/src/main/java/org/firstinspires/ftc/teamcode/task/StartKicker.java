package org.firstinspires.ftc.teamcode.task;

import org.firstinspires.ftc.teamcode.component.mechanism.Intake;
import org.firstinspires.ftc.teamcode.component.mechanism.Kicker;
import org.firstinspires.ftc.teamcode.opmode.RobotBaseOpMode;

public class StartKicker implements Task {

    private final Kicker kicker;

    public StartKicker(RobotBaseOpMode robot) {
        this.kicker = robot.getKicker();
    }

    public boolean execute() {
        kicker.forward();
        return false;
    }
}
