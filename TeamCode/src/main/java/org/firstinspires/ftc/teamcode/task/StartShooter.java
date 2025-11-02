package org.firstinspires.ftc.teamcode.task;

import org.firstinspires.ftc.robotcore.external.Telemetry;
import org.firstinspires.ftc.teamcode.component.mechanism.Shooter;
import org.firstinspires.ftc.teamcode.opmode.RobotBaseOpMode;

public class StartShooter implements Task {

    private final static double DEFAULT_SHOOTER_SPEED = 1.0;
    private double shooterSpeed;
    private final Shooter shooter;

    public StartShooter(RobotBaseOpMode robot, double shooterSpeed) {
        this.shooter = robot.getShooter();
        this.shooterSpeed = shooterSpeed;
    }

    public StartShooter(RobotBaseOpMode robot) {
        this(robot, DEFAULT_SHOOTER_SPEED);
    }
    public boolean execute() {
        shooter.setPower(shooterSpeed);
        return false; // we're done here!
    }
}
