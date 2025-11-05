package org.firstinspires.ftc.teamcode.task;

import org.firstinspires.ftc.teamcode.component.mechanism.Kicker;
import org.firstinspires.ftc.teamcode.opmode.RobotBaseOpMode;

public class StopKicker implements Task {

    private final Kicker kicker;

    public StopKicker(RobotBaseOpMode robot) {
        this.kicker = robot.getKicker();
    }
    
    public boolean execute() {
        kicker.stop();
        return false;
    }
}
