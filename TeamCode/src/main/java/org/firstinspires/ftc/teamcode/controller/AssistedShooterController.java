package org.firstinspires.ftc.teamcode.controller;

import org.firstinspires.ftc.robotcore.external.Telemetry;
import org.firstinspires.ftc.robotcore.external.navigation.AngleUnit;
import org.firstinspires.ftc.robotcore.external.navigation.DistanceUnit;
import org.firstinspires.ftc.robotcore.external.navigation.Pose2D;
import org.firstinspires.ftc.teamcode.Constants;
import org.firstinspires.ftc.teamcode.component.mechanism.Feeder;
import org.firstinspires.ftc.teamcode.component.mechanism.Kicker;
import org.firstinspires.ftc.teamcode.component.mechanism.Shooter;
import org.firstinspires.ftc.teamcode.component.sensor.Odometer;
import org.firstinspires.ftc.teamcode.opmode.RobotBaseOpMode;
import org.firstinspires.ftc.teamcode.util.AllianceColor;
import org.firstinspires.ftc.teamcode.util.ShooterUtils;

public class AssistedShooterController {

    protected Shooter shooter;
    protected Odometer odometer;
    protected Feeder feeder;
    protected Kicker kicker;
    protected Telemetry telemetry;

    protected AllianceColor color;

    public AssistedShooterController(RobotBaseOpMode robot, AllianceColor color) {
        this.shooter = robot.getShooter();
        this.odometer = robot.getOdometer();
        this.feeder = robot.getFeeder();
        this.kicker = robot.getKicker();
        this.telemetry = robot.getTelemetry();

        this.color = color;
    }

    public void handleInput() {
        // always set shooter velocity based on distance
        odometer.update();
        double currentX = odometer.getX(DistanceUnit.INCH);
        double currentY = odometer.getY(DistanceUnit.INCH);
        Pose2D target = Constants.TARGET.forColor(color);
        double targetX = target.getX(DistanceUnit.INCH);
        double targetY = target.getY(DistanceUnit.INCH);
        double distance = ShooterUtils.calculateDistance(currentX, currentY, targetX, targetY);
        double recommendedVelocity = ShooterUtils.distance2Velocity(distance);
        double targetHeading = ShooterUtils.headingTowards(currentX, currentY, targetX, targetY);

        shooter.setVelocity(recommendedVelocity);
        telemetry.addData("X", currentX);
        telemetry.addData("Y", currentY);

        double robotHeading = odometer.getHeading(AngleUnit.DEGREES);
        telemetry.addData("Robot Heading", robotHeading);
        telemetry.addData("Target heading", targetHeading);
        telemetry.addData("** HEADING ERROR", targetHeading - robotHeading);

        telemetry.addData("Distance to Target", distance);

        double currVelocity = shooter.getShooterVelocity();
        telemetry.addData("Target velocity", recommendedVelocity);
        telemetry.addData("Current Velocity", currVelocity);
        telemetry.addData("** VELOCITY ERROR", recommendedVelocity - currVelocity);
    }
}
