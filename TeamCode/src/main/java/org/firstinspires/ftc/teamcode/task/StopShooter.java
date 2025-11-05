package org.firstinspires.ftc.teamcode.task;

import org.firstinspires.ftc.teamcode.component.mechanism.Shooter;
import org.firstinspires.ftc.teamcode.opmode.RobotBaseOpMode;

public class StopShooter implements Task {

    private Shooter shooter;

    public StopShooter(RobotBaseOpMode robot) {
        this.shooter = robot.getShooter();
    }

    public boolean execute() {
        shooter.stop();
        return false; // done
    }
}
