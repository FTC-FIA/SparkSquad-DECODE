package org.firstinspires.ftc.teamcode.task;

import org.firstinspires.ftc.teamcode.component.mechanism.Shooter;
import org.firstinspires.ftc.teamcode.controller.ShooterController;
import org.firstinspires.ftc.teamcode.opmode.RobotBaseOpMode;

public class SpinUpAndShoot implements Task {

    private final static double DEFAULT_SHOOTER_VELOCITY = 750.0;
    private double shooterVelocity;
    private final ShooterController shooterController;
    private boolean shotFired = false;

    public SpinUpAndShoot(RobotBaseOpMode robot, double shooterVelocity) {
        this.shooterController = robot.getShooterController();
        this.shooterVelocity = shooterVelocity;
    }

    public SpinUpAndShoot(RobotBaseOpMode robot) {
        this(robot, DEFAULT_SHOOTER_VELOCITY);
    }

    public boolean execute() {
        boolean result = shooterController.spinUpAndShoot(shooterVelocity);
        if (!result) {
            if (!shotFired) {
                shooterController.idle();
                shotFired = true;
            }
        }
        return (!shotFired); // once shot has been fired we are done
    }
}
