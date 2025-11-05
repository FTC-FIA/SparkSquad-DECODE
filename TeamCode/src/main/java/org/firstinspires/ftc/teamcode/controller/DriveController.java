package org.firstinspires.ftc.teamcode.controller;

import com.qualcomm.robotcore.hardware.Gamepad;

import org.firstinspires.ftc.robotcore.external.Telemetry;
import org.firstinspires.ftc.teamcode.component.drive.FieldRelativeDrive;
import org.firstinspires.ftc.teamcode.opmode.RobotBaseOpMode;

public class DriveController {

    private final FieldRelativeDrive drive;
    private final Gamepad driverGamepad;
    private final Telemetry telemetry;

    private double forwardScale = 1.0;
    private double strafeScale = 1.0;
    private double rotateScale = 1.0;

    public DriveController(RobotBaseOpMode robot) {
        drive = robot.getFieldRelativeDrive();
        driverGamepad = robot.getDriverGamepad();
        telemetry = robot.getTelemetry();
    }

    public void handleInput() {
        double forward = -driverGamepad.left_stick_y;
        double strafe = driverGamepad.left_stick_x;
        double rotate = driverGamepad.right_stick_x;

        if (driverGamepad.bWasPressed()) {
            double newScale = Math.min(forwardScale + 0.1, 1.0);
            forwardScale = strafeScale = rotateScale = newScale;
        } else if (driverGamepad.xWasPressed()) {
            double newScale = Math.max(forwardScale - 0.1, 0.0);
            forwardScale = strafeScale = rotateScale = newScale;
        }

        forward *= forwardScale;
        strafe *= strafeScale;
        rotate *= rotateScale;

        drive.drive(forward, strafe, rotate);

        telemetry.addData("Speed scale", forwardScale);
        telemetry.addData("Forward", forward);
        telemetry.addData("Strafe", strafe);
        telemetry.addData("Rotate", rotate);

    }
}
