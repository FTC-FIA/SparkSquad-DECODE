package org.firstinspires.ftc.teamcode.controller;

import com.qualcomm.robotcore.hardware.Gamepad;

import org.firstinspires.ftc.robotcore.external.Telemetry;
import org.firstinspires.ftc.teamcode.component.mechanism.Shooter;
import org.firstinspires.ftc.teamcode.component.mechanism.Kicker;
import org.firstinspires.ftc.teamcode.opmode.RobotBaseOpMode;

public class ShooterController {

    private static final double DEFAULT_VELOCITY = 650;
    private static final double MAX_VELOCITY = 1600;

    private final Shooter shooter;
    private final Gamepad operatorGamepad;
    private final Telemetry telemetry;

    private boolean isRunning = false;
    private double shooterVelocity = DEFAULT_VELOCITY;

    public ShooterController(RobotBaseOpMode robot) {
        this.shooter = robot.getShooter();
        this.operatorGamepad = robot.getOperatorGamepad();
        this.telemetry = robot.getTelemetry();
    }

    public void handleInput() {
        if (operatorGamepad.yWasPressed()) {
            isRunning = true;
        } else if (operatorGamepad.aWasPressed()) {
            isRunning = false;
        } else if (operatorGamepad.xWasPressed()) {
            shooterVelocity = shooterVelocity * 0.9;
        } else if (operatorGamepad.bWasPressed()) {
            shooterVelocity = Math.min(shooterVelocity * 1.1, MAX_VELOCITY);
        }
        double requestedVelocity = isRunning ? shooterVelocity : 0.0;
        shooter.setVelocity(requestedVelocity);

        telemetry.addData("Shooter Velocity (req)", requestedVelocity);
        telemetry.addData("Shooter Velocity (actual)", String.valueOf(shooter.getShooterVelocity()));
        telemetry.addData("Shooter Power", shooter.getPower());
    }

}
