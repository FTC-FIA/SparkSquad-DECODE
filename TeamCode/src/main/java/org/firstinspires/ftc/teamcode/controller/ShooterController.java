package org.firstinspires.ftc.teamcode.controller;

import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.Gamepad;
import com.qualcomm.robotcore.hardware.PIDFCoefficients;
import com.qualcomm.robotcore.hardware.Servo;

import org.firstinspires.ftc.robotcore.external.Telemetry;
import org.firstinspires.ftc.teamcode.Constants;
import org.firstinspires.ftc.teamcode.component.mechanism.Shooter;
import org.firstinspires.ftc.teamcode.component.mechanism.Kicker;
import org.firstinspires.ftc.teamcode.component.sensor.Odometer;
import org.firstinspires.ftc.teamcode.opmode.RobotBaseOpMode;

import javax.sql.ConnectionEventListener;

public class ShooterController {

    private static final double DEFAULT_FORWARD_VELOCITY = 600;
    private static final double DEFAULT_REVERSE_VELOCITY = -100;
    private static final double MAX_VELOCITY = 1600;
    private static final double VELOCITY_INCREMENT = 20.0;

    private static final double kP = 100.0;
    private static final double kI = 38.0;
    private static final double kD = 25.0;

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
        this.shooter.setPIDF(kP, kI, kD);
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

        requestedVelocity = isRunning ? requestedVelocity : 0.0;
        shooter.setVelocity( requestedVelocity );

        double actualVelocity = shooter.getShooterVelocity();
        if ( Math.abs(actualVelocity - requestedVelocity) <= VELOCITY_INCREMENT ){
            shooterLed.setPosition(Constants.LED_GREEN);
        } else {
            shooterLed.setPosition(Constants.LED_ORANGE);
        }

        // TODO: REMOVE AFTER VERIFICATION
        PIDFCoefficients pidf = shooter.getPIDF();
        telemetry.addData("Shooter P", pidf.p);
        telemetry.addData("Shooter I", pidf.i);
        telemetry.addData("Shooter D", pidf.d);

        telemetry.addData("Shooter LED", shooterLed.getPosition());
        telemetry.addData("Shooter Velocity (req)", requestedVelocity);
        telemetry.addData("Shooter Velocity (actual)", String.valueOf(shooter.getShooterVelocity()));
    }

}
