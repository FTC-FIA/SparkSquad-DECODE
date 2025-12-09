package org.firstinspires.ftc.teamcode.controller;

import org.firstinspires.ftc.robotcore.external.Telemetry;
import org.firstinspires.ftc.robotcore.external.navigation.AngleUnit;
import org.firstinspires.ftc.robotcore.external.navigation.DistanceUnit;
import org.firstinspires.ftc.robotcore.external.navigation.Pose2D;
import org.firstinspires.ftc.teamcode.component.sensor.Limelight;
import org.firstinspires.ftc.teamcode.component.sensor.Odometer;
import org.firstinspires.ftc.teamcode.opmode.RobotBaseOpMode;

public class LimelightOdometerController {

    private Odometer odometer;
    private Limelight limelight;
    private Telemetry telemetry;

    public LimelightOdometerController(RobotBaseOpMode robot) {
        this.odometer = robot.getOdometer();
        this.limelight = robot.getLimelight();
        this.telemetry = robot.getTelemetry();
    }

    // handles limelight input, not human input, but does run on every cycle
    public void handleInput() {
        odometer.update();
        double limelightX = limelight.getRobotX();
        double limelightY = limelight.getRobotY();
        double limelightH = limelight.getRobotH();

        // check if results are invalid
        if (Double.isNaN(limelightX) || Double.isNaN(limelightY) || Double.isNaN(limelightH)) {
            return;
        }

        // if not, update odometer
        Pose2D limelightPose2D = new Pose2D(DistanceUnit.INCH, limelightX, limelightY, AngleUnit.DEGREES, limelightH);
        odometer.setPosition(limelightPose2D);

        telemetry.addData("LL X", limelightX);
        telemetry.addData("LL Y", limelightY);
        telemetry.addData("LL H", limelightH);
        //TODO: smooth limelight values?
        //TODO: only update if bot is not moving?
    }
}
