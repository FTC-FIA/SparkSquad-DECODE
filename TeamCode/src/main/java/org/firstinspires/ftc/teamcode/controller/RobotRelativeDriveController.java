package org.firstinspires.ftc.teamcode.controller;

import com.qualcomm.robotcore.hardware.Gamepad;

import org.firstinspires.ftc.robotcontroller.external.samples.RobotTeleopMecanumFieldRelativeDrive;
import org.firstinspires.ftc.robotcontroller.internal.FtcOpModeRegister;
import org.firstinspires.ftc.robotcore.external.Telemetry;
import org.firstinspires.ftc.robotcore.external.navigation.AngleUnit;
import org.firstinspires.ftc.robotcore.external.navigation.DistanceUnit;
import org.firstinspires.ftc.robotcore.external.navigation.Pose2D;
import org.firstinspires.ftc.teamcode.component.drive.FieldRelativeDrive;
import org.firstinspires.ftc.teamcode.component.drive.MecanumDrive;
import org.firstinspires.ftc.teamcode.component.sensor.Odometer;
import org.firstinspires.ftc.teamcode.opmode.RobotBaseOpMode;

public class RobotRelativeDriveController {

    protected MecanumDrive drive;
    private final Odometer odometer;
    private final Gamepad driverGamepad;
    private final Telemetry telemetry;

    private static double FAST_FORWARD_SCALE = 0.8;
    private static double FAST_STRAFE_SCALE = 1.0;
    private static double FAST_ROTATE_SCALE = 0.6;

    private static double SLOW_FORWARD_SCALE = 0.4;
    private static double SLOW_STRAFE_SCALE = 0.5;
    private static double SLOW_ROTATE_SCALE = 0.3;

    private double forwardScale = FAST_FORWARD_SCALE;
    private double strafeScale = FAST_STRAFE_SCALE;
    private double rotateScale = FAST_ROTATE_SCALE;

    public RobotRelativeDriveController(RobotBaseOpMode robot) {
        drive = robot.getMecanumDrive();
        odometer = robot.getOdometer();
        driverGamepad = robot.getDriverGamepad();
        telemetry = robot.getTelemetry();
    }

    public void handleInput() {
        double forward = -driverGamepad.left_stick_y;
        double strafe = driverGamepad.left_stick_x;
        double rotate = driverGamepad.right_stick_x;

        if (driverGamepad.backWasPressed()) {
            odometer.update();
            odometer.setHeading(0.0, AngleUnit.DEGREES);
        } else if (driverGamepad.bWasPressed()) {
            forwardScale = SLOW_FORWARD_SCALE;
            strafeScale = SLOW_STRAFE_SCALE;
            rotateScale = SLOW_ROTATE_SCALE;
        } else if (driverGamepad.xWasPressed()) {
            forwardScale = FAST_FORWARD_SCALE;
            strafeScale = FAST_STRAFE_SCALE;
            rotateScale = FAST_ROTATE_SCALE;
        }

        forward *= forwardScale;
        strafe *= strafeScale;
        rotate *= rotateScale;

        drive.drive(forward, strafe, rotate);

        odometer.update();
        telemetry.addData("X", odometer.getX(DistanceUnit.INCH));
        telemetry.addData("Y", odometer.getY(DistanceUnit.INCH));
        telemetry.addData("H", odometer.getHeading(AngleUnit.DEGREES));
        telemetry.addData("Speed scale", forwardScale);
    }
}
