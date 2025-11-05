package org.firstinspires.ftc.teamcode.task;

import org.firstinspires.ftc.robotcore.external.Telemetry;
import org.firstinspires.ftc.teamcode.component.mechanism.Shooter;
import org.firstinspires.ftc.teamcode.opmode.RobotBaseOpMode;

public class StartShooterWithVelocity implements Task {

    private double velocity;
    private final Shooter shooter;

    public StartShooterWithVelocity(RobotBaseOpMode robot, double velocity) {
        this.shooter = robot.getShooter();
        this.velocity = velocity;
    }

    public boolean execute() {
        shooter.setVelocity(velocity);
        return false; // we're done here!
    }
}
