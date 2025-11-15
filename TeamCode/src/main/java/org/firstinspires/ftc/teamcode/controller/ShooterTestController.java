package org.firstinspires.ftc.teamcode.controller;

import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorEx;
import com.qualcomm.robotcore.hardware.Gamepad;
import com.qualcomm.robotcore.hardware.PIDCoefficients;
import com.qualcomm.robotcore.hardware.PIDFCoefficients;
import com.qualcomm.robotcore.hardware.Servo;

import org.firstinspires.ftc.robotcore.external.Telemetry;
import org.firstinspires.ftc.teamcode.Constants;
import org.firstinspires.ftc.teamcode.component.mechanism.Shooter;
import org.firstinspires.ftc.teamcode.opmode.RobotBaseOpMode;

public class ShooterTestController {

    private static final double DEFAULT_FORWARD_VELOCITY = 600;
    private static final double MAX_VELOCITY = 1600;
    private static final double VELOCITY_INCREMENT = 20.0;

    private final Shooter shooter;
    private final Gamepad operatorGamepad;
    private final Telemetry telemetry;
    private final Servo shooterLed;
    private final DcMotorEx shooterMotor;

    private boolean isRunning = true;
    private double forwardVelocity = DEFAULT_FORWARD_VELOCITY;
    private double requestedVelocity = forwardVelocity;

    private double kP = 10.0;
    private double kI = 0.0;
    private double kD = 0.0;

    public ShooterTestController(RobotBaseOpMode robot) {
        this.shooter = robot.getShooter();
        this.operatorGamepad = robot.getOperatorGamepad();
        this.telemetry = robot.getTelemetry();
        this.shooterLed = robot.getShooterLed();
        this.shooterMotor = robot.getShooterMotor();
    }

    private void updatePID() {
        PIDFCoefficients newCoefficients = new PIDFCoefficients(kP, kI, kD, 0.0);
        shooterMotor.setPIDFCoefficients(DcMotor.RunMode.RUN_USING_ENCODER, newCoefficients);
    }

    public void handleInput() {

        if (operatorGamepad.bWasPressed()) {
            kP += 1.0;
            updatePID();
        }
        if (operatorGamepad.xWasPressed()) {
            kP = Math.max(kP - 1.0, 0.0);
            updatePID();
        }
        if (operatorGamepad.dpadUpWasPressed()) {
            kD += 0.1;
            updatePID();
        }
        if (operatorGamepad.dpadDownWasPressed()) {
            kD = Math.max(kD - 0.1, 0.0);
            updatePID();
        }
        if (operatorGamepad.dpadRightWasPressed()) {
            kI += 0.1;
            updatePID();
        }
        if (operatorGamepad.dpadLeftWasPressed()) {
            kI = Math.max(kI - 0.1, 0.0);
            updatePID();
        }

        if (operatorGamepad.yWasPressed()) {
            isRunning = !isRunning;
            requestedVelocity = forwardVelocity;
        }

        requestedVelocity = isRunning ? requestedVelocity : 0.0;
        shooter.setVelocity( requestedVelocity );

        double actualVelocity = shooter.getShooterVelocity();
        if ( Math.abs(actualVelocity - requestedVelocity) <= VELOCITY_INCREMENT ){
            shooterLed.setPosition(Constants.LED_GREEN);
        } else {
            shooterLed.setPosition(Constants.LED_ORANGE);
        }
        PIDFCoefficients pidf = shooterMotor.getPIDFCoefficients(DcMotor.RunMode.RUN_USING_ENCODER);
        telemetry.addData("P",pidf.p);
        telemetry.addData("I",pidf.i);
        telemetry.addData("D",pidf.d);
    }

}
