package org.firstinspires.ftc.teamcode.controller;

import com.qualcomm.robotcore.hardware.Gamepad;
import com.qualcomm.robotcore.hardware.Servo;

import org.firstinspires.ftc.robotcore.external.Telemetry;
import org.firstinspires.ftc.teamcode.Constants;
import org.firstinspires.ftc.teamcode.component.mechanism.Shooter;
import org.firstinspires.ftc.teamcode.component.mechanism.Kicker;
import org.firstinspires.ftc.teamcode.opmode.RobotBaseOpMode;

import javax.sql.ConnectionEventListener;

public class ShooterController {

    private static final double DEFAULT_FORWARD_VELOCITY = 600;
    private static final double DEFAULT_REVERSE_VELOCITY = -100;
    private static final double MAX_VELOCITY = 1600;
    private static final double VELOCITY_INCREMENT = 20.0;

    private final Shooter shooter;
    private final Gamepad operatorGamepad;
    private final Telemetry telemetry;
    private final Servo shooterLed;

    private boolean isRunning = true;
    private double forwardVelocity = DEFAULT_FORWARD_VELOCITY;
    private double reverseVelocity = DEFAULT_REVERSE_VELOCITY;
    private double requestedVelocity = forwardVelocity;

    public ShooterController(RobotBaseOpMode robot) {
        this.shooter = robot.getShooter();
        this.operatorGamepad = robot.getOperatorGamepad();
        this.telemetry = robot.getTelemetry();
        this.shooterLed = robot.getShooterLed();
    }

    public void handleInput() {

        if (operatorGamepad.bWasPressed()) {
            forwardVelocity += VELOCITY_INCREMENT;
            requestedVelocity = forwardVelocity;
        } else if (operatorGamepad.xWasPressed()) {
            forwardVelocity -= VELOCITY_INCREMENT;
            requestedVelocity = forwardVelocity;
        }

        if (operatorGamepad.yWasPressed()) {
            isRunning = !isRunning;
            requestedVelocity = forwardVelocity;
        } else if (operatorGamepad.aWasPressed()) {
            isRunning = !isRunning;
            requestedVelocity = reverseVelocity;
        }

//        double speedChangeFactor = operatorGamepad.right_stick_y * 10;
//        reverseVelocity += Math.min(Math.max(speedChangeFactor / 2, 0.0), MAX_VELOCITY);
//        forwardVelocity += Math.min(Math.max(speedChangeFactor, 0.0), MAX_VELOCITY);

        requestedVelocity = isRunning ? requestedVelocity : 0.0;
        shooter.setVelocity( requestedVelocity );

        double actualVelocity = shooter.getShooterVelocity();
        if ( Math.abs(actualVelocity - requestedVelocity) <= VELOCITY_INCREMENT ){
            shooterLed.setPosition(Constants.LED_GREEN);
        } else {
            shooterLed.setPosition(Constants.LED_ORANGE);
        }

        telemetry.addData("Shooter LED position", shooterLed.getPosition());
        telemetry.addData("Shooter Velocity (req)", requestedVelocity);
        telemetry.addData("Shooter Velocity (actual)", String.valueOf(shooter.getShooterVelocity()));
        telemetry.addData("Shooter Power", shooter.getPower());
    }

}
