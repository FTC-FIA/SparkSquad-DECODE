package org.firstinspires.ftc.teamcode.controller;

import com.qualcomm.robotcore.hardware.Gamepad;
import com.qualcomm.robotcore.hardware.Servo;

import org.firstinspires.ftc.robotcore.external.Telemetry;
import org.firstinspires.ftc.robotcore.external.navigation.AngleUnit;
import org.firstinspires.ftc.robotcore.external.navigation.DistanceUnit;
import org.firstinspires.ftc.robotcore.external.navigation.Pose2D;
import org.firstinspires.ftc.teamcode.Constants;
import org.firstinspires.ftc.teamcode.component.drive.MecanumDrive;
import org.firstinspires.ftc.teamcode.component.mechanism.Feeder;
import org.firstinspires.ftc.teamcode.component.mechanism.Kicker;
import org.firstinspires.ftc.teamcode.component.mechanism.Shooter;
import org.firstinspires.ftc.teamcode.component.sensor.Odometer;
import org.firstinspires.ftc.teamcode.opmode.RobotBaseOpMode;
import org.firstinspires.ftc.teamcode.util.Alliance;
import org.firstinspires.ftc.teamcode.util.ShooterUtils;

public class AssistedShooterController {

    protected RobotBaseOpMode robot;
    protected Gamepad operatorGamepad;
    protected Gamepad driverGamepad;
    protected Shooter shooter;
    protected Odometer odometer;
    protected Feeder feeder;
    protected Kicker kicker;
    protected Servo shooterLed;
    protected Servo aimerLed;
    protected MecanumDrive mecanumDrive;
    protected Telemetry telemetry;
    protected Pose2D targetPose;

    protected Alliance color;

    private double velocityAdjustment = -40.0;
    private double aimerAdjustment = 2.0;

    public AssistedShooterController(RobotBaseOpMode robot, Alliance color) {
        this.robot = robot;
        this.shooter = robot.getShooter();
        this.odometer = robot.getOdometer();
        this.feeder = robot.getFeeder();
        this.kicker = robot.getKicker();
        this.telemetry = robot.getTelemetry();
        this.shooterLed = robot.getShooterLed();
        this.aimerLed = robot.getAimerLed();
        this.mecanumDrive = robot.getMecanumDrive();
        this.operatorGamepad = robot.getOperatorGamepad();
        this.driverGamepad = robot.getDriverGamepad();
        this.color = color;
        this.targetPose = Constants.TARGET.forAlliance(color);
    }

    public void handleInput() {
        // always set shooter velocity based on distance
        odometer.update();
        double currentX = odometer.getX(DistanceUnit.INCH);
        double currentY = odometer.getY(DistanceUnit.INCH);

        Pose2D target = Constants.TARGET.forAlliance(color);
        double targetX = target.getX(DistanceUnit.INCH);
        double targetY = target.getY(DistanceUnit.INCH);

        double distance = ShooterUtils.calculateDistance(currentX, currentY, targetX, targetY);
        double recommendedVelocity = ShooterUtils.distance2Velocity(distance);
        double recommendedHeading = ShooterUtils.headingTowards(currentX, currentY, targetX, targetY);

        if (operatorGamepad.bWasPressed()) {
            velocityAdjustment += Constants.SHOOTER_VELOCITY_INCREMENT;
        } else if (operatorGamepad.xWasPressed()) {
            velocityAdjustment -= Constants.SHOOTER_VELOCITY_INCREMENT;
        }

        if (driverGamepad.dpadRightWasPressed()) {
            aimerAdjustment += 1.0;
        } else if (driverGamepad.dpadLeftWasPressed()) {
            aimerAdjustment -= 1.0;
        }


        double targetVelocity = recommendedVelocity + velocityAdjustment;
        // display velocity accuracy
        double actualVelocity = shooter.getShooterVelocity();
        if ( Math.abs(actualVelocity - targetVelocity) <= Constants.SHOOTER_VELOCITY_INCREMENT ){
            shooterLed.setPosition(Constants.LED_GREEN);
        } else {
            shooterLed.setPosition(Constants.LED_RED);
        }

        double targetHeading = recommendedHeading + aimerAdjustment;
        // display heading accuracy
        double robotHeading = odometer.getHeading(AngleUnit.DEGREES);
        if (Math.abs(targetHeading - robotHeading) <= Constants.AIM_TOLERANCE) {
            aimerLed.setPosition(Constants.LED_GREEN);
        } else {
            aimerLed.setPosition(Constants.LED_RED);
        }


        // update velocity based on distance
        shooter.setVelocity(targetVelocity);

        // auto aim
        double rotateSpeed = 0.0;
        double headingError = targetHeading - robotHeading;
        if (driverGamepad.y) {
            if (headingError > Constants.AIM_TOLERANCE) {
                rotateSpeed = Constants.AIMER_ROTATE_SPEED;
            }
            if (headingError < -Constants.AIM_TOLERANCE) {
                rotateSpeed = -Constants.AIMER_ROTATE_SPEED;
            }
        }
        mecanumDrive.drive(0.0, 0.0, rotateSpeed);
        telemetry.addData("Aimer adjustment", aimerAdjustment);
        telemetry.addData("Velocity adjustment", velocityAdjustment);

        telemetry.addData("Robot Heading", robotHeading);
        telemetry.addData("Rec'd heading", recommendedHeading);
        telemetry.addData("Target heading", targetHeading);
        telemetry.addData("** HEADING ERROR", targetHeading - robotHeading);

        telemetry.addData("Distance to Target", distance);

        double currVelocity = shooter.getShooterVelocity();
        telemetry.addData("Rec'd velocity", recommendedVelocity);
        telemetry.addData("Target velocity", targetVelocity);
        telemetry.addData("Current Velocity", currVelocity);
        telemetry.addData("** VELOCITY ERROR", targetVelocity - currVelocity);
    }

}
